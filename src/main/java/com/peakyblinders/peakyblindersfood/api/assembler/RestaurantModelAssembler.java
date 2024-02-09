package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.KitchenModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.dto.RestaurantModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantModelDTO toModel(Restaurant restaurant){
      return modelMapper.map(restaurant,RestaurantModelDTO.class);
    }
    public List<RestaurantModelDTO> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> toModel(restaurant))
                .collect(Collectors.toList());

    }
}
