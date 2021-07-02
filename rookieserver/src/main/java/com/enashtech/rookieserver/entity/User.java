package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "\"User\"")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<UserRole> userRoles;    
    private String url_avatar;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
