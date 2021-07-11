package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "customer", uniqueConstraints = { 
    @UniqueConstraint(columnNames = { "phone" }),
    @UniqueConstraint(columnNames = { "email" }) 
})
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Valid
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
    
    @NotBlank(message = "Cutomer's fist name should not be null or blank")
    @Size(min = 3, max = 30, message = "Cutomer's fist name  should be in range 3-30")
    private String first_name;
    
    @NotBlank(message = "Cutomer's last name should not be null or blank")
    @Size(min = 3, max = 30, message = "Cutomer's last name  should be in range 3-30")
    private String last_name;
    
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{9,11})", message = "Phone mush match 9-11 digits number")
    private String phone;
    
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    @Past
    private Date birth_day;

    @Valid
    @NotNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address address;

    @Valid
    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Order> orders;

    @Valid
    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Review> reviews;

    public Customer(CustomerDTO customerDTO) {
        this.first_name = customerDTO.getFirst_name();
        this.last_name = customerDTO.getLast_name();
        this.email = customerDTO.getEmail();
        this.phone = customerDTO.getPhone();
        this.address = customerDTO.getAddress();
    }

    public Customer() {
    }

    public List<Order> addOrder(Order order) {
        if (this.orders.isEmpty())
            this.orders = new ArrayList<>();
        this.orders.add(order);
        return this.orders;
    }
}
