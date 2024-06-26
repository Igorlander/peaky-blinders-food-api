package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantInput {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal shippingFee;

    @Valid
    @NotNull
    private KitchenIdInput kitchen;

    @Valid
    @NotNull
    private AddressInput address;
}
