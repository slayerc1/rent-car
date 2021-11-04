package com.raul.rodriguez.car_rental.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Integer id) {
        super("Car id not found: " + id);
    }

}
