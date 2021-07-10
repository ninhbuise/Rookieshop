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
@Table(name = "store")
public class Store implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(min=3, max = 30)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address address;

    public Store() {
    }
}
