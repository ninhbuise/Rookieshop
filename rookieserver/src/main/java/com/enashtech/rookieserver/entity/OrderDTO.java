package com.enashtech.rookieserver.entity;

import java.util.List;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class OrderDTO {
    private List<OrderDetailDTO> detailDTOs;
    @Pattern(regexp="(^$|[0-9]{9,11})")
    private String phone;
    private Address address;

    OrderDTO() {
        
    }
}
