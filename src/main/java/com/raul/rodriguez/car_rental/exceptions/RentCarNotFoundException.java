package com.raul.rodriguez.car_rental.exceptions;

public class RentCarNotFoundException extends RuntimeException {
    public RentCarNotFoundException(Integer id) {
        super("RentCar not found: " + id);
    }

}
