package com.enashtech.rookieserver.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    String username;
    @NotBlank
    @Size(min = 6, max = 40)
    String password;
    @NotBlank
    @Size(min=3, max = 30)
    private String first_name;
    @NotBlank
    @Size(min=3, max = 30)
    private String last_name;
    //this matches either empty string or 9-11 digits number.
    @Pattern(regexp="(^$|[0-9]{9,11})")
    private String phone;
    @Email
    private String email;
    
    public CustomerDTO(Customer customer){
        this.username = customer.getUser().getUsername();
        this.password = customer.getUser().getPassword();
        this.first_name = customer.getFirst_name();
        this.last_name = customer.getLast_name();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }

    public CustomerDTO(){
        
    }
}
