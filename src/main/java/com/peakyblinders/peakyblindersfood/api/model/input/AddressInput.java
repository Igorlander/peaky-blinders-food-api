package com.peakyblinders.peakyblindersfood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    private String postalCode;
    @NotBlank
    private String publicPlace; // tradução -> logradouro
    @NotBlank
    private String number;
    private String complement;
    @NotBlank
    private
    String  neighborhood;   // tradução -> bairro
    @Valid
    @NotNull
    private CityIdInput city;

}
