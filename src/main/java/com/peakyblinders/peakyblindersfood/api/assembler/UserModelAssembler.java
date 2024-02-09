package com.peakyblinders.peakyblindersfood.api.assembler;


import com.peakyblinders.peakyblindersfood.api.model.dto.UserModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public UserModelDTO toModel(User user) {
        return modelMapper.map(user, UserModelDTO.class);
    }

    public List<UserModelDTO> toCollectionModel(Collection<User> users) {
        return users.stream()
                .map(user -> toModel(user))
                .collect(Collectors.toList());

    }
}
