package com.enashtech.rookieserver.controller;

import java.util.List;

import javax.validation.Valid;

import com.enashtech.rookieserver.entity.Image;
import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserCotroller {
    private final UserService userService;

    @Autowired
    public UserCotroller(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user")
    User updateUser(@Valid @RequestBody User newUser) {
        return userService.updateUser(newUser);
    }

    @PutMapping("/user-avatar")
    @ResponseBody
    User updateUserAvatar(@Valid @RequestBody Image avatar, Authentication authentication) {
        return userService.updateUserAvatar(avatar, authentication.getName());
    }

    @GetMapping("/users")
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    @ResponseBody
    User getUserByUsername(Authentication authentication) {
        return userService.getUserByUsername(authentication.getName());
    }

    @PostMapping("/users/status/{id}")
    User updateUserStatus(@PathVariable int id, @RequestParam String status) {
        return userService.updateUserStatus(id, status);
    }

    @PostMapping("/users/roles/{id}")
    User updateUserRole(@PathVariable int id, @RequestParam List<String> roles) {
        return userService.updateUserRole(id, roles);
    }
}
