package com.peakyblinders.peakyblindersfood.api.model.dto;


import com.peakyblinders.peakyblindersfood.api.model.input.StateIdInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateModelDTO {
    private String name;

    @Valid
    @NotNull
    private StateIdInput state;

}
