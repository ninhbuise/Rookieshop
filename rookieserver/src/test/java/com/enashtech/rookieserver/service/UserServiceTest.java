package com.enashtech.rookieserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import org.junit.jupiter.api.BeforeEach;
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
    UserService service;

    List<User> list;

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        User user1 = new User("admin", "123456");
        User user2 = new User("ABC", "zxc");
        User user3 = new User("Rookies", "password");

        list.add(0, user1);
        list.add(1, user2);
        list.add(2, user3);
    }
    
    @Test
    public void getUsers_ReturnUserList() {
        when(userRepo.findAll()).thenReturn(list);
        List<User> userList = service.getUsers();
        assertEquals(3, userList.size());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void getUserById_RetuenUser() {
        when(userRepo.findById(3)).thenReturn(Optional.of(list.get(2)));
        User userABC = service.getUserById(3);
        assertEquals("password", userABC.getPassword());
    }

    @Test
    public void getUserById_ThrowNotFoundExecption() throws NotFoundExecptionHandle {
        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            service.getUserById(1);
        });
        assertEquals("Could not found user: 1", exception.getMessage());
    }

    @Test
    public void getUserByUsername_ReturnUser() {
        when(userRepo.findByUsername("ABC")).thenReturn(Optional.of(list.get(1)));
        User userABC = service.getUserByUsername("ABC");
        assertEquals("zxc", userABC.getPassword());
    }

    @Test
    public void getUserByUsername_ThrowNotFoundExecption() {
        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            service.getUserByUsername("ABC");
        });
        assertEquals("Could not found user: ABC", exception.getMessage());
    }

    @Test
    public void saveUser_ReturnUser() {
        when(userRepo.save(list.get(0))).thenReturn(list.get(0));
        assertEquals(service.saveUser(list.get(0)), list.get(0));
    }

    @Test
    public void updateUser_ReturnUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        user.setPassword("D@nim");
        service.updateUser(user);

        User userABC = service.getUserById(1);
        assertEquals("D@nim", userABC.getPassword());
    }

    @Test
    public void updateUser_ThrowNotFoundExecption() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");
        userRepo.save(user);

        user.setId(2);
        user.setUsername("Rzayu");

        Exception exception = assertThrows(NotFoundExecptionHandle.class, () -> {
            service.updateUser(user);
        });
        
        assertEquals("Could not found user: 2", exception.getMessage());
    }

    @Test
    public void updateUserStatus_ReturnUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("ABC");
        user.setPassword("password");
        
        Role role = new Role();
        role.setName(RoleName.ROLE_ADMIN);

        when(userRepo.save(user)).thenReturn(user);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(roleRepo.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of(role));

        service.updateUserStatus(1, "banned");
        User userABC = service.getUserById(1);

        assertEquals(Status.BANNED, userABC.getStatus());
    }

    @Test
    public void updateUserStatus_HasRoleAdmin_ThrowRuntimeExceptioon() throws RuntimeExceptionHandle {
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
            service.updateUserStatus(1, "locked");
        });

        assertEquals("Could not change role for user has role 'ADMIN' user: 1", exception.getMessage());
    }

    @Test
    public void updateUserStatus_StatusNotFound_ThorwsNotFoundExecption() throws NotFoundExecptionHandle {
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
            service.updateUserStatus(1, "closed");
        });

        assertEquals("Could not found status: closed", exception.getMessage());
    }

    @Test
    public void updateUserRole_ReturnUser() {
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

        service.updateUserRole(1, newRoles);

        User newUser = service.getUserById(1);

        assertEquals(newUser.getRoles().contains(new Role(RoleName.ROLE_STORE)), true);
    }

    @Test
    public void updateUserRole_ListRoleIsEmpty_ThrowsRuntimeException() throws RuntimeExceptionHandle {
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
            service.updateUserRole(1, newRoles);
        });

        assertEquals("Roles can't be empty", exception.getMessage());
    }

    @Test
    public void updateUserRole_RoleNotFound_ThrowsRuntimeException() throws RuntimeExceptionHandle {
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
            service.updateUserRole(1, newRoles);
        });

        assertEquals("Role is not found.", exception.getMessage());
    }

    @Test
    public void updateUserRole_UserNotFound_ThrowsNotFoundExecption() throws NotFoundExecptionHandle {
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
            service.updateUserRole(2, newRoles);
        });

        assertEquals("Could not found user: 2", exception.getMessage());
    }

    @Test
    public void existsByUsername_ReturnTrue() {
        when(userRepo.existsByUsername("ABC")).thenReturn(true);
        assertEquals(service.existsByUsername("ABC"), true);
    }

    @Test
    public void existsByUsername_ReturnFalse() {
        when(userRepo.existsByUsername("ABC")).thenReturn(false);
        assertEquals(service.existsByUsername("ABC"), false);
    }

    @Test
    public void deleteUser() {
        service.deleteUser(1);
        verify(userRepo).deleteById(any());
    }
}
