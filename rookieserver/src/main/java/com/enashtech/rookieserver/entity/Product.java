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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String name;

    @Valid
    @ManyToOne
    @JoinColumn(name = "product_type", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductType productType;

    @NotNull
    @Positive
    private double price;

    @NotNull
    @PositiveOrZero
    private int quantity;

    @Valid
    @NotNull
    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Size> sizes;

    @Valid
    @NotNull
    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Color> colors;

    @Valid
    @NotNull
    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Image> images;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Store store;

    @Valid
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductDetail productDetail;

    @Valid
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
