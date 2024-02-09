package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.PermissionModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionModelDTO toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModelDTO.class);
    }

    public List<PermissionModelDTO> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream().map(permission -> toModel(permission)).collect(Collectors.toList());

    }

}
