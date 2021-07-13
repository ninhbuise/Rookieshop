package com.enashtech.rookieserver.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.enashtech.rookieserver.entity.RoleName;
import com.enashtech.rookieserver.entity.User;
import com.enashtech.rookieserver.handleException.RuntimeExceptionHandle;
import com.enashtech.rookieserver.entity.AdminDTO;
import com.enashtech.rookieserver.entity.CustomerDTO;
import com.enashtech.rookieserver.entity.Role;
import com.enashtech.rookieserver.payload.request.LoginRequest;
import com.enashtech.rookieserver.payload.response.JwtResponse;
import com.enashtech.rookieserver.payload.response.MessageResponse;
import com.enashtech.rookieserver.security.jwt.JwtUtils;
import com.enashtech.rookieserver.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserService userService;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Autowired
    AuthServiceImpl(UserService userService, CustomerService customerService, RoleService roleService,
                    PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.customerService = customerService;
        this.roleService = roleService;
        this. encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        // TODO, authenticate when login
        // Username, pass from client
        // com.nashtech.rookies.security.WebSecurityConfig.configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
        // authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // on this step, we tell to authenticationManager how we load data from database
        // and the password encoder
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // if go there, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                                                 userDetails.getId(),
                                                 userDetails.getUsername(),
                                                 roles));
        
        // return new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()).getAuthorities().toString();
    }

    @Override
    public ResponseEntity<?> register(CustomerDTO customerDTO) {
        if (userService.existsByUsername(customerDTO.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }
        
        if(customerService.existsByEmail(customerDTO.getEmail())) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Email is already taken!"));
        }

        if(customerService.existsByPhone(customerDTO.getPhone())) {
            return ResponseEntity
            .badRequest()
            .body(new MessageResponse("Error: Phone is already taken!"));
        }
        
        // Create new user's account
        User user = new User(customerDTO.getUsername(),
                             encoder.encode(customerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName(RoleName.ROLE_CUSTOMER)
            .orElseThrow(() -> new RuntimeExceptionHandle("Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public ResponseEntity<?> saveAdmin(AdminDTO adminDTO) {
        if (userService.existsByUsername(adminDTO.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }
        // Create new admin's account
        User admin = new User(adminDTO.getUsername(),
                             encoder.encode(adminDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeExceptionHandle("Role is not found."));
        roles.add(adminRole);
        admin.setRoles(roles);
        userService.saveUser(admin);
        return ResponseEntity.ok(new MessageResponse("Add new user successfully!"));
    }

}
