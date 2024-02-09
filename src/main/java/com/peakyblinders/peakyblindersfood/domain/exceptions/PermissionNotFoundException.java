package com.peakyblinders.peakyblindersfood.domain.exceptions;


public class PermissionNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;


    public PermissionNotFoundException(String mensage){
        super(mensage);
    }

    public PermissionNotFoundException(Long permissionId){
       this(String.format("Permissão com o código %d não foi encontrado", permissionId));
    }


}
