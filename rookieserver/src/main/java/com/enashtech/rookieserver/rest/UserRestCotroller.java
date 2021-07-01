package com.enashtech.rookieserver.rest;

import java.util.List;
import java.util.Optional;

import com.enashtech.rookieserver.HandleException.UserNotFoundException;
import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable int id){
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable int id) {
    
    return userRepository.findById(id)
        .map(user -> {
            user.setPass_word(newUser.getPass_word());
            user.setUrl_avatar(newUser.getUrl_avatar());
            user.setUserRole(newUser.getUserRole());
            return userRepository.save(user);
        })
        .orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
