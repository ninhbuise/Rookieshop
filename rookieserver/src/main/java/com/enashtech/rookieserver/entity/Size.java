package com.enashtech.rookieserver.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "size")
public class Size implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String size_name;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;
}
