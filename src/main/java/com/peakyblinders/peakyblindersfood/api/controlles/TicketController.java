package com.peakyblinders.peakyblindersfood.api.controlles;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.ImmutableMap;
import com.peakyblinders.peakyblindersfood.api.assembler.TicketInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.assembler.TicketModelAssembler;
import com.peakyblinders.peakyblindersfood.api.assembler.TicketSummaryModelAssembler;
import com.peakyblinders.peakyblindersfood.api.assembler.TicketSummarySearchModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.TicketModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.dto.TicketSummaryModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.dto.TicketSummarySearchModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.TicketInput;
import com.peakyblinders.peakyblindersfood.core.data.PageableTranslator;
import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import com.peakyblinders.peakyblindersfood.domain.models.User;
import com.peakyblinders.peakyblindersfood.domain.repositories.TicketRepository;
import com.peakyblinders.peakyblindersfood.domain.filter.TicketOrderFilter;
import com.peakyblinders.peakyblindersfood.domain.services.TicketService;
import com.peakyblinders.peakyblindersfood.infrastructure.repository.spec.TicketSepcs;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketModelAssembler ticketModelAssembler;
    @Autowired
    private TicketSummaryModelAssembler ticketSummaryModelAssembler;

    @Autowired
    private TicketSummarySearchModelAssembler ticketSummarySearchModelAssembler;

    @Autowired
    private TicketInputDisassembler ticketInputDisassembler;

    @GetMapping
    public MappingJacksonValue list(@RequestParam(required = false) String fields) {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketSummaryModelDTO> ticketModel = ticketSummaryModelAssembler.toCollectionModel(tickets);
        MappingJacksonValue ticketWrapper = new MappingJacksonValue(ticketModel);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("ticketFilter",
                SimpleBeanPropertyFilter.serializeAll());
        if (StringUtils.isNotBlank(fields)) {
            filterProvider.addFilter("ticketFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
        }

        ticketWrapper.setFilters(filterProvider);
        return ticketWrapper;
    }

    @GetMapping("/search")
    public Page<TicketSummarySearchModelDTO> search(TicketOrderFilter filter,
                                                    @PageableDefault(size = 10) Pageable pageable) {

        pageable = translatePageable(pageable);

        Page<Ticket> ticketsPages = ticketRepository.findAll(TicketSepcs.usingFilter(filter),pageable);

        List<TicketSummarySearchModelDTO> ticketSummarySearchModel = ticketSummarySearchModelAssembler
                .toCollectionModel(ticketsPages.getContent());

        Page<TicketSummarySearchModelDTO> ticketsSummaryPages = new PageImpl<>(ticketSummarySearchModel,
                pageable,ticketsPages.getTotalElements());
        return ticketsSummaryPages;
    }

    @GetMapping("/{code}")
    public TicketModelDTO searchById(@PathVariable String code) {
        Ticket ticket = ticketService.seekOrFail(code);
        return ticketModelAssembler.toModel(ticket);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketModelDTO save(@Valid @RequestBody TicketInput ticketInput) {
        try {
            Ticket newTicket = ticketInputDisassembler.toDomainObject(ticketInput);
            // usuario autenticado
            newTicket.setClient(new User());
            newTicket.getClient().setId(1L);

            newTicket = ticketService.issue(newTicket);
            return ticketModelAssembler.toModel(newTicket);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private Pageable translatePageable(Pageable apiPageable){
        var mapi = ImmutableMap.of(
            "code" ,"code",
                "amount","amount",
                "subTotal", "subTotal",
                    "name","name",
                "status", "status",
                "creationDate", "creationDate",
                "restaurant", "restaurant",
                "client", "client"
        );

        return PageableTranslator.trranslate(apiPageable , mapi);
    }
}
