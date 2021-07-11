package com.enashtech.rookieserver.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "color")
public class Color implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Color's name should not be null or blank")
    @Size(min = 3, max = 30, message = "Color's name should be in range 3-30")
    private String color_name;

    @NotBlank(message = "Hex code of color should not be null or blank")
    @Size(min = 7, max = 7, message = "Hex code of color should be in range 7-7")
    private String hex_code;
}
