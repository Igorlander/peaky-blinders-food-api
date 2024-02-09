package com.peakyblinders.peakyblindersfood.domain.exceptions;

public class UserNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;


    public UserNotFoundException(String mensage) {
        super(mensage);
    }

    public UserNotFoundException(Long userId) {
        this(String.format("Este código %d de usúario não existe", userId));

    }

}
