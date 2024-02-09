package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithPasswordInput extends UserInput{

    @NotBlank
    private String password;
}
