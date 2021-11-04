package com.raul.rodriguez.car_rental.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("User not found: " + userId);
    }

}
