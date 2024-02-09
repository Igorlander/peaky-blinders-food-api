package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.KitchenModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class KitchenModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenModelDTO toModel(Kitchen kitchen) {
        return modelMapper.map(kitchen, KitchenModelDTO.class);
    }

    public List<KitchenModelDTO> toCollectionModel(List<Kitchen> kitchens) {
        return kitchens.stream()
                .map(kitchen -> toModel(kitchen))
                .collect(Collectors.toList());

    }
}
