package com.enashtech.rookieserver.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name ="address")
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(min=3, max = 50)
    private String city;
    @NotBlank
    @Size(min=3, max = 50)
    private String district;
    @NotBlank
    @Size(min=3, max = 50)
    private String ward;
    @NotBlank
    @Size(min=3, max = 50)
    private String street;
}
