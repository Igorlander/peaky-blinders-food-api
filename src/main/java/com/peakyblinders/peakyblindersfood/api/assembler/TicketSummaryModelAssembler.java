package com.peakyblinders.peakyblindersfood.api.assembler;

import com.peakyblinders.peakyblindersfood.api.model.dto.TicketModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.dto.TicketSummaryModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.dto.TicketSummarySearchModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketSummaryModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public TicketSummaryModelDTO toModel(Ticket ticket) {
        return modelMapper.map(ticket, TicketSummaryModelDTO.class);
    }

    public List<TicketSummaryModelDTO> toCollectionModel(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> toModel(ticket))
                .collect(Collectors.toList());

    }
}
