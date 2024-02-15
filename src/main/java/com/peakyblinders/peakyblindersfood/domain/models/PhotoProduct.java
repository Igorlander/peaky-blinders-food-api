package com.peakyblinders.peakyblindersfood.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class PhotoProduct {


    @EqualsAndHashCode.Include
    @Id
    @Column(name = "product_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;
    private String nameFile;
    private String description;
    private String contentType;
    private Long sizeFile;

    public Long getRestauranId(){
        if (getProduct() != null){
            return getProduct().getRestaurant().getId();
        }
        return null;
    }
}
