package com.raul.rodriguez.car_rental.repository;

import java.io.Serializable;

import com.raul.rodriguez.car_rental.entity.Car;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Serializable>, JpaSpecificationExecutor<Car> {

}
