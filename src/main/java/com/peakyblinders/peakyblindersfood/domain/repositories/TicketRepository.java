package com.peakyblinders.peakyblindersfood.domain.repositories;

import com.peakyblinders.peakyblindersfood.domain.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket , String>, JpaSpecificationExecutor<Ticket> {

    Optional<Ticket> findByCode(String code);

    @Query("from Ticket t join fetch t.client join fetch t.restaurant r join fetch r.kitchen")
    List<Ticket>findAll();
}
