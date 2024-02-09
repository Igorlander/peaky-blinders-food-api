package com.peakyblinders.peakyblindersfood.domain.exceptions;

public class ProductNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException (String mesage){
        super(mesage);
    }

    public ProductNotFoundException(Long restaurantId, Long productId){
        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d",
                productId, restaurantId));
    }

}
