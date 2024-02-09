package com.peakyblinders.peakyblindersfood.domain.exceptions;


public class StateNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;


    public StateNotFoundException(String mensage){
        super(mensage);
    }

    public StateNotFoundException(Long stateId){
       this(String.format("Este código %d de estado não existe", stateId)) ;
    }


}
