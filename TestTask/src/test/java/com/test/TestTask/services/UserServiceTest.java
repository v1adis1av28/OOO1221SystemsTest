package com.test.TestTask.services;


import com.test.TestTask.exceptions.UserNotCreatedException;
import com.test.TestTask.exceptions.UserNotFoundException;
import com.test.TestTask.model.Gender;
import com.test.TestTask.model.Goal;
import com.test.TestTask.model.User;
import com.test.TestTask.repositories.UserRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testCalculateDailyCalories() {
        User user = new User();
        user.setAge(18);
        user.setUsername("test");
        user.setWeight(100);
        user.setHeight(195);
        user.setGoal(Goal.MAINTENANCE);
        user.setGender(Gender.MALE);
        float calories = userService.calculateDailyCalories(user);

        assertEquals(2295, Math.ceil(calories));
    }


    @Test
    public void testInvalidUserCreation() {
        // Arrange
        User user = new User();
        user.setAge(18);
        user.setUsername("test");
        user.setWeight(100);
        user.setHeight(195);
        user.setGoal(Goal.MAINTENANCE);
        user.setGender(Gender.MALE);
        user.setEmail("emailTes.mail.ru");
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        assertFalse(Pattern.matches(user.getEmail(), regexPattern));
        user.setAge(-1); // Некорректный возраст

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.save(user));
        assertTrue(exception.getMessage().contains("Invalid user data"));

        user.setAge(19);
        user.setHeight(-1);
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> userService.save(user));
        assertTrue(exception2.getMessage().contains("Invalid user data"));

    }

    @Test
    public void testGetUserWithInvalidId() {
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(-1);
        });

    }

}