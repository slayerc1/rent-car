package com.raul.rodriguez.car_rental.service;

import java.text.ParseException;

import com.raul.rodriguez.car_rental.entity.Car;

import org.springframework.data.domain.Page;

public interface CarService {

    public abstract Page<Car> getCars(Integer page);

    public abstract Car getCar(Integer carId);

    public abstract Car addCar(Car car);

    public abstract Car updateCar(Car editCar, Integer carId);

    public abstract void deleteCar(Integer carId);

    public abstract Double calculatePrice(Integer carId, String startDate, String endDate) throws ParseException;

    public abstract Double calculateExtraCharge(Integer carId, String endDate, String reciveDate) throws ParseException;
}
