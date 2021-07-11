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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @PastOrPresent
    private Date created;

    @Pattern(regexp = "(^$|[0-9]{9,11})", message = "Phone mush match 9-11 digits number")
    private String phone;

    @Valid
    @NotNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private Status status;

    @Valid
    @NotNull
    @OneToMany
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetail> orderDetails;

    @PrePersist
    protected void onCreate() {
      this.created = new Date();
      this.status = Status.PROCESSING;
    }
}
