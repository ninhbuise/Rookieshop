package com.enashtech.rookieserver.controller;

import javax.validation.Valid;

import com.enashtech.rookieserver.entity.AdminDTO;
import com.enashtech.rookieserver.entity.CustomerDTO;
import com.enashtech.rookieserver.payload.request.LoginRequest;
import com.enashtech.rookieserver.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController (AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerDTO customerDTO) {
        return authService.register(customerDTO);
    }

    @PostMapping("/admin")
    ResponseEntity<?> addNewAdmin(@RequestBody AdminDTO adminDTO){
        return authService.addNewAdmin(adminDTO);
    }
}