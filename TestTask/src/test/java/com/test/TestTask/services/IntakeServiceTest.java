package com.test.TestTask.services;

import com.test.TestTask.DTO.IntakeDTO;
import com.test.TestTask.exceptions.DishNotFoundException;
import com.test.TestTask.exceptions.EmptyIntakeException;
import com.test.TestTask.model.Dish;
import com.test.TestTask.model.DishIntake;
import com.test.TestTask.model.Intake;
import com.test.TestTask.model.User;
import com.test.TestTask.repositories.DishRepository;
import com.test.TestTask.repositories.IntakeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IntakeServiceTest {

    @MockitoBean
    private IntakeRepository intakeRepository;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private DishIntakeService dishIntakeService;

    @MockitoBean
    private DishRepository dishRepository;

    @Autowired
    private IntakeService intakeService;

    @Test
    public void testSave_Success() {
        // Arrange
        IntakeDTO intakeDTO = new IntakeDTO();
        intakeDTO.setUserId(1);
        intakeDTO.setDishesName(List.of("Pizza", "Salad"));

        User mockUser = new User();
        mockUser.setId(1);

        Dish pizza = new Dish();
        pizza.setName("pizza");
        pizza.setCalorie(300);

        Dish salad = new Dish();
        salad.setName("salad");
        salad.setCalorie(100);

        when(userService.getUserById(1)).thenReturn(mockUser);
        when(dishRepository.findDishByName("pizza")).thenReturn(Optional.of(pizza));
        when(dishRepository.findDishByName("salad")).thenReturn(Optional.of(salad));
        Intake mockIntake = new Intake();
        when(intakeRepository.save(any(Intake.class))).thenReturn(mockIntake);

        intakeService.save(intakeDTO);

        verify(intakeRepository, times(1)).save(any(Intake.class));
        verify(dishIntakeService, times(2)).save(any(DishIntake.class));
    }
    @Test
    public void testSave_EmptyDishes() {
        IntakeDTO intakeDTO = new IntakeDTO();
        intakeDTO.setUserId(1);
        intakeDTO.setDishesName(new ArrayList<>());
        assertThrows(EmptyIntakeException.class, () -> intakeService.save(intakeDTO));
    }

    @Test
    public void testSave_DishNotFound() {
        IntakeDTO intakeDTO = new IntakeDTO();
        intakeDTO.setUserId(1);
        intakeDTO.setDishesName(List.of("NonExistentDish"));

        User mockUser = new User();
        mockUser.setId(1);

        when(userService.getUserById(1)).thenReturn(mockUser);
        when(dishRepository.findDishByName("nonexistentdish")).thenReturn(Optional.empty());
        assertThrows(DishNotFoundException.class, () -> intakeService.save(intakeDTO));
    }
}