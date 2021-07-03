package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

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
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<UserRole> roles;  
    @NotBlank
    @Size(min = 3, max = 90)  
    private String url_avatar;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private Status status;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.status = Status.OPEN;
    }

    public User() {
        this.status = Status.OPEN;
	}
}
