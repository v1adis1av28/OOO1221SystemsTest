package com.test.TestTask.services;

import com.test.TestTask.exceptions.DishAlreadyExistException;
import com.test.TestTask.model.Dish;
import com.test.TestTask.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void save(Dish dish) {
        Optional<Dish> dishOptional = dishRepository.findDishByName(dish.getName().toLowerCase());
        if (dishOptional.isPresent()) {
            throw new DishAlreadyExistException("Dish already exist");
        }
        dish.setName(dish.getName().toLowerCase());
        dishRepository.save(dish);
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}
