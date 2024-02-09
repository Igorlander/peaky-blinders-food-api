package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.ProductModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public ProductModelDTO toModel(Product product) {
        return modelMapper.map(product, ProductModelDTO.class);
    }

    public List<ProductModelDTO> toCollectionModel(List<Product> products) {
        return products.stream()
                .map(product -> toModel(product))
                .collect(Collectors.toList());
    }
}
