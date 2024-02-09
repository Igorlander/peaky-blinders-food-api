package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInput {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
}
