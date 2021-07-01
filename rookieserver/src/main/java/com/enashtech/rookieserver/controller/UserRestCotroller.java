package com.enashtech.rookieserver.controller;

import java.util.List;

import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserRestCotroller {
    private final UserService userService;

    @Autowired
    public UserRestCotroller(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    User addNewUser(@RequestBody User newUser){
        return userService.addNewUser(newUser);
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable int id) {
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}