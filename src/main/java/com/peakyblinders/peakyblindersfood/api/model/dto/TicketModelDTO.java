package com.peakyblinders.peakyblindersfood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class TicketModelDTO {


    private String code;
    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal amount; // Valor total

    private String status ;
    private OffsetDateTime creationDate;
    private OffsetDateTime confirmDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;

    private RestaurantSummaryModelDTO restaurant;
    private UserModelDTO client;
    private PaymentMethodModelDTO paymentMethod;
    private AddressModelDTO address;
    private List<ItemOrderModelDTO> items;


}
