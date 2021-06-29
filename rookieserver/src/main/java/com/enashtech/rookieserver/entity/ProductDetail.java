package com.enashtech.rookieserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "prodict_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String brand;
    private String brand_orgin;
    private String made_in;
    private String material;
    private String sku;
    private String description;
}
