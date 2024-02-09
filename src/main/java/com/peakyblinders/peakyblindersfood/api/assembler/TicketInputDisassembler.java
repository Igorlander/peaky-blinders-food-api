package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.input.TicketInput;
import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Ticket toDomainObject(TicketInput ticketInput){
        return modelMapper.map(ticketInput,Ticket.class);
    }
    public void copyToDomainObject(TicketInput ticketInput, Ticket ticket ){
        modelMapper.map(ticketInput, ticket);
    }


}
