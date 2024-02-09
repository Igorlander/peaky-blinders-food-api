package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.input.PaymentMethodInput;
import com.peakyblinders.peakyblindersfood.domain.models.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;
    public PaymentMethod toDomainObject(PaymentMethodInput paymentMethodInput){
        return modelMapper.map(paymentMethodInput, PaymentMethod.class);
    }
    public void copyToDomainObjtect(PaymentMethodInput  paymentMethodInput, PaymentMethod paymentMethod){
        modelMapper.map(paymentMethodInput, paymentMethod);
    }

}
