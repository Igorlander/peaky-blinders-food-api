package com.peakyblinders.peakyblindersfood.domain.repositories;

import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

    List<Restaurant> findWithfreighFree(String name);
}
