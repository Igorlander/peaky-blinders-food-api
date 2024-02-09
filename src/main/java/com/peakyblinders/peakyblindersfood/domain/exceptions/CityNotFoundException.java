package com.peakyblinders.peakyblindersfood.domain.exceptions;


public class CityNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;


    public CityNotFoundException(String mensage){
        super(mensage);
    }

    public CityNotFoundException(Long cityId){
       this(String.format("Código %d de cidade não encontrada", cityId)) ;
    }


}
