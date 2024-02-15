package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.PhotoProductModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.PhotoProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoProducModelAssembler {


    @Autowired
    private ModelMapper modelMapper;

    public PhotoProductModelDTO toModel( PhotoProduct  photoProduct) {
        return modelMapper.map(photoProduct, PhotoProductModelDTO.class);
    }

}

