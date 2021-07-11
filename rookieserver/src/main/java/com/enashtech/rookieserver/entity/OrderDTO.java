package com.enashtech.rookieserver.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class OrderDTO {
    @Valid
    @NotNull
    private List<OrderDetailDTO> detailDTOs;
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{9,11})", message = "Phone mush match 9-11 digits number")
    private String phone;
    @NotNull
    @Valid
    private Address address;

    OrderDTO() {

    }
}
