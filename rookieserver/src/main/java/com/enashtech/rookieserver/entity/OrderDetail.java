package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail implements Serializable{
    @Id
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;
}
