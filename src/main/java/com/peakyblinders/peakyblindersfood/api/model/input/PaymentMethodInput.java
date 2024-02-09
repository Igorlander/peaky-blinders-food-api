package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodInput {

    @NotBlank
    private String description;


}
