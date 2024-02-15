package com.peakyblinders.peakyblindersfood.domain.repositories;

import com.peakyblinders.peakyblindersfood.domain.models.PhotoProduct;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepositoryQueries {

    PhotoProduct save(PhotoProduct photo);

    void delete(PhotoProduct photo);
}
