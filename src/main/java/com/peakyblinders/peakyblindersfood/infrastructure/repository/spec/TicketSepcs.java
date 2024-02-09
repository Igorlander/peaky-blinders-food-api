package com.peakyblinders.peakyblindersfood.infrastructure.repository.spec;

import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import com.peakyblinders.peakyblindersfood.domain.filter.TicketOrderFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;


public class TicketSepcs {

    public static Specification<Ticket> usingFilter(TicketOrderFilter orderFilter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (Ticket.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("restaurant").fetch("address").fetch("city")
                        .fetch("state");
                root.fetch("client");
            }
            if (orderFilter.getClientId() != null) {
                predicates.add(builder.equal(root.get("client").get("id"), orderFilter.getClientId()));
            }

            if (orderFilter.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant").get("id"), orderFilter.getRestaurantId()));
            }

            if (orderFilter.getCreationInitialDate() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"),
                        orderFilter.getCreationInitialDate()));
            }

            if (orderFilter.getCreationFinalDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), orderFilter.getCreationFinalDate()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
