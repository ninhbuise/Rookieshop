package com.enashtech.rookieserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String color_name;
    private String hex_code;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;
}
