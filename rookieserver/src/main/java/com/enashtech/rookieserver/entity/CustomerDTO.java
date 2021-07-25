package com.enashtech.rookieserver.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Password should not be null or blank")
    @Size(min = 6, max = 40, message = "Password should be in range 6-40")
    String password;

    @NotBlank(message = "Cutomer's fist name should not be null or blank")
    @Size(min = 3, max = 30, message = "Cutomer's fist name  should be in range 3-30")
    private String first_name;

    @NotBlank(message = "Cutomer's last name should not be null or blank")
    @Size(min = 3, max = 30, message = "Cutomer's fist name  should be in range 3-30")
    private String last_name;

    public CustomerDTO(Customer customer) {
        this.password = customer.getUser().getPassword();
        this.first_name = customer.getFirst_name();
        this.last_name = customer.getLast_name();
        this.email = customer.getEmail();
    }

    public CustomerDTO() {

    }
}
