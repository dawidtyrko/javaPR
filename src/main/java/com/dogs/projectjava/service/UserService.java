package com.dogs.projectjava.service;

import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.exceptionHandler.UserNotFoundException;
import com.dogs.projectjava.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void disableByUsername(String username){
        User userToDisable = userRepository.findByUsername(username);

        if (userToDisable != null) {
            userToDisable.setEnabled(0);
            userRepository.save(userToDisable);
        } else {
            throw new UserNotFoundException("User with username " + username + " not found");
        }
    }
    @Transactional
    public void enableByUsername(String username){
        User userToEnable = userRepository.findByUsername(username);

        if (userToEnable != null) {
            userToEnable.setEnabled(1);
            userRepository.save(userToEnable);
        } else {
            throw new UserNotFoundException("User with username " + username + " not found");
        }
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
