package com.enashtech.rookieserver.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "product_type", nullable = false)
    private ProductType productType;
    private double price;
    private int quantity;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Size> sizes;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Color> colors;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Image> images;
    @ManyToOne
    @JoinColumn(name = "store", nullable = false)
    private Store store;
    @ManyToOne
    @JoinColumn(name = "product_detail", nullable = false)
    private ProductDetail productDetail;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviews;
}
