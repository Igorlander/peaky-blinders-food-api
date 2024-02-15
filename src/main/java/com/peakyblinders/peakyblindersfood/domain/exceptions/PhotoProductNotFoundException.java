package com.peakyblinders.peakyblindersfood.domain.exceptions;

public class PhotoProductNotFoundException extends EntityNotFoundException {
    public PhotoProductNotFoundException(String mensage) {
        super(mensage);
    }
    public PhotoProductNotFoundException(Long restaurantId, Long productId){
        this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante " +
                "de código %d.", productId , restaurantId));
    }
}
