package com.test.TestTask.services;


import com.test.TestTask.model.DishIntake;
import com.test.TestTask.repositories.DishIntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishIntakeService {

    private final DishIntakeRepository dishIntakeRepository;

    @Autowired
    public DishIntakeService(DishIntakeRepository dishIntakeRepository)
    {
        this.dishIntakeRepository = dishIntakeRepository;
    }

    public void save(DishIntake dishIntake) {
        dishIntakeRepository.save(dishIntake);
    }
}
