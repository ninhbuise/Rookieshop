package com.enashtech.rookieserver.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String user_name;
    private String pass_word;
    @OneToOne(mappedBy = "user")
    private UserRole userRole;    
    private String url_avatar;
    @OneToOne(mappedBy = "user")
    private Customer customer;
}
