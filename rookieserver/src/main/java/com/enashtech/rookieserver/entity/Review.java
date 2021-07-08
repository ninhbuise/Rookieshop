package com.enashtech.rookieserver.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "review")
public class Review implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Lob
    private String comment;
    private int rating;
 
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    private Date created;    
 
    @PrePersist
    protected void onCreate() {
      created = new Date();
    }
}
