package com.enashtech.rookieserver.controller;

import java.util.List;

import com.enashtech.rookieserver.entity.AdminDTO;
import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.service.AuthService;
import com.enashtech.rookieserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AdminController(UserService userService, AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping()
    ResponseEntity<?> addNewAdmin(@RequestBody AdminDTO adminDTO){
        return authService.addNewAdmin(adminDTO);
    }
    
    @GetMapping("/users")
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/user/{id}")
    User updateUserStatus(@PathVariable int id, @RequestParam String status){
        return userService.updateUserStatus(id, status);
    }

    @PostMapping("/user-role/{id}")
    User updateUserRole(@PathVariable int id, @RequestParam List<String> roles){
        return userService.updateUserRole(id, roles);
    }
}
