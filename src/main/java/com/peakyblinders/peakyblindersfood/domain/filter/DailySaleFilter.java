package com.peakyblinders.peakyblindersfood.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class DailySaleFilter {

    private Long restaurantId;
    private OffsetDateTime creationInitialDate;
    private OffsetDateTime creationFinalDate;
}
