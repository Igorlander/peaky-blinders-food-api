package com.peakyblinders.peakyblindersfood.domain.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Address {


    @Column(name = "address_postal_code")
    private String postalCode;
    @Column(name = "address_public_place")
    private String publicPlace; // tradução -> logradouro
    @Column(name = "address_number")
    private String number;
    @Column(name = "address_complement")
    private String complement;
    @Column(name = "address_neighborhood")
    private String  neighborhood;   // tradução -> bairro


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_city_id")
    private City city;




}
