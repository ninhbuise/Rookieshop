package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

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
    // @Pattern(regexp="(^([0-9]*[1-9][0-9]*(\\.[0-9]+)?|[0]+\\.[0-9]*[1-9][0-9]*)$)")
    private double price;
    // @Pattern(regexp="(^[0-9]\\d*$)")
    private int quantity;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Size> sizes;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Color> colors;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
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

    public Product(ProductDTO productDTO) {
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.productType = productDTO.getProductType();
        this.quantity = productDTO.getQuantity();
        this.colors = productDTO.getColors();
        this.sizes = productDTO.getSizes();
        this.images = productDTO.getImages();
        this.productDetail = productDTO.getProductDetail();
    }

    public Product() {
    }
}
