package com.peakyblinders.peakyblindersfood.core.modelmapper;


import com.peakyblinders.peakyblindersfood.api.model.dto.AddressModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.ItemOrderTicketInput;
import com.peakyblinders.peakyblindersfood.domain.models.Address;
import com.peakyblinders.peakyblindersfood.domain.models.ItemOrder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemOrderTicketInput.class, ItemOrder.class)
                .addMappings(mapper -> mapper.skip(ItemOrder::setId));

        var addressToAddressModelTypeMap =  modelMapper.createTypeMap(Address.class, AddressModelDTO.class);

        addressToAddressModelTypeMap.<String>addMapping(
                src -> src.getCity().getState().getName(),
                (dest, value) -> dest.getCity().setState(value)
        );
        return modelMapper;
    }
}
