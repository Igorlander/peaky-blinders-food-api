package com.peakyblinders.peakyblindersfood.domain.exceptions;

public class TicketNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;


    public TicketNotFoundException(String code){
        super(String.format("Não existe um pedido com código %s.", code));
     }

}
