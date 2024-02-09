package com.peakyblinders.peakyblindersfood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@JsonFilter("ticketFilter")
@Getter
@Setter
public class TicketSummaryModelDTO {

    private String code;
    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal amount; // Valor total
    private String status ;
    private OffsetDateTime creationDate;
    private RestaurantSummaryModelDTO restaurant;
    private UserModelDTO client;


}
