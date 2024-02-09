package com.peakyblinders.peakyblindersfood.domain.services;


import com.peakyblinders.peakyblindersfood.domain.exceptions.CityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.models.City;
import com.peakyblinders.peakyblindersfood.domain.models.State;
import com.peakyblinders.peakyblindersfood.domain.repositories.CityRepository;
import com.peakyblinders.peakyblindersfood.domain.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {


    public static final String
            CITY_OF_CODE_CAN_NOT_BE_REMOVED_BECAUSE_IT_IS_IN_USE =
            "Cidade de código %d não pode ser removida, pois esta em uso";
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @Transactional
    public City save(City city){
        Long stateId = city.getState().getId();
        State state = stateService.seekOrFail(stateId);
        city.setState(state);
        return cityRepository.save(city);
    }

    @Transactional
    public void remove(Long cityId){
        try {
            cityRepository.deleteById(cityId);
            cityRepository.flush();
        }catch (EmptyResultDataAccessException e ) {
            throw new CityNotFoundException(cityId);
        }
        catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format(CITY_OF_CODE_CAN_NOT_BE_REMOVED_BECAUSE_IT_IS_IN_USE,
                    cityId));
        }
    }

    public City seekOrFail(Long cityId){
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));

    }

}
