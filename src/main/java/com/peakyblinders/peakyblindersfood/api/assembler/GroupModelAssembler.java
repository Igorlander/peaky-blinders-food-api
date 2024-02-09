package com.peakyblinders.peakyblindersfood.api.assembler;



import com.peakyblinders.peakyblindersfood.api.model.dto.GroupModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupModelAssembler {


    @Autowired
    private ModelMapper modelMapper;

    public GroupModelDTO toModel(Group group) {
        return modelMapper.map(group, GroupModelDTO.class);
    }

    public List<GroupModelDTO> toCollectionModel(Collection<Group> groups) {
        return groups.stream()
                .map(group -> toModel(group))
                .collect(Collectors.toList());

    }


}
