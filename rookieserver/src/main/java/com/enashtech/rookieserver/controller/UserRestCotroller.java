package com.enashtech.rookieserver.controller;

import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserRestCotroller {
    private final UserService userService;

    @Autowired
    public UserRestCotroller(UserService userService){
        this.userService = userService;
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable int id) {
        return userService.updateUser(newUser, id);
    }
}
