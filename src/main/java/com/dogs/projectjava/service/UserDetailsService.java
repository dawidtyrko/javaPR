package com.dogs.projectjava.service;

import com.dogs.projectjava.entity.UserDetails;
import com.dogs.projectjava.repo.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetails getUserDetails(String username){
        return userDetailsRepository.getAllUserDetailsWithUser(username);
    }
}
