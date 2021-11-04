package com.raul.rodriguez.car_rental.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Category implements Serializable {

    private static final long serialVersionUID = 1483122041950251207L;

    @Id
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Double extraDayPrice;

    @NotNull
    private Double extraDayPct;

    @NotNull
    private Integer loyaltyPoints;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Car> cars = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<CategoryOffers> categoryOffers = new ArrayList<>();
}
