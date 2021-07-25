package com.enashtech.rookieserver.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class OrderDetailDTO {
    @Positive
    @NotNull
    private int product_id;

    @Positive
    @NotNull
    private int amount;

    @Positive
    @NotNull
    private int size_id;

    @Positive
    @NotNull
    private int color_id;

    OrderDetailDTO() {
    }
}
