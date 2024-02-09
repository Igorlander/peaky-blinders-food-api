package com.peakyblinders.peakyblindersfood.api.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModelDTO {

    private Long id;
    private String name;
    private String email;

}
