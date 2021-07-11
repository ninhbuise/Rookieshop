package com.enashtech.rookieserver.entity;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerDTO {
    @NotBlank(message = "Username should not be null or blank")
    @Size(min = 3, max = 20, message = "Username should be in range 3-20")
    String username;

    @NotBlank(message = "Password should not be null or blank")
    @Size(min = 6, max = 40, message = "Username should be in range 3-20")
    String password;

    @NotBlank(message = "Cutomer's fist name should not be null or blank")
    @Size(min = 3, max = 30, message = "Cutomer's fist name  should be in range 3-30")
    private String first_name;

    @NotBlank(message = "Cutomer's last name should not be null or blank")
    @Size(min = 3, max = 30, message = "Cutomer's fist name  should be in range 3-30")
    private String last_name;

    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{9,11})", message = "Phone mush match 9-11 digits number")
    private String phone;

    @NotBlank
    @Email
    private String email;

    @Valid
    @NotNull
    private Address address;

    public CustomerDTO(Customer customer) {
        this.username = customer.getUser().getUsername();
        this.password = customer.getUser().getPassword();
        this.first_name = customer.getFirst_name();
        this.last_name = customer.getLast_name();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.address = customer.getAddress();
    }

    public CustomerDTO() {

    }
}
