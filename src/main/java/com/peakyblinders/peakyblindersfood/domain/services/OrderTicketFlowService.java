package com.peakyblinders.peakyblindersfood.domain.services;


import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderTicketFlowService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private SendingEmailService sendingEmail;

    @Transactional
    public void confirmation(String code){
        Ticket ticket = ticketService.seekOrFail(code);
        ticket.confirmation();
        var message = SendingEmailService.Message.builder()
                .subject(ticket.getRestaurant().getName() + " - Pedido confirmado")
                .body("O pedido de código <stron>" +
                        ticket.getCode() + " </strong> foi confirmado! ")
                .recipient(ticket.getClient().getEmail())
                .build();

        sendingEmail.toSend(message);

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
