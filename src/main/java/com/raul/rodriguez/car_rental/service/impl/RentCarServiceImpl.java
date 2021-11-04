package com.raul.rodriguez.car_rental.service.impl;

import java.text.ParseException;
import java.util.List;

import com.raul.rodriguez.car_rental.entity.Car;
import com.raul.rodriguez.car_rental.entity.RentCar;
import com.raul.rodriguez.car_rental.entity.User;
import com.raul.rodriguez.car_rental.exceptions.RentCarNotFoundException;
import com.raul.rodriguez.car_rental.repository.RentCarRepository;
import com.raul.rodriguez.car_rental.service.RentCarService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("RentCarService")
public class RentCarServiceImpl implements RentCarService {

    private static final Log LOG = LogFactory.getLog(RentCarServiceImpl.class);

    @Autowired
    @Qualifier("rentCarRepository")
    private RentCarRepository rentCarRepository;

    @Autowired
    @Qualifier("CarService")
    private CarServiceImpl carService;

    @Autowired
    @Qualifier("UserService")
    private UserServiceImpl userService;

    @Override
    public Page<RentCar> getRentCars(Integer page) {
        LOG.info("METHOD: getRentCars -- PARAMS: " + page);
        return rentCarRepository.findBy(PageRequest.of(page, 100));
    }

    @Override
    public RentCar getRentCar(Integer rentId) {
        LOG.info("METHOD: getRentCar -- PARAMS: " + rentId);
        return rentCarRepository.findById(rentId).orElseThrow(() -> new RentCarNotFoundException(rentId));
    }

    @Override
    public Double rentCar(List<RentCar> cars) throws ParseException {
        LOG.info("METHOD: rentCar -- PARAMS: " + cars);
        rentCarRepository.saveAll(cars);
        Double price = 0.0;
        for (RentCar rentCar : cars) {
            Car car = carService.getCar(rentCar.getCar().getCarId());
            price += carService.calculatePrice(car.getCarId(), rentCar.getStartDay(), rentCar.getEndDay());
            userService.updateLoyalty(rentCar.getUser().getUserId(), car.getCategory().getLoyaltyPoints());
        }
        return price;
    }

    @Override
    public void deleteRentCar(Integer rentId) {
        LOG.info("METHOD: deleteRentCar -- PARAMS: " + rentId);
        rentCarRepository.deleteById(rentId);
    }

    @Override
    public Double returnCar(Integer rentId, String reciveDate) throws ParseException {
        LOG.info("METHOD: returnCar -- PARAMS: " + rentId + " / " + reciveDate);
        RentCar rentCar = getRentCar(rentId);
        rentCar.setReciveDate(reciveDate);
        rentCarRepository.save(rentCar);
        Double extraCharge = 0.0;
        extraCharge += carService.calculateExtraCharge(rentCar.getCar().getCarId(), rentCar.getEndDay(),
                rentCar.getReciveDate());
        return extraCharge;
    }
}
