package com.enashtech.rookieserver.entity;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class OrderDetailDTO {
    @Pattern(regexp="(^[0-9]\\d*$)")
    private int product_id;
    @Pattern(regexp="(^[0-9]\\d*$)")
    private int amount;
    OrderDetailDTO() {
    }
}
