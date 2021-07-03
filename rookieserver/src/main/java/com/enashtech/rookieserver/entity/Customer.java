package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "customer")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @OneToOne(mappedBy = "customer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
    @NotBlank
    @Size(min=3, max = 30)
    private String fist_name;
    @NotBlank
    @Size(min=3, max = 30)
    private String last_name;
    //this matches either empty string or 11 digits number.
    @Pattern(regexp="(^$|[0-9]{11})")
    private String phone;
    @Email
    private String email;
    @NotEmpty
    private Date birth_day;
    
    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Order> order;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Review> reviews;
}
