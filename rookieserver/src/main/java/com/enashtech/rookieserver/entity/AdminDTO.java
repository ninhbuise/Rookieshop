package com.enashtech.rookieserver.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    String username;
    @NotBlank
    @Size(min = 6, max = 40)
    String password;

    public AdminDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public AdminDTO() {
        
    }
}
