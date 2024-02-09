package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOrderTicketInput {

    @NotNull
    private Long productId;
    @NotNull
    @PositiveOrZero
    private Integer amount;
    private String observation;

}
