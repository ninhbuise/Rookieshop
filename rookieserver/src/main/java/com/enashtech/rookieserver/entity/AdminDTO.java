package com.enashtech.rookieserver.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminDTO {
    @NotBlank(message = "Username should not be null or blank")
    @Size(min = 3, max = 20, message = "Username should be in range 3-20")
    String username;

    @NotBlank(message = "Password should not be null or blank")
    @Size(min = 6, max = 40, message = "Username should be in range 3-20")
    String password;

    public AdminDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public AdminDTO() {

    }
}
