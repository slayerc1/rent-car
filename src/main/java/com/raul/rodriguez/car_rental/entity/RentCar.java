package com.raul.rodriguez.car_rental.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class RentCar implements Serializable {

    private static final long serialVersionUID = 2405172041950274137L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car", referencedColumnName = "carId")
    private Car car;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private User user;

    @NotNull
    private String startDay;

    @NotNull
    private String endDay;

    private String reciveDate;
}
