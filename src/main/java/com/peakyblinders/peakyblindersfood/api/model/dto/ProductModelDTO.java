package com.peakyblinders.peakyblindersfood.api.model.dto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductModelDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
}
