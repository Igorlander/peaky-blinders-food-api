package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.input.AddressInput;
import com.peakyblinders.peakyblindersfood.api.model.input.RestaurantInput;
import com.peakyblinders.peakyblindersfood.domain.models.City;
import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;
    public Restaurant toDomainObject(RestaurantInput restaurantInput){
          return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObjtect(RestaurantInput restaurantInput , Restaurant restaurant){
            // Codigo abaixo evita uma exception
           restaurant.setKitchen(new Kitchen());
           if (restaurant.getAddress()!= null){
               restaurant.getAddress().setCity(new City());
           }

     modelMapper.map(restaurantInput, restaurant);
    }
}
