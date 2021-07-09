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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "customer", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "phone"
    }),
    @UniqueConstraint(columnNames = {
        "email"
    })
})
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
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
    private Date birth_day;
    
    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Address> addresses;

    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Order> orders;

    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Review> reviews;

    public Customer() {
    }

    public List<Order> addOrder(Order order) {
        if(this.orders.isEmpty())
            this.orders = new ArrayList<>();
        this.orders.add(order);
        return this.orders;
    }
}
