package com.peakyblinders.peakyblindersfood.domain.services;


import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.PaymentMethodNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.PaymentMethod;
import com.peakyblinders.peakyblindersfood.domain.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentMethodService {

    public static final String PAYMENT_METHOD_IN_USE
            = "Método de pagamento com o código %d não pode ser removida, pois esta em uso. ";
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    public void remove(Long paymentMethodId){
        try {
            paymentMethodRepository.deleteById(paymentMethodId);
            paymentMethodRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new PaymentMethodNotFoundException(paymentMethodId);
        }
        catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format(PAYMENT_METHOD_IN_USE, paymentMethodId));
        }

        }

    public PaymentMethod seekOrFail(Long methodId){
        return paymentMethodRepository.findById(methodId).orElseThrow(() -> new PaymentMethodNotFoundException(
                methodId));
    }

    }








