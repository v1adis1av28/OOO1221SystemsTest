package com.test.TestTask.controllers;

import com.test.TestTask.model.User;
import com.test.TestTask.services.UserService;
import com.test.TestTask.util.UserNotFoundException;
import com.test.TestTask.util.UserResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }



    @ExceptionHandler
    private ResponseEntity<UserResponseError> handleException(UserNotFoundException e) {
        UserResponseError response = new UserResponseError(
                "Person with this id wasn`t found!"
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
