package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "\"Order\"")
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date created;    
    
    //this matches either empty string or 9-11 digits number.
    @Pattern(regexp="(^$|[0-9]{9,11})")
    private String phone;
    
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address addresses;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private Status status;

    @OneToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetail> orderDetails;

    @PrePersist
    protected void onCreate() {
      created = new Date();
    }
}
