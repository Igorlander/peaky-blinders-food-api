package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.CityModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CityModelDTO toModel(City city) {
        return modelMapper.map(city, CityModelDTO.class);
    }

    public List<CityModelDTO> toCollectionModel(List<City> citys) {
        return citys.stream()
                .map(city -> toModel(city))
                .collect(Collectors.toList());

    }
}
