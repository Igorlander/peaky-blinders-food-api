package com.peakyblinders.peakyblindersfood.domain.exceptions;

public class GroupNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public GroupNotFoundException(String mensage) {
        super(mensage);
    }

    public GroupNotFoundException(Long groupId){
        this(String.format("Código %d de grupo não encontrado", groupId)) ;
    }
}
