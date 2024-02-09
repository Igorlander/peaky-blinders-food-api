package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.filter.DailySaleFilter;
import com.peakyblinders.peakyblindersfood.domain.models.statistics.DailySale;

import java.util.List;

public interface SaleQueryService {

    List<DailySale> consultSalesDaily(DailySaleFilter dailySaleFilter,String timeOffset);
}
