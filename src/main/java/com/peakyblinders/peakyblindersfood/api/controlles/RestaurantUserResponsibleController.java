package com.peakyblinders.peakyblindersfood.api.controlles;


import com.peakyblinders.peakyblindersfood.api.assembler.UserModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.UserModelDTO;
import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import com.peakyblinders.peakyblindersfood.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants/{restaurantId}/responsibles")
public class RestaurantUserResponsibleController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @GetMapping
    public List<UserModelDTO> list(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.seekOrFail(restaurantId);
        return userModelAssembler.toCollectionModel(restaurant.getResponsibles());
    }
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }

}
