package com.raul.rodriguez.car_rental.service;

import com.raul.rodriguez.car_rental.entity.User;

import org.springframework.data.domain.Page;

public interface UserService {

    public abstract Page<User> getUsers(Integer page);

    public abstract User getUser(String userId);

    public abstract User addUser(User user);

    public abstract User updateUser(User editUser, String userId);

    public abstract void deleteUser(String userId);

    public abstract void updateLoyalty(String userId, Integer loyaltyPoints);

}
