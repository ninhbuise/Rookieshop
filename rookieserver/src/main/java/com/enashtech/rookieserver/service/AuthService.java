package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.entity.AdminDTO;
import com.enashtech.rookieserver.entity.CustomerDTO;
import com.enashtech.rookieserver.payload.request.LoginRequest;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> register(CustomerDTO customerDTO);
    ResponseEntity<?> addNewAdmin(AdminDTO adminDTO);
}
