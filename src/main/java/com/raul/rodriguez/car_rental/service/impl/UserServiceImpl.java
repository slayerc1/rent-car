package com.raul.rodriguez.car_rental.service.impl;

import com.raul.rodriguez.car_rental.entity.User;
import com.raul.rodriguez.car_rental.exceptions.UserNotFoundException;
import com.raul.rodriguez.car_rental.repository.UserRepository;
import com.raul.rodriguez.car_rental.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public Page<User> getUsers(Integer page) {
        LOG.info("METHOD: getUsers -- PARAMS: " + page);
        return userRepository.findAll(PageRequest.of(page, 100));
    }

    @Override
    public User getUser(String userId) {
        LOG.info("METHOD: getUser -- PARAMS: " + userId);
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public User addUser(User user) {
        LOG.info("METHOD: addUser -- PARAMS: " + user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User editUser, String userId) {
        LOG.info("METHOD: updateUser -- PARAMS: " + editUser + " / " + userId);
        return userRepository.findById(userId).map(user -> {
            user.setName(editUser.getName());
            user.setLoyaltyPoints(editUser.getLoyaltyPoints());
            return userRepository.save(user);
        }).orElseGet(() -> {
            editUser.setUserId(userId);
            return userRepository.save(editUser);
        });
    }

    @Override
    public void deleteUser(String userId) {
        LOG.info("METHOD: deleteUser -- PARAMS: " + userId);
        userRepository.deleteById(userId);
    }

    @Override
    public void updateLoyalty(String userId, Integer loyaltyPoints) {
        LOG.info("METHOD: updateLoyalty -- PARAMS: " + userId + " / " + loyaltyPoints);
        User user = this.getUser(userId);
        Integer oldPoints = user.getLoyaltyPoints();
        if (oldPoints == null) {
            oldPoints = 0;
        }
        user.setLoyaltyPoints(oldPoints + loyaltyPoints);
        userRepository.save(user);
    }
}
