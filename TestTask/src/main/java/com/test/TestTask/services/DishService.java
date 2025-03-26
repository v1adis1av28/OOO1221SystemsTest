package com.test.TestTask.services;

import com.test.TestTask.model.Dish;
import com.test.TestTask.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void save(Dish dish) {
        dishRepository.save(dish);
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}
