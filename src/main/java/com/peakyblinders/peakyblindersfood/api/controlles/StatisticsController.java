package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.domain.filter.DailySaleFilter;
import com.peakyblinders.peakyblindersfood.domain.models.statistics.DailySale;
import com.peakyblinders.peakyblindersfood.domain.services.SaleQueryService;
import com.peakyblinders.peakyblindersfood.domain.services.SaleReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private SaleQueryService saleQueryService;

    @Autowired
    private SaleReportService saleReportService;

    @GetMapping(path = "/daily-sales" , produces =  MediaType.APPLICATION_JSON_VALUE)
    public List<DailySale> consultDailySales(DailySaleFilter dailySaleFilter,
                                            @RequestParam(required = false,
                                                    defaultValue = "+00:00") String timeOffset){
        return saleQueryService.consultSalesDaily(dailySaleFilter, timeOffset);
    }

    @GetMapping(path = "/daily-sales" , produces =  MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultDailySalesPdf(DailySaleFilter dailySaleFilter,
                                                          @RequestParam(required = false,
                                                     defaultValue = "+00:00") String timeOffset) throws JRException {
        byte[] bytesPdf = saleReportService.issueDailySale(dailySaleFilter, timeOffset);
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=daily-sales.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);

    }
}
