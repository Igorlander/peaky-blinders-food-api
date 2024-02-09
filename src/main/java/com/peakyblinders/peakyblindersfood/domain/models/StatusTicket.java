package com.peakyblinders.peakyblindersfood.domain.models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StatusTicket {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado" , CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String description ;

    private List<StatusTicket> statusTicketsPrevious;


    StatusTicket(String description , StatusTicket... statusTicketsPrevious){
        this.description = description;
        this.statusTicketsPrevious = Arrays.asList(statusTicketsPrevious);

    }
    public String getDescription(){
        return this.description;
    }

    public boolean cannotChangeTo(StatusTicket newStatus){
        return !newStatus.statusTicketsPrevious.contains(this);
    }

}
