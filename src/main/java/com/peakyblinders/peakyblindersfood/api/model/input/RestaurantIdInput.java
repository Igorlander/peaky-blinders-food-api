package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantIdInput {
    @NotNull
    private Long id;
}
