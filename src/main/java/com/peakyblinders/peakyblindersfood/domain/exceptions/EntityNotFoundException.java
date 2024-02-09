package com.peakyblinders.peakyblindersfood.domain.exceptions;


//@ResponseStatus(value = HttpStatus.NOT_FOUND )//, reason = "Entidade não encontrada" )
public abstract class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;


    public EntityNotFoundException(String mensage){
        super(mensage);
    }

}
