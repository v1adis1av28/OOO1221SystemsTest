package com.test.TestTask.services;


import com.test.TestTask.model.User;
import com.test.TestTask.repositories.UserRepository;
import com.test.TestTask.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        userRepository.save(user);
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

}
