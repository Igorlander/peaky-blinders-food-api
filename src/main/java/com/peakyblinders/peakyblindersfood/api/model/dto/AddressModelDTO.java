package com.peakyblinders.peakyblindersfood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModelDTO {

    private String postalCode;
    private String publicPlace; // tradução -> logradouro
    private String number;
    private String complement;
    private String  neighborhood;   // tradução -> bairro
    private CityResumModelDTO city;

}
