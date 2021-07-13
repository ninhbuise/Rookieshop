package com.enashtech.rookieserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.enashtech.rookieserver.entity.Role;
import com.enashtech.rookieserver.entity.RoleName;
import com.enashtech.rookieserver.entity.Status;
import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.handleException.NotFoundExecptionHandle;
import com.enashtech.rookieserver.handleException.RuntimeExceptionHandle;
import com.enashtech.rookieserver.repository.RoleRepository;
import com.enashtech.rookieserver.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {
    @MockBean
    UserRepository userRepo;

    @MockBean
    RoleRepository roleRepo;

    @Autowired
    UserService userService;
    
    @Test
    public void getUsers() {
        //Give
        List<User> list = new ArrayList<>();
        User user1 = new User("admin", "123456");
        User user2 = new User("ABC", "zxc");
        User user3 = new User("Rookies", "password");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(userRepo.findAll()).thenReturn(list);

        //When
        List<User> userList = userService.getUsers();

        //Then
        assertEquals(3, userList.size());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void getUserById() {
        //Give
        User user = new User();
        user.setId(1);
		user.setUsername("ABC");
		user.setPassword("password");

		when(userRepo.save(user)).thenReturn(user);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        //when
        User userABC = userService.getUserById(1);

        //then
        assertEquals("password", userABC.getPassword());
    }

    @Test
    public void getUserById_notFound() throws NotFoundExecptionHandle {
     
        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            userService.getUserById(1);
        });
        
        assertEquals("Could not found user: 1", exception.getMessage());
    }

    @Test
    public void getUserByUsername() {
        //Give
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");
        userRepo.save(user);

        when(userRepo.findByUsername("ABC")).thenReturn(Optional.of(user));

        //when
        User userABC = userService.getUserByUsername("ABC");

        //then
        assertEquals("password", userABC.getPassword());
    }

    @Test
    public void getUserByUsername_NotFlound() {
        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            userService.getUserByUsername("ABC");
        });
        
        assertEquals("Could not found user: ABC", exception.getMessage());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        when(userRepo.save(user)).thenReturn(user);

        assertEquals(userService.saveUser(user), user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        user.setPassword("D@nim");
        userService.updateUser(user);

        User userABC = userService.getUserById(1);
        assertEquals("D@nim", userABC.getPassword());
    }

    @Test
    public void updateUser_NotFound() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");
        userRepo.save(user);

        user.setId(2);
        user.setUsername("Rzayu");

        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            userService.updateUser(user);
        });
        
        assertEquals("Could not found user: 2", exception.getMessage());
    }

    @Test
    public void updateUserStatus() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");
        
        Role role = new Role();
        role.setName(RoleName.ROLE_ADMIN);

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(roleRepo.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of(role));

        userService.updateUserStatus(1, "locked");
        User userABC = userService.getUserById(1);

        assertEquals(Status.LOCKED, userABC.getStatus());
    }

    @Test
    public void updateUserStatus_RoleAdmin() throws RuntimeExceptionHandle {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");
        
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(RoleName.ROLE_ADMIN);
        roles.add(role);
        user.setRoles(roles);
        
        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(roleRepo.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of(role));

        Exception exception = assertThrows(RuntimeExceptionHandle.class, () -> {
            userService.updateUserStatus(1, "locked");
        });

        assertEquals("Could not change role for user has role 'ADMIN' user: 1", exception.getMessage());
    }

    @Test
    public void updateUserStatus_StatusNotFound() throws NotFoundExecptionHandle {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        Role role = new Role();
        role.setName(RoleName.ROLE_ADMIN);
        
        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(roleRepo.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of(role));

        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            userService.updateUserStatus(1, "closed");
        });

        assertEquals("Could not found status: closed", exception.getMessage());
    }

    @Test
    public void updateUserRole() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(RoleName.ROLE_CUSTOMER);
        roles.add(role);
        user.setRoles(roles);

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(roleRepo.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of( new Role(RoleName.ROLE_ADMIN)));
        when(roleRepo.findByName(RoleName.ROLE_STORE)).thenReturn(Optional.of( new Role(RoleName.ROLE_STORE)));
        when(roleRepo.findByName(RoleName.ROLE_CUSTOMER)).thenReturn(Optional.of( new Role(RoleName.ROLE_CUSTOMER)));

        List<String> newRoles = new ArrayList<>();
        newRoles.add("store");
        newRoles.add("admin");

        userService.updateUserRole(1, newRoles);

        User newUser = userService.getUserById(1);

        assertEquals(newUser.getRoles().contains(new Role(RoleName.ROLE_STORE)), true);
    }

    @Test
    public void updateUserRole_ListRoleIsEmpty() throws RuntimeExceptionHandle {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(RoleName.ROLE_CUSTOMER);
        roles.add(role);
        user.setRoles(roles);

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(roleRepo.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of( new Role(RoleName.ROLE_ADMIN)));
        when(roleRepo.findByName(RoleName.ROLE_STORE)).thenReturn(Optional.of( new Role(RoleName.ROLE_STORE)));
        when(roleRepo.findByName(RoleName.ROLE_CUSTOMER)).thenReturn(Optional.of( new Role(RoleName.ROLE_CUSTOMER)));

        List<String> newRoles = new ArrayList<>();
        Exception exception = assertThrows(RuntimeExceptionHandle.class, () -> {
            userService.updateUserRole(1, newRoles);
        });

        assertEquals("Roles can't be empty", exception.getMessage());
    }

    @Test
    public void updateUserRole_RoleNotFound() throws RuntimeExceptionHandle {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(RoleName.ROLE_CUSTOMER);
        roles.add(role);
        user.setRoles(roles);

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        List<String> newRoles = new ArrayList<>();
        newRoles.add("store");
        newRoles.add("admin");
        Exception exception = assertThrows(RuntimeExceptionHandle.class, () -> {
            userService.updateUserRole(1, newRoles);
        });

        assertEquals("Role is not found.", exception.getMessage());
    }

    @Test
    public void updateUserRole_UserNotFound() throws NotFoundExecptionHandle {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName(RoleName.ROLE_CUSTOMER);
        roles.add(role);
        user.setRoles(roles);

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(roleRepo.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of( new Role(RoleName.ROLE_ADMIN)));
        when(roleRepo.findByName(RoleName.ROLE_STORE)).thenReturn(Optional.of( new Role(RoleName.ROLE_STORE)));
        when(roleRepo.findByName(RoleName.ROLE_CUSTOMER)).thenReturn(Optional.of( new Role(RoleName.ROLE_CUSTOMER)));

        List<String> newRoles = new ArrayList<>();
        newRoles.add("store");
        newRoles.add("admin");
        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            userService.updateUserRole(2, newRoles);
        });

        assertEquals("Could not found user: 2", exception.getMessage());
    }

    @Test
    public void existsByUsername() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        when(userRepo.existsByUsername("ABC")).thenReturn(true);

        assertEquals(userService.existsByUsername("ABC"), true);
    }
}
