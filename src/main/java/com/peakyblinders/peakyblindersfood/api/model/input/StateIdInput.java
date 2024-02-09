package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StateIdInput {
    @NotNull
    private Long id;
}
