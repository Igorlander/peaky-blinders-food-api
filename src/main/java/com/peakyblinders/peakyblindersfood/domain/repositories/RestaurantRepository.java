package com.peakyblinders.peakyblindersfood.domain.repositories;

import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long> ,RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    List<Restaurant> find(String name, BigDecimal shippingFeeInitial , BigDecimal shippingFeeFinal );

   }
