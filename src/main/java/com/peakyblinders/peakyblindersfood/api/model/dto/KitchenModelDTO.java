package com.peakyblinders.peakyblindersfood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.peakyblinders.peakyblindersfood.api.model.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModelDTO {
    @JsonView(RestaurantView.Summary.class)
    private long id;
    @JsonView(RestaurantView.Summary.class)
    private String name;
}
