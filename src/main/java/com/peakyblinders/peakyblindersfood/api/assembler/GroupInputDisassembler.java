package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.input.GroupInput;
import com.peakyblinders.peakyblindersfood.domain.models.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    public Group toDomainObject(GroupInput groupInput){
        return modelMapper
                .map(groupInput, Group.class);
    }
    public void copyToDomainObjtect(GroupInput groupInput , Group group){
        // Código abaixo evita uma exception
        //city.setState(new State());
        modelMapper.map(groupInput, group);
    }

}
