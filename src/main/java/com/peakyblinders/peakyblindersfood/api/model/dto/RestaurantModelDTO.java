package com.peakyblinders.peakyblindersfood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.peakyblinders.peakyblindersfood.api.model.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class RestaurantModelDTO {

 @JsonView({RestaurantView.Summary.class , RestaurantView.NameOnly.class})
 private Long id;
 @JsonView({RestaurantView.Summary.class , RestaurantView.NameOnly.class})
 private String name;
 @JsonView(RestaurantView.Summary.class)
 BigDecimal shippingFee;
 @JsonView(RestaurantView.Summary.class)
 KitchenModelDTO kitchen;
 private Boolean active;
 private Boolean open;
 private AddressModelDTO address;
}
