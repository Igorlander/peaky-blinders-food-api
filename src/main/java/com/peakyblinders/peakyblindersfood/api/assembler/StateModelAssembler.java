package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.CityModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.dto.StateModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.City;
import com.peakyblinders.peakyblindersfood.domain.models.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StateModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public StateModelDTO toModel(State state) {
        return modelMapper.map(state, StateModelDTO.class);
    }

    public List<StateModelDTO> toCollectionModel(List<State> states) {
        return states.stream()
                .map(state -> toModel(state))
                .collect(Collectors.toList());

    }
}
