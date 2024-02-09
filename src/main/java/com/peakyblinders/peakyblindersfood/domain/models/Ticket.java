package com.peakyblinders.peakyblindersfood.domain.models;


import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ticket {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Column(name = "total_price")
    private BigDecimal subTotal;
    private BigDecimal shippingFee;
    private BigDecimal amount; // Valor total


    @Embedded
    private Address  deliveryAddress;

    @Enumerated(EnumType.STRING)
    private StatusTicket status = StatusTicket.CRIADO;
    @CreationTimestamp
    private OffsetDateTime creationDate;

    private OffsetDateTime confirmDate;
    private OffsetDateTime cancellationDate;

    private OffsetDateTime deliveryDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id",nullable = false)
    private User client;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<ItemOrder> items = new ArrayList<>();

    public void calculateTotalValue(){
        getItems().forEach(ItemOrder::calculatePriceTotal);
        this.subTotal= getItems().stream()
                .map(itemOrder -> itemOrder.getTotalPrice())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        this.amount = this.subTotal.add(this.shippingFee);
    }

    public void setShipping(){
        setShippingFee(getRestaurant().getShippingFee());
    }
    public void AssignOrderToItems(){
        getItems().forEach(itemOrder -> itemOrder.setTicket(this));
    }

    public void confirmation(){
        setStatus(StatusTicket.CONFIRMADO);
        setConfirmDate(OffsetDateTime.now());
    }

    public void delivery(){
        setStatus(StatusTicket.ENTREGUE);
        setDeliveryDate(OffsetDateTime.now());
    }

    public void canceled(){
        setStatus(StatusTicket.CANCELADO);
        setCancellationDate(OffsetDateTime.now());
    }
    private void setStatus(StatusTicket newStatusTicket){
        if(getStatus().cannotChangeTo(newStatusTicket)){
            throw new BusinessException(String.format("Status do ticket %s não pode ser alterado " +
                            "de %s para %s.", getCode() , getStatus().getDescription()
                    ,newStatusTicket.getDescription()
            ));
        }
            this.status = newStatusTicket;
    }

    @PrePersist
    private void   generateCode(){
        setCode(UUID.randomUUID().toString());
    }
}


