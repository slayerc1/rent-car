package com.raul.rodriguez.car_rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

import javax.sound.midi.Receiver;

import com.raul.rodriguez.car_rental.entity.RentCar;
import com.raul.rodriguez.car_rental.service.RentCarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/rentcars")
public class RentCarController {
    @Autowired
    @Qualifier("RentCarService")
    private RentCarService rentCarService;

    @GetMapping
    public List<RentCar> getCategories(@RequestParam Integer page) {
        return rentCarService.getRentCars(page).getContent();
    }

    @GetMapping(value = "/{rentId}")
    public RentCar getRentCar(@PathVariable(required = true) Integer rentId) {
        return rentCarService.getRentCar(rentId);
    }

    @PostMapping
    public Double rentCars(@RequestBody List<RentCar> cars) throws ParseException {
        return rentCarService.rentCar(cars);
    }

    @DeleteMapping(value = "/{rentId}")
    public void deleteRentCar(@PathVariable(required = true) Integer rentId) {
        rentCarService.deleteRentCar(rentId);
    }

    @PatchMapping(value = "/{rentId}/return")
    public Double returnCar(@PathVariable(required = true) Integer rentId, @RequestParam String reciveDate)
            throws ParseException {
        return rentCarService.returnCar(rentId, reciveDate);
    }
}
