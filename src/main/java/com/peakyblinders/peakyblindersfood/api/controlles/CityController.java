package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.assembler.CityModelAssembler;
import com.peakyblinders.peakyblindersfood.api.assembler.CityInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.CityModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.CityInput;
import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.StateNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.City;
import com.peakyblinders.peakyblindersfood.domain.repositories.CityRepository;
import com.peakyblinders.peakyblindersfood.domain.repositories.StateRepository;
import com.peakyblinders.peakyblindersfood.domain.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/citys")
public class CityController {

    @Autowired
    private CityRepository cityRepository;


    @Autowired
    private CityService cityService;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityModelAssembler cityModelAssembler;

    @Autowired
    private CityInputDisassembler cityInputDisassembler;


    @GetMapping
    public List<CityModelDTO> list() {
        List<City> citysTotal =cityRepository.findAll();
        return cityModelAssembler.toCollectionModel(citysTotal);
    }

    @GetMapping("/{cityId}")
    public CityModelDTO searchById(@PathVariable("cityId") Long cityId) {
        City city = cityService.seekOrFail(cityId);
        return cityModelAssembler.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModelDTO save(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityInputDisassembler.toDomainObject(cityInput);
            city = cityService.save(city);
            return cityModelAssembler.toModel(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public CityModelDTO update(@PathVariable Long cityId,
                               @RequestBody @Valid CityInput cityInput) {
        try {
            City cityCurrent = cityService.seekOrFail(cityId);
            cityInputDisassembler.copyToDomainObjtect(cityInput, cityCurrent);
            cityCurrent = cityService.save(cityCurrent);
            return cityModelAssembler.toModel(cityCurrent);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {cityService.remove(cityId);}


}