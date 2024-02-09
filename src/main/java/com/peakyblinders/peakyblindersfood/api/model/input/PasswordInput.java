package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordInput {
    @NotBlank
    private String passwordCurrent;

    @NotBlank
    private String newPassword;
}
