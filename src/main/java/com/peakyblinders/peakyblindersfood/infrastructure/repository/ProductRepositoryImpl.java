package com.peakyblinders.peakyblindersfood.infrastructure.repository;

import com.peakyblinders.peakyblindersfood.domain.models.PhotoProduct;
import com.peakyblinders.peakyblindersfood.domain.repositories.ProductRepository;
import com.peakyblinders.peakyblindersfood.domain.repositories.ProductRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public PhotoProduct save(PhotoProduct photo) {
        return manager.merge(photo);
    }

    @Override
    @Transactional
    public void delete(PhotoProduct photo) {
        manager.flush();
        manager.remove(photo);
    }
}
