package com.enashtech.rookieserver.entity;

import lombok.Data;

@Data
public class AdminDTO {
    String username;
    String password;

    public AdminDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public AdminDTO(){
        
    }
}
