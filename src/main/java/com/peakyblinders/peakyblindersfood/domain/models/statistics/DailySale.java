package com.peakyblinders.peakyblindersfood.domain.models.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class DailySale {

    private Date date;
    private Long totalSales;
    private BigDecimal  totalBilled; // total faturado

}
