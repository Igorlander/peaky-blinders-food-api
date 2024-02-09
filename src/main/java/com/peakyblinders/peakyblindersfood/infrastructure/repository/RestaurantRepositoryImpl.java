package com.peakyblinders.peakyblindersfood.infrastructure.repository;

import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import com.peakyblinders.peakyblindersfood.domain.repositories.RestaurantRepository;
import com.peakyblinders.peakyblindersfood.domain.repositories.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.peakyblinders.peakyblindersfood.infrastructure.repository.spec.RestaurantSepcs.withNameSimilar;
import static com.peakyblinders.peakyblindersfood.infrastructure.repository.spec.RestaurantSepcs.withfreighFree;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String name, BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal){

        /*var jpql = "from Restaurant where name like :name " +
                "and shippingFee between :shippingFeeInitial and :shippingFeeFinal";*/
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        //criteria.from(Restaurant.class); // from Restaurant

        var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasText(name)){
            predicates.add(builder.like(root.get("name") , "%" + name + "%"));
        }
        if(shippingFeeInitial != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("shippingFee"),shippingFeeInitial));
        }
        if(StringUtils.hasText(name)){
            predicates.add(builder.lessThanOrEqualTo(root.get("shippingFee"),shippingFeeFinal));

        }
        criteria.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();

        //return manager.createQuery(criteria)
                /*.setParameter("name",  "%" + name + "%")
                .setParameter("shippingFeeInitial", shippingFeeInitial)
                .setParameter("shippingFeeFinal", shippingFeeFinal)*/
          //      .getResultList();
    }



    @Override
    public List<Restaurant> findWithfreighFree(String name) {
        return restaurantRepository.findAll(withfreighFree().and(withNameSimilar(name)));
    }


}
