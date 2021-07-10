package com.enashtech.rookieserver.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.enashtech.rookieserver.entity.Role;
import com.enashtech.rookieserver.entity.RoleName;
import com.enashtech.rookieserver.entity.Status;
import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.handleException.RuntimeExceptionHandle;
import com.enashtech.rookieserver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found user: " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found user: " + username));
    }

    @Override
    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User newUser, int id) {
        return userRepository.findById(id)
            .map(user -> {
                user.setPassword(newUser.getPassword());
                user.setAvatar(newUser.getAvatar());
                return userRepository.save(user);
            })
            .orElseGet(() -> {
                newUser.setId(id);
                return userRepository.save(newUser);
            }
        );
    }

    @Override
    public User updateUserStatus(int id, String status) {
        return userRepository.findById(id)
            .map(user -> {
                //Check if user have role ADMIN
                Role roleAdmin = roleService.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeExceptionHandle("Role is not found."));
                if(user.getRoles().contains(roleAdmin))
                    throw new RuntimeExceptionHandle("Could not change role for user has role 'ADMIN' user: " + id);
                //check stats invalid
                switch(status.toLowerCase()) {
                    case "locked":
                        user.setStatus(Status.LOCKED);
                    break;
                    case "open":
                        user.setStatus(Status.OPEN);
                    break;
                    default:
                        throw new NotFoundExecptionHandle("Could not found status: " + status);
                }
                return userRepository.save(user);
            })
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found user: " + id));
    }

    public User updateUserRole(int id, List<String> roles) {
        if(roles.size() == 0)
            throw new RuntimeExceptionHandle("roles can't be empty");
        //lowcase list role
        List<String> newRoles = roles.stream()
                             .map(String::toLowerCase)
                             .collect(Collectors.toList());

        return userRepository.findById(id)
            .map(user -> {
                //Check if user have role ADMIN
                Role roleAdmin = roleService.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeExceptionHandle("Role is not found."));
                if(user.getRoles().contains(roleAdmin) && !newRoles.contains("admin"))
                    throw new RuntimeExceptionHandle("Could not remove role 'ADMIN' for user has role 'ADMIN' user: " + id);

                Set<Role> userRoles = new HashSet<>();
                newRoles.forEach(role -> {
                    switch (role.toLowerCase()) {
                        case "admin":
                            Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeExceptionHandle("Role is not found."));
                                userRoles.add(adminRole);
                            break;
                        case "store":
                            Role storeRole = roleService.findByName(RoleName.ROLE_STORE)
                                .orElseThrow(() -> new RuntimeExceptionHandle("Role is not found."));
                                userRoles.add(storeRole);
                            break;
                        default:
                            Role userRole = roleService.findByName(RoleName.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeExceptionHandle("Role is not found."));
                                userRoles.add(userRole);
                    }
                });
                user.setRoles(userRoles);
                return userRepository.save(user);
            })
            .orElseThrow(() -> new NotFoundExecptionHandle("Could not found user: " + id));
    }


    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
