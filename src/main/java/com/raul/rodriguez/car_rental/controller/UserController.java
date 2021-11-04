package com.raul.rodriguez.car_rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.raul.rodriguez.car_rental.entity.User;
import com.raul.rodriguez.car_rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @GetMapping
    public List<User> getUsers(@RequestParam Integer page) {
        return userService.getUsers(page).getContent();
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable(required = true) String userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping(value = "/{userId}")
    public User updateUser(@RequestBody User editUser, @PathVariable(required = true) String userId) {
        return userService.updateUser(editUser, userId);
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable(required = true) String userId) {
        userService.deleteUser(userId);
    }
}
