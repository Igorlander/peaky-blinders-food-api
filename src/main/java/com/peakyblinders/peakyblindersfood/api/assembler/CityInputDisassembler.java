package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.input.CityInput;
import com.peakyblinders.peakyblindersfood.domain.models.City;
import com.peakyblinders.peakyblindersfood.domain.models.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    public City toDomainObject(CityInput cityInput){
        return modelMapper.map(cityInput, City.class);
    }
    public void copyToDomainObjtect(CityInput cityInput , City city){
        // Código abaixo evita uma exception
         city.setState(new State());
        modelMapper.map(cityInput, city);
    }

}
