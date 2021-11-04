package com.raul.rodriguez.car_rental.repository;

import java.io.Serializable;

import com.raul.rodriguez.car_rental.entity.RentCar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentCarRepository
                extends PagingAndSortingRepository<RentCar, Serializable>, JpaSpecificationExecutor<RentCar> {

        @EntityGraph(attributePaths = { "car", "user" })
        Page<RentCar> findBy(Pageable page);
}
