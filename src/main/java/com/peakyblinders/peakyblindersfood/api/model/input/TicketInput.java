package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketInput {

    @Valid
    @NotNull
    private RestaurantIdInput restaurant;

    @Valid
    @NotNull
    private AddressInput addressDelivery;
    @Valid
    @NotNull
    private PaymentMethodIdInput paymentMethod;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemOrderTicketInput> items;
}
