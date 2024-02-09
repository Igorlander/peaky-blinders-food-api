package com.peakyblinders.peakyblindersfood.domain.exceptions;


public class KitchenNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;


    public KitchenNotFoundException(String mensage){
        super(mensage);
    }

    public KitchenNotFoundException(Long kitchenId){
       this(String.format("Código %d de cozinha não encontrada", kitchenId)) ;
    }


}
