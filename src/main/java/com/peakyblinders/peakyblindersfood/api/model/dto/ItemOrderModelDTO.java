package com.peakyblinders.peakyblindersfood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemOrderModelDTO {
    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer amount; // quantidade
    private String observation;
}
