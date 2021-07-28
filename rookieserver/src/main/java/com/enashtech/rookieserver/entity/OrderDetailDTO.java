package com.enashtech.rookieserver.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class OrderDetailDTO {
    @Positive
    @NotNull
    private int product_id;

    @Positive
    @NotNull
    private int amount;

    @PositiveOrZero
    @NotNull
    private int size_id;

    @PositiveOrZero
    @NotNull
    private int color_id;

    OrderDetailDTO() {
    }
}
