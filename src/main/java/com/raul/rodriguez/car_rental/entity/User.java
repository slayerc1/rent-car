package com.raul.rodriguez.car_rental.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 7854172041950251807L;

    @Id
    private String userId;

    private String name;

    private Integer loyaltyPoints;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<RentCar> rentCars = new ArrayList<>();
}
