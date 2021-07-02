package com.enashtech.rookieserver.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    private String district;
    private String ward;
    private String street;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;
}
