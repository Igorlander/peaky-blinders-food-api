package com.peakyblinders.peakyblindersfood.domain.services;


import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.StateNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.State;
import com.peakyblinders.peakyblindersfood.domain.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {

    public static final String CODE_STATE_CAN_NOT_BE_REMOVED_BECAUSE_IT_IS_IN_USE =
            "Estado de código %d não pode ser removida, pois esta em uso";
    @Autowired
    private StateRepository stateRepository;

    @Transactional
    public State save(State state){
        return stateRepository.save(state);
    }

    @Transactional
    public void remove(Long stateId){
        try {
            State state = seekOrFail(stateId);
            stateRepository.deleteById(stateId);
            stateRepository.flush();
        }catch (EmptyResultDataAccessException e ) {
            throw new StateNotFoundException(stateId);
        }
        catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format(CODE_STATE_CAN_NOT_BE_REMOVED_BECAUSE_IT_IS_IN_USE, stateId));
        }
    }
    public State seekOrFail(Long stateId){
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));

    }
}
