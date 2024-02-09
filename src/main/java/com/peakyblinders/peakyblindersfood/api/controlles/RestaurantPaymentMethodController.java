package com.peakyblinders.peakyblindersfood.api.controlles;


import com.peakyblinders.peakyblindersfood.api.assembler.PaymentMethodModelAssembler;
import com.peakyblinders.peakyblindersfood.api.assembler.RestaurantInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.assembler.RestaurantModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.PaymentMethodModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.dto.RestaurantModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.PaymentMethod;
import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import com.peakyblinders.peakyblindersfood.domain.repositories.RestaurantRepository;
import com.peakyblinders.peakyblindersfood.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/payment-method")
public class RestaurantPaymentMethodController {


    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

    @Autowired
    private PaymentMethodModelAssembler methodModelAssembler;


    @GetMapping
    public List<PaymentMethodModelDTO> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.seekOrFail(restaurantId);
        return methodModelAssembler.toCollectionModel(restaurant.getPaymentsMethods());
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  disassociate( @PathVariable Long restaurantId, @PathVariable Long paymentMethodId){
                restaurantService.disassociatePaymentMethod(restaurantId, paymentMethodId);
    }
    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  associate( @PathVariable Long restaurantId, @PathVariable Long paymentMethodId){
        restaurantService.associatePaymentMethod(restaurantId, paymentMethodId);
    }


}
