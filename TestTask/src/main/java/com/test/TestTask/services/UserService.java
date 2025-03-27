package com.test.TestTask.services;


import com.test.TestTask.DTO.UserDTO;
import com.test.TestTask.model.User;
import com.test.TestTask.repositories.UserRepository;
import com.test.TestTask.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findUserById(id);

        return user.orElseThrow(UserNotFoundException::new);
    }

    public void save(User user) {
        user.setDailyCalories((float) Math.ceil(calculateDailyCalories(user)));
        userRepository.save(user);
    }

    private float calculateDailyCalories(User user)
    {
        if(user.getGender().toString().toLowerCase().equals("male"))
            return (float) (13.75*user.getWeight() + (5 * user.getHeight()) - (6.75* user.getAge()) + 66.473);
        else
            return (float) (9.56 * user.getWeight() + ( 1.8 * user.getHeight()) - (4.67 * user.getAge()) + 655);

    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public UserDTO convertToDTO(User userById) {
        UserDTO dto = new UserDTO();
        dto.setDailyCalories(userById.getDailyCalories());
        dto.setUsername(userById.getUsername());
        dto.setEmail(userById.getEmail());
        dto.setGender(userById.getGender());
        dto.setGoal(userById.getGoal());
        dto.setAge(userById.getAge());
        dto.setHeight((float) userById.getHeight());
        dto.setWeight((float) userById.getWeight());
        return dto;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> dtos = new ArrayList<>();
        userRepository.findAll().forEach(user -> dtos.add(convertToDTO(user)));
        return dtos;
    }
}
