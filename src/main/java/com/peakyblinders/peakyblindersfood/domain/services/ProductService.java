package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.exceptions.PaymentMethodNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.ProductNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.PaymentMethod;
import com.peakyblinders.peakyblindersfood.domain.models.Product;
import com.peakyblinders.peakyblindersfood.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product){return productRepository.save(product);}


    public Product seekOrFail(Long restaurantId,Long productId){
        return productRepository.findById(restaurantId, productId).orElseThrow(() -> new ProductNotFoundException(
                productId, restaurantId));
    }

}
