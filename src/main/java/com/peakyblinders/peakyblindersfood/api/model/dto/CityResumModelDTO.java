package com.peakyblinders.peakyblindersfood.api.model.dto;

import com.peakyblinders.peakyblindersfood.domain.models.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResumModelDTO {

    private Long id;
    private String name;
    private String state;


}
