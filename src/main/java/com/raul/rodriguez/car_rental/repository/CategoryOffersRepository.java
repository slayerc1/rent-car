package com.raul.rodriguez.car_rental.repository;

import java.io.Serializable;
import java.util.List;

import com.raul.rodriguez.car_rental.entity.CategoryOffers;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryOffersRepository extends PagingAndSortingRepository<CategoryOffers, Serializable>,
                JpaSpecificationExecutor<CategoryOffers> {

        @EntityGraph(attributePaths = { "category", "specialOffer" })
        List<CategoryOffers> findByCategory_Name(String category);
}
