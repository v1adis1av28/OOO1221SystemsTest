package com.test.TestTask.controllers;

import com.test.TestTask.exceptions.DishAlreadyExistException;
import com.test.TestTask.exceptions.DishNotCreatedException;
import com.test.TestTask.exceptions.UserNotCreatedException;
import com.test.TestTask.model.Dish;
import com.test.TestTask.services.DishService;
import com.test.TestTask.util.ResponseError;
import com.test.TestTask.util.ResponseError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.findAll();
    }

    @PostMapping
    public HttpEntity<HttpStatus> createDish(@RequestBody @Valid Dish dish, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder ErrorMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {
                ErrorMsg.append(fieldError.getField().concat("-").concat(fieldError.getDefaultMessage().concat(";")));
            }
            throw new DishNotCreatedException(ErrorMsg.toString());
        }

        dishService.save(dish);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(DishNotCreatedException e) {
        ResponseError response = new ResponseError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseError> handleException(DishAlreadyExistException e) {
        ResponseError response = new ResponseError(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
