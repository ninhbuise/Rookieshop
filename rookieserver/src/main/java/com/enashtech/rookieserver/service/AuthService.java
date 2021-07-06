package com.enashtech.rookieserver.service;

import com.enashtech.rookieserver.payload.request.LoginRequest;
import com.enashtech.rookieserver.payload.request.SignupRequest;

import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> register(SignupRequest signUpRequest);
}
