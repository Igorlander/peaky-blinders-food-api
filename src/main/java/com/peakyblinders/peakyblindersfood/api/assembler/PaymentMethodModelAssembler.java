package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.PaymentMethodModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public PaymentMethodModelDTO toModel(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodModelDTO.class);
    }

    public List<PaymentMethodModelDTO> toCollectionModel(Collection<PaymentMethod> paymentMethods) {
        return paymentMethods.stream().map(paymentMethod -> toModel(paymentMethod)).collect(Collectors.toList());

    }
}
