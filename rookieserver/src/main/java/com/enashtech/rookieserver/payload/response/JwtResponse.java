package com.enashtech.rookieserver.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, int id, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}