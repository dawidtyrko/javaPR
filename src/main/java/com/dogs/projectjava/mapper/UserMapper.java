package com.dogs.projectjava.mapper;

import com.dogs.projectjava.Dto.UserDto;
import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.entity.UserDetails;
import com.dogs.projectjava.service.UserDetailsService;
import com.dogs.projectjava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    public UserDetails toUserDetails(UserDto userDto){
        return  new UserDetails(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getCountry(),userService.findByUsername(userDto.getUsername()).getUserDetails().getUser());
    }
    public UserDto toDto(UserDetails userDetails){
        return new UserDto(userDetails.getUsername(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), userDetails.getCountry());
    }
    public UserDto entityToDetails(User user){
        var userDetails = userDetailsService.getUserDetails(user.getUsername());

        return new UserDto(user.getUsername(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), userDetails.getCountry());

    }
}
