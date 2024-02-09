package com.peakyblinders.peakyblindersfood.api.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionModelDTO {

    private Long id;
    private String name;
    private String  description;

}
