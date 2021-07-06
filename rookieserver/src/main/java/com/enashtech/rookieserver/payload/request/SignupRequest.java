package com.enashtech.rookieserver.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import com.enashtech.rookieserver.entity.Customer;

import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Customer customer;
}