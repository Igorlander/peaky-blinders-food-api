package com.peakyblinders.peakyblindersfood.domain.models;

import com.peakyblinders.peakyblindersfood.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(name = "shipping_Fee", nullable = false)
    private BigDecimal shippingFee;


    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    private Boolean active = Boolean.TRUE;
    @Column(name = "opening")
    private Boolean open = Boolean.TRUE;

    @Embedded
    private Address address;
    @CreationTimestamp
    @Column(name = "registration_date", nullable = false)
    private OffsetDateTime registrationDate;

    @UpdateTimestamp
    @Column(name= "update_date" , nullable = false)
    private OffsetDateTime updateDate;

    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
    inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private Set<PaymentMethod> paymentsMethods = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurant_user_responsible",
                joinColumns = @JoinColumn(name = "restaurant_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> responsibles = new HashSet<>();


    public boolean removeResponsible(User user){
        return getResponsibles().remove(user);
    }
    public boolean addResponsible(User user){
        return getResponsibles().add(user);
    }
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    public void open(){
        setOpen(true);
    }

    public void close(){
        setOpen(false);}


    public void active(){
        setActive(true);
    }
    public void inactive(){
        setActive(false);
    }

    public boolean disassociatePaymentMethod(PaymentMethod paymentMethod){
        return getPaymentsMethods().remove(paymentMethod);
    }
    public boolean associatePaymentMethod(PaymentMethod paymentMethod){
        return getPaymentsMethods().add(paymentMethod);
    }

    public boolean acceptedPaymentMethod(PaymentMethod paymentMethod){
        return getPaymentsMethods().contains(paymentMethod);
    }
    public boolean doNotAcceptdPaymentMethod(PaymentMethod paymentMethod){
        return !acceptedPaymentMethod(paymentMethod);
    }
}
