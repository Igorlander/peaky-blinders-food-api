package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.filter.DailySaleFilter;
import net.sf.jasperreports.engine.JRException;

public interface SaleReportService {

    byte[]  issueDailySale(DailySaleFilter dailySaleFilter, String timeOffset) throws JRException;
}
