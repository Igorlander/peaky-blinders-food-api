package com.peakyblinders.peakyblindersfood.infrastructure.service.report;

import com.peakyblinders.peakyblindersfood.domain.filter.DailySaleFilter;
import com.peakyblinders.peakyblindersfood.domain.services.SaleQueryService;
import com.peakyblinders.peakyblindersfood.domain.services.SaleReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfSaleReportService implements SaleReportService {

    @Autowired
    private SaleQueryService saleQueryService;

    @Override
    public byte[] issueDailySale(DailySaleFilter dailySaleFilter, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream(
                    "/report/dailys-Sales.jasper");

            var parameter = new HashMap<String, Object>();
            parameter.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var dailysSales = saleQueryService.consultSalesDaily(dailySaleFilter, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(dailysSales);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parameter, dataSource);
            System.out.println("AKI --- >>>");

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            System.out.println("ERRO");

            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
        }
    }
}
