package com.peakyblinders.peakyblindersfood.domain.exceptions;

//@ResponseStatus(value = HttpStatus.CONFLICT )
public class EntityInUseException extends BusinessException{

    private static final long serialVersionUID = 1L;

    public EntityInUseException(String mensage){
        super(mensage);
    }

}
