package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityInUseException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.RestaurantNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.*;
import com.peakyblinders.peakyblindersfood.domain.repositories.KitchenRepository;
import com.peakyblinders.peakyblindersfood.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    public static final String RESTAURANT_OF_CODE_CAN_NOT_BE_REMOTVED_BECAUSE_IT_IS_IN_USE
            = "Restaurante de código %d não pode ser removido, pois esta em uso";
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private CityService cityService;
    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private UserService userService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Kitchen kitchen = kitchenService.seekOrFail(kitchenId);

        City city = cityService.seekOrFail(cityId);

        restaurant.getAddress().setCity(city);
        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }


    @Transactional
    public void remove(Long restaurantId) {
        try {
            restaurantRepository.deleteById(restaurantId);
            restaurantRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestaurantNotFoundException(restaurantId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(RESTAURANT_OF_CODE_CAN_NOT_BE_REMOTVED_BECAUSE_IT_IS_IN_USE,
                    restaurantId));
        }
    }

    @Transactional
    public void open(Long restaurantId) {
        Restaurant restaurantCurrent = seekOrFail(restaurantId);
        restaurantCurrent.open();
    }

    @Transactional
    public void close(Long restaurentId) {
        Restaurant restaurantCurrent = seekOrFail(restaurentId);
        restaurantCurrent.close();
    }

    @Transactional
    public void active(Long restaurantId) {
        Restaurant restaurantCurrent = seekOrFail(restaurantId);
        restaurantCurrent.active();
    }

    @Transactional
    public void inactive(Long restaurantId) {
        Restaurant restaurantCurrent = seekOrFail(restaurantId);
        restaurantCurrent.inactive();
    }

    @Transactional
    public void active(List<Long> restauranIds) {
        try {
            restauranIds.forEach(this::active);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void inactive(List<Long> restauranIds){
       try {
           restauranIds.forEach(this::inactive);
       }catch (RestaurantNotFoundException e){
           throw new BusinessException(e.getMessage(),e );
       }

    }


    @Transactional
    public void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = seekOrFail(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.seekOrFail(paymentMethodId);
        restaurant.getPaymentsMethods().remove(paymentMethod);
    }

    @Transactional
    public void associatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = seekOrFail(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.seekOrFail(paymentMethodId);
        restaurant.getPaymentsMethods().add(paymentMethod);
    }

    @Transactional
    public void disassociateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = seekOrFail(restaurantId);
        User user = userService.seekOrFail(userId);
        restaurant.getResponsibles().remove(user);
    }

    @Transactional
    public void associateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = seekOrFail(restaurantId);
        User user = userService.seekOrFail(userId);
        restaurant.getResponsibles().add(user);
    }

    public Restaurant seekOrFail(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(
                restaurantId));
    }

}
