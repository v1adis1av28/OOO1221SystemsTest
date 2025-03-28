package com.test.TestTask.services;


import com.test.TestTask.DTO.CalorieCheckDTO;
import com.test.TestTask.DTO.IntakeDTO;
import com.test.TestTask.exceptions.DishNotFoundException;
import com.test.TestTask.exceptions.EmptyIntakeException;
import com.test.TestTask.model.Dish;
import com.test.TestTask.model.DishIntake;
import com.test.TestTask.model.Intake;
import com.test.TestTask.repositories.DishRepository;
import com.test.TestTask.repositories.IntakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IntakeService {

    private final IntakeRepository intakeRepository;
    private final UserService userService;
    private final DishIntakeService dishIntakeService;
    private final DishRepository dishRepository;
    @Autowired
    public IntakeService(IntakeRepository intakeRepository, UserService userService, DishIntakeService dishIntakeService, DishRepository dishRepository)
    {
        this.intakeRepository = intakeRepository;
        this.userService = userService;
        this.dishIntakeService = dishIntakeService;
        this.dishRepository = dishRepository;
    }

    public void save(IntakeDTO intakeDTO)
    {
        if(intakeDTO.getDishesName().isEmpty())
        {
            throw new EmptyIntakeException("You need to add at least one dish");
        }
        Intake intake = intakeRepository.save(convertToIntake(intakeDTO));
        updateDishIntakes(intake, intakeDTO.getDishesName());
    }


    private void updateDishIntakes(Intake intake, List<String> dishesName)
    {
        List<DishIntake> dishIntakes = new ArrayList<>();
        for(String name : dishesName)
        {
            DishIntake dishIntake = new DishIntake();
            Optional<Dish> dish = dishRepository.findDishByName(name.toLowerCase());
            dishIntake.setDish(dish.orElseThrow(() -> new DishNotFoundException("Dish with name " + name +" not found")));
            dishIntake.setIntake(intake);
            dishIntakeService.save(dishIntake);
            dishIntakes.add(dishIntake);
        }
        intake.setDishIntakes(dishIntakes);
    }

    private Intake convertToIntake(IntakeDTO intakeDTO)
    {
        Intake intake = new Intake();
        intake.setDate(LocalDate.now());
        intake.setUser(userService.getUserById(intakeDTO.getUserId()));
        return intake;
    }

    public List<IntakeDTO> getAll() {
        List<Intake> intakes = intakeRepository.findAll();
        return convertToDTO(intakes);
    }

    public List<IntakeDTO> convertToDTO(List<Intake> intakes) {
        List<IntakeDTO> dtos = new ArrayList<>();
        for(Intake intake : intakes)
        {
            IntakeDTO intakeDTO = new IntakeDTO();
            intakeDTO.setDishesName(intake.getDishIntakes().stream().map(dishIntake -> dishIntake.getDish().getName()).collect(Collectors.toList()));
            intakeDTO.setUserId(intake.getUser().getId());
            dtos.add(intakeDTO);
        }
        return dtos;
    }

    public List<Intake> getUserIntakes(int id) {
        return intakeRepository.getIntakesByUser(userService.getUserById(id));
    }

    public List<IntakeDTO> getDailyIntakes(int id)
    {
        List<Intake> dailyIntake = new ArrayList<>();
        for(Intake intake : getUserIntakes(id))
        {
            if(intake.getDate().getDayOfYear() == LocalDate.now().getDayOfYear())
            {
                dailyIntake.add(intake);
            }
        }
        return convertToDTO(dailyIntake);
    }


    public int countCalories(List<IntakeDTO> userIntakes) {
        int sum = 0;
        for (IntakeDTO intakeDTO : userIntakes) {
            List<Dish> dishes = intakeDTO.getDishesName().stream()
                    .map(dishName -> dishRepository.findDishByName(dishName.toLowerCase()))
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());

            int calories = dishes.stream()
                    .mapToInt(dish -> (int) dish.getCalorie())
                    .sum();

            sum += calories;
        }
        return sum;
    }

    public CalorieCheckDTO checkCalories(int id) {
        CalorieCheckDTO dto = new CalorieCheckDTO();
        dto.setCurrentCalorieCount(countCalories(getDailyIntakes(id)));;
        dto.setUserDailyCalorie(userService.getUserById(id).getDailyCalories());
        return dto;
    }

    public List<Intake> getIntakesByDate(LocalDate date, int id) {
        return intakeRepository.getIntakeByDateAndUser(date,userService.getUserById(id));
    }
}
