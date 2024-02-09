package com.peakyblinders.peakyblindersfood.api.model.input;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenInput {

    @NotBlank
    private String name;
}
