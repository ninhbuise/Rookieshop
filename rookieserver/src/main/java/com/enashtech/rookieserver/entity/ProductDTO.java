package com.enashtech.rookieserver.entity;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class ProductDTO {
    @PositiveOrZero
    private int id;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private double price;

    @NotNull
    @Positive
    private int quantity;

    @Valid
    @NotNull
    private ProductType productType;

    @Valid
    @NotNull
    private Set<Size> sizes;

    @Valid
    @NotNull
    private Set<Color> colors;

    @Valid
    @NotNull
    private List<Image> images;

    @Valid
    @NotNull
    private ProductDetail productDetail;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.productType = product.getProductType();
        this.sizes = product.getSizes();
        this.colors = product.getColors();
        this.images = product.getImages();
        this.productDetail = product.getProductDetail();
    }

    public ProductDTO() {
    }
}
