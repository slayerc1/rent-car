package com.raul.rodriguez.car_rental.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class SpecialOffer implements Serializable {

    private static final long serialVersionUID = 7854178521854151807L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offerId;

    private Integer dayStart;

    private Integer dayEnd;

    private Double offerPct;

    @JsonIgnore
    @OneToMany(mappedBy = "specialOffer")
    private List<CategoryOffers> categoryOffers = new ArrayList<>();

}
