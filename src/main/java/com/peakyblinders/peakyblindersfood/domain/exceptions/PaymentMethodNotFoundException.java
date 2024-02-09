package com.peakyblinders.peakyblindersfood.domain.exceptions;


public class PaymentMethodNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;


    public PaymentMethodNotFoundException(String mensage){
        super(mensage);
    }

    public PaymentMethodNotFoundException(Long methodId){
       this(String.format("Método de pagamento com o código %d não encontrado", methodId)) ;
    }


}
