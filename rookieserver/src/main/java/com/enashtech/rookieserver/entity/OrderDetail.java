package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail implements Serializable{
    @Id
    private int id;
    @NotBlank 
    private int amount;
    @NotBlank 
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
