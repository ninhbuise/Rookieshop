package com.enashtech.rookieserver.entity;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private ProductType productType;
    private Set<Size> sizes;
    private Set<Color> colors;
    private List<Image> images;
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
