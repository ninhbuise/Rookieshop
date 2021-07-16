package com.enashtech.rookieserver.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.enashtech.rookieserver.entity.Role;
import com.enashtech.rookieserver.entity.RoleName;
import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.repository.UserRepository;
import com.enashtech.rookieserver.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	@MockBean
	private UserRepository userRepo;

	private List<User> userList;

    @BeforeEach
	void setUp() {
		this.userList = new ArrayList<>();
        Set<Role> roles = new HashSet<>();

        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("password");
        roles.add(new Role(RoleName.ROLE_ADMIN));
        user.setRoles(roles);
        this.userList.add(0, user);

        roles.clear();
        user.setId(2);
        user.setUsername("Krzay");
        user.setPassword("password");
        roles.add(new Role(RoleName.ROLE_CUSTOMER));
        user.setRoles(roles);
        this.userList.add(1, user);

        roles.clear();
        user.setId(3);
        user.setUsername("User3");
        user.setPassword("password");
        roles.add(new Role(RoleName.ROLE_STORE));
        user.setRoles(roles);
        this.userList.add(2, user);
        
	}

    @Test
	@WithMockUser(username = "admin")
	public void getAllusers_ReturnUserList() throws Exception {
		when(userService.getUsers()).thenReturn(this.userList);
		this.mockMvc.perform(get("/api/v1/users"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.size()", is(userList.size())));
	}

    @Test
	@WithMockUser(username = "admin")
	public void getUserbyId_ReturnUser() throws Exception {
		when(userService.getUserById(1)).thenReturn(new User("Krzay", "password"));
		this.mockMvc.perform(get("/api/v1/users/1"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.username", is("Krzay")));
	}
}
