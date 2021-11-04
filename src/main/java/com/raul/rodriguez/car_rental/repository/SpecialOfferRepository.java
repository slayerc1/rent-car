package com.raul.rodriguez.car_rental.repository;

import java.io.Serializable;

import com.raul.rodriguez.car_rental.entity.SpecialOffer;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialOfferRepository
        extends PagingAndSortingRepository<SpecialOffer, Serializable>, JpaSpecificationExecutor<SpecialOffer> {

}
