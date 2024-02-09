package com.peakyblinders.peakyblindersfood.infrastructure.service.query;

import com.peakyblinders.peakyblindersfood.domain.filter.DailySaleFilter;
import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import com.peakyblinders.peakyblindersfood.domain.models.statistics.DailySale;
import com.peakyblinders.peakyblindersfood.domain.services.SaleQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<DailySale> consultSalesDaily(DailySaleFilter dailySaleFilter, String timeOffset) {
       var builder =  manager.getCriteriaBuilder();
       var query = builder.createQuery(DailySale.class);
       var root = query.from(Ticket.class);
       var functionConvertTzDateCreation = builder.function("convert_tz",
               Date.class , root.get("creationDate"),builder.literal("+00:00"),builder.literal(timeOffset));
       var functionDateCreation = builder.function("date", Date.class ,
               functionConvertTzDateCreation);

       var selection  = builder.construct(DailySale.class,
               functionDateCreation,
               builder.count(root.get("id")),
               builder.sum(root.get("amount")));

        query.select(selection);
        query.groupBy(functionDateCreation);
     return manager.createQuery(query).getResultList();
    }
}
