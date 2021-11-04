package com.raul.rodriguez.car_rental.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String name) {
        super("Category not found: " + name);
    }

}
