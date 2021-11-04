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
public class CategoryOffers implements Serializable {

    private static final long serialVersionUID = 6541122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryOfferId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", referencedColumnName = "name")
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialOffer", referencedColumnName = "offerId")
    private SpecialOffer specialOffer;
}
