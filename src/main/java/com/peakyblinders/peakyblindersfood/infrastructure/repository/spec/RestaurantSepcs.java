package com.peakyblinders.peakyblindersfood.infrastructure.repository.spec;

import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSepcs {

    public static Specification<Restaurant>  withfreighFree(){
        return ((root, query, builder) ->
                builder.equal(root.get("shippingFee"), BigDecimal.ZERO));
    }

    public static Specification<Restaurant> withNameSimilar(String name){
        return (root, query, builder) ->
                builder.like(root.get("name"), "%" + name + "%");
    }

}
