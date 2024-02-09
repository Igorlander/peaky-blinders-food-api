package com.peakyblinders.peakyblindersfood.domain.exceptions;


public class RestaurantNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;


    public RestaurantNotFoundException(String mensage){
        super(mensage);
    }

    public RestaurantNotFoundException(Long restaurantId){
       this(String.format("Código %d de restaurante não existe", restaurantId)) ;
    }


}
