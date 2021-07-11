package com.enashtech.rookieserver.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "product_detail")
public class ProductDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    @Size(min = 3, max = 30)
    private String brand;
    @NotBlank
    @Size(min = 3, max = 30)
    private String brand_orgin;
    @NotBlank
    @Size(min = 3, max = 30)
    private String made_in;
    @NotBlank
    @Size(min = 3, max = 30)
    private String material;
    @NotBlank
    @Size(min = 3, max = 30)
    private String sku;
    @Lob
    @NotBlank
    private String description;

    @Valid
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;
}
