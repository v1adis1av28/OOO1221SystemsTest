package com.test.TestTask.controllers;

import com.test.TestTask.exceptions.UserNotCreatedException;
import com.test.TestTask.model.User;
import com.test.TestTask.services.UserService;
import com.test.TestTask.exceptions.UserNotFoundException;
import com.test.TestTask.util.ResponseError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    //TODo сделать дто для пользователя + пофиксить dailyReport + добавить туда обработку ошибок

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

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuilder ErrorMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {
                ErrorMsg.append(fieldError.getField().concat("-").concat(fieldError.getDefaultMessage().concat(";")));
            }
            throw new UserNotCreatedException(ErrorMsg.toString());
        }

        userService.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(UserNotFoundException e) {
        ResponseError response = new ResponseError("Person with this id wasn`t found!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(UserNotCreatedException e) {
        ResponseError response = new ResponseError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
