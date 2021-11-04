package com.raul.rodriguez.car_rental.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.raul.rodriguez.car_rental.entity.Car;
import com.raul.rodriguez.car_rental.entity.CategoryOffers;
import com.raul.rodriguez.car_rental.exceptions.CarNotFoundException;
import com.raul.rodriguez.car_rental.repository.CarRepository;
import com.raul.rodriguez.car_rental.repository.CategoryOffersRepository;
import com.raul.rodriguez.car_rental.repository.RentCarRepository;
import com.raul.rodriguez.car_rental.service.CarService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("CarService")
public class CarServiceImpl implements CarService {

    private static final Log LOG = LogFactory.getLog(CarServiceImpl.class);

    @Autowired
    @Qualifier("carRepository")
    private CarRepository carRepository;

    @Autowired
    @Qualifier("rentCarRepository")
    private RentCarRepository rentCarRepository;

    @Autowired
    @Qualifier("categoryOffersRepository")
    private CategoryOffersRepository categoryOffersRepository;

    @Override
    public Page<Car> getCars(Integer page) {
        LOG.info("METHOD: getCars -- PARAMS: " + page);
        return carRepository.findAll(PageRequest.of(page, 100));
    }

    @Override
    public Car getCar(Integer carId) {
        LOG.info("METHOD: getCar -- PARAMS: " + carId);
        return carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
    }

    @Override
    public Car addCar(Car car) {
        LOG.info("METHOD: addCar -- PARAMS: " + car);
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car editCar, Integer carId) {
        LOG.info("METHOD: updateCar -- PARAMS: " + editCar + " / " + carId);
        return carRepository.findById(carId).map(car -> {
            car.setBrand(editCar.getBrand());
            car.setModel(editCar.getModel());
            car.setCategory(editCar.getCategory());
            return carRepository.save(car);
        }).orElseGet(() -> {
            editCar.setCarId(carId);
            return carRepository.save(editCar);
        });
    }

    @Override
    public void deleteCar(Integer carId) {
        LOG.info("METHOD: deleteCar -- PARAMS: " + carId);
        carRepository.deleteById(carId);
    }

    @Override
    public Double calculatePrice(Integer carId, String startDate, String endDate) throws ParseException {
        LOG.info("METHOD: calculatePrice -- PARAMS: " + carId + " / " + startDate + " / " + endDate);
        Double price = 0.0;
        Car car = this.getCar(carId);
        List<CategoryOffers> categoryOffers = categoryOffersRepository.findByCategory_Name(car.getCategory().getName());
        long numDays = this.getDifferenceDays(this.convertISOStringToDate(startDate),
                this.convertISOStringToDate(endDate));
        if (!categoryOffers.isEmpty()) {
            for (CategoryOffers offer : categoryOffers) {
                Integer dayStart = offer.getSpecialOffer().getDayStart();
                Integer dayEnd = offer.getSpecialOffer().getDayEnd();
                Double offerPrice = car.getCategory().getPrice() * offer.getSpecialOffer().getOfferPct();

                if (numDays > dayStart && dayEnd == null) {
                    price += (offerPrice * (numDays - dayStart));
                } else if (numDays > dayStart && numDays <= dayEnd) {
                    price += (offerPrice * (numDays - dayStart));
                } else if (numDays > dayStart) {
                    price += (offerPrice * (dayEnd - dayStart));
                }
            }
        } else {
            price = numDays * car.getCategory().getPrice();
        }
        return price;
    }

    @Override
    public Double calculateExtraCharge(Integer carId, String endDate, String reciveDate) throws ParseException {
        Double extraCharge = 0.0;
        Car car = this.getCar(carId);
        long numDays = this.getDifferenceDays(this.convertISOStringToDate(endDate),
                this.convertISOStringToDate(reciveDate));

        if (numDays > 0) {
            extraCharge += (numDays * car.getCategory().getPrice())
                    + (numDays * (car.getCategory().getExtraDayPrice() * car.getCategory().getExtraDayPct()));
        }
        return extraCharge;
    }

    private long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    private Date convertISOStringToDate(String isoDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        return df.parse(isoDate);
    }
}
