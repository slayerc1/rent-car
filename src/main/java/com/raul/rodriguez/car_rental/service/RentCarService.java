package com.raul.rodriguez.car_rental.service;

import java.text.ParseException;
import java.util.List;

import com.raul.rodriguez.car_rental.entity.RentCar;

import org.springframework.data.domain.Page;

public interface RentCarService {

    public abstract Page<RentCar> getRentCars(Integer page);

    public abstract RentCar getRentCar(Integer rentId);

    public abstract Double rentCar(List<RentCar> cars) throws ParseException;

    public abstract void deleteRentCar(Integer rentId);

    public abstract Double returnCar(Integer rentId, String reciveDate) throws ParseException;

}
