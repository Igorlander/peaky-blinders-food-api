package com.peakyblinders.peakyblindersfood.api.controlles;

import com.fasterxml.jackson.annotation.JsonView;
import com.peakyblinders.peakyblindersfood.api.assembler.RestaurantInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.assembler.RestaurantModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.RestaurantModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.RestaurantInput;
import com.peakyblinders.peakyblindersfood.api.model.view.RestaurantView;
import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.RestaurantNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.UserNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import com.peakyblinders.peakyblindersfood.domain.repositories.RestaurantRepository;
import com.peakyblinders.peakyblindersfood.domain.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

    @JsonView(RestaurantView.Summary.class)
    @GetMapping
    public List<RestaurantModelDTO> list() {
        return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
    }

    @JsonView(RestaurantView.NameOnly.class)
    @GetMapping(params = "project=name-only")
    public List<RestaurantModelDTO> listNameOnly() {
        return list();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantModelDTO searchById(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.seekOrFail(restaurantId);
        return restaurantModelAssembler.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModelDTO save(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
            return restaurantModelAssembler.toModel(restaurantService.save(restaurant));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public RestaurantModelDTO update(@PathVariable Long restaurantId,
                                     @RequestBody @Valid RestaurantInput restaurantInput) {

        Restaurant restaurantCurrent = restaurantService.seekOrFail(restaurantId);
        try {
           restaurantInputDisassembler.copyToDomainObjtect(restaurantInput,restaurantCurrent);

            return restaurantModelAssembler.toModel(restaurantService.save(restaurantCurrent));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }

    }
    @PutMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void active(@PathVariable Long restaurantId){
        restaurantService.active(restaurantId);
    }

    @DeleteMapping("/{restaurantId}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactive(@PathVariable Long restaurantId){
        restaurantService.inactive(restaurantId);
    }
    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activeMultiple(@RequestBody List<Long> restauranIds){
        restaurantService.active(restauranIds);
    }

    @DeleteMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactiveMultiple(@RequestBody List<Long> restauranIds){
        restaurantService.inactive(restauranIds);
    }
    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long restaurantId){restaurantService.open(restaurantId);}

    @PutMapping("/{restaurantId}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long restaurantId){restaurantService.close(restaurantId);}


    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId) {
        try {
            restaurantService.remove(restaurantId);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /// TESTES

    @GetMapping("/restaurant/for-name-and-freight")
    public List<Restaurant> restaurantForNameAndFreight(String name, BigDecimal shippingFeeInitial,
                                                        BigDecimal shippingFeeFinal) {
        return restaurantRepository.find(name, shippingFeeInitial, shippingFeeFinal);
    }

    @GetMapping("/restaurant/freight-free")
    public List<Restaurant> forFreightFree(String name) {
        return restaurantRepository.findWithfreighFree(name);
    }

    @GetMapping("/restaurant/first")
    public Optional<Restaurant> restaurantFisrt() {
        return restaurantRepository.findFirst();
    }

}
