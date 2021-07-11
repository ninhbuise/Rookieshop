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
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;

@Entity
@Data
@Table(name = "review")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String comment;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @PastOrPresent
    private Date created;

    @PrePersist
    protected void onCreate() {
      created = new Date();
    }
}
