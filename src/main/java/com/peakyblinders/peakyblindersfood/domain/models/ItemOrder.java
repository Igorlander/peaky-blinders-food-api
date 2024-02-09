package com.peakyblinders.peakyblindersfood.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemOrder {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer amount; // quantidade
    private String observation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public void calculatePriceTotal(){
        BigDecimal unitPrice = this.unitPrice;
        Integer amount = this.amount;
        if (unitPrice == null){
            unitPrice = BigDecimal.ZERO;
        }
        if (amount == null){
            amount = 0;
        }
        this.setTotalPrice(unitPrice.multiply(new BigDecimal(amount)));
    }

}
