package com.peakyblinders.peakyblindersfood.domain.services;



import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.KitchenNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import com.peakyblinders.peakyblindersfood.domain.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KitchenService {



    public static final String KITCHEN_IN_USE
            = "Cozinha de código %d não pode ser removida, pois esta em uso";


    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public Kitchen save(Kitchen kitchen){
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void remove(Long kitchenId){

        try {
              kitchenRepository.deleteById(kitchenId);
              kitchenRepository.flush();
        }catch (EmptyResultDataAccessException  e) {
            throw new KitchenNotFoundException(kitchenId);
        }
        catch (DataIntegrityViolationException e){
           throw new EntityInUseException(String.format(KITCHEN_IN_USE, kitchenId));
        }
    }

    public Kitchen seekOrFail(Long kitchenId){
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
    }
}
