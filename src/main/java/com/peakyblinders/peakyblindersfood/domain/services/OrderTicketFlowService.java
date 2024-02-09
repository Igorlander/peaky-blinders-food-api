package com.peakyblinders.peakyblindersfood.domain.services;


import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderTicketFlowService {

    @Autowired
    private TicketService ticketService;

    @Transactional
    public void confirmation(String code){
        Ticket ticket = ticketService.seekOrFail(code);
        ticket.confirmation();
    }
    @Transactional
    public void delivery(String code){
        Ticket ticket = ticketService.seekOrFail(code);
       ticket.delivery();
    }
    @Transactional
    public void   canceled(String code){
        Ticket ticket = ticketService.seekOrFail(code);
            ticket.canceled();
            }

}
