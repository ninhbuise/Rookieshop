package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_type", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductType productType;
    private double price;
    private int quantity;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Size> sizes;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Color> colors;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "store", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Store store;

    @OneToOne(fetch = FetchType.LAZY,
        cascade =  CascadeType.ALL,
        mappedBy = "product")
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductDetail productDetail;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Review> reviews;
}
