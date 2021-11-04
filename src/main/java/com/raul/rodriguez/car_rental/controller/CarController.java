package com.raul.rodriguez.car_rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

import com.raul.rodriguez.car_rental.entity.Car;
import com.raul.rodriguez.car_rental.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    @Qualifier("CarService")
    private CarService carService;

    @GetMapping
    public List<Car> getCars(@RequestParam Integer page) {
        return carService.getCars(page).getContent();
    }

    @GetMapping(value = "/{carId}")
    public Car getCar(@PathVariable(required = true) Integer carId) {
        return carService.getCar(carId);
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @PutMapping(value = "/{carId}")
    public Car updateCar(@RequestBody Car editCar, @PathVariable(required = true) Integer carId) {
        return carService.updateCar(editCar, carId);
    }

    @DeleteMapping(value = "/{carId}")
    public void deleteCar(@PathVariable(required = true) Integer carId) {
        carService.deleteCar(carId);
    }

    @GetMapping(value = "/{carId}/price")
    public Double calculatePrice(@PathVariable(required = true) Integer carId, @RequestParam String startDate,
            @RequestParam String endDate) throws ParseException {
        return carService.calculatePrice(carId, startDate, endDate);
    }

    @GetMapping(value = "/{carId}/extraCharge")
    public Double calculateExtraCharge(@PathVariable(required = true) Integer carId, @RequestParam String endDate,
            @RequestParam String reciveDate) throws ParseException {
        return carService.calculateExtraCharge(carId, endDate, reciveDate);
    }
}
