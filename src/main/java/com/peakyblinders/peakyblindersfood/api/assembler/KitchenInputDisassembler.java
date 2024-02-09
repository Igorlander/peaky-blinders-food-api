package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.input.KitchenInput;
import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    public Kitchen toDomainObject(KitchenInput kitchenInput){
        return modelMapper.map(kitchenInput, Kitchen.class);
    }

    public void copyToDomainObjtect(KitchenInput kitchenInput , Kitchen kitchen){
        modelMapper.map(kitchenInput, kitchen);
    }


}
