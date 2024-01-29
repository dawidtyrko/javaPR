package com.dogs.projectjava.mapper;

import com.dogs.projectjava.Dto.UserDto;
import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.entity.UserDetails;

public class UserMapper {
    public UserDetails toUserDetails(UserDto userDto){
        return  new UserDetails(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getCountry(), userDto.getUser());
    }
    public UserDto toDto(UserDetails userDetails){
        return new UserDto(userDetails.getUsername(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), userDetails.getCountry(), userDetails.getUser());
    }
}
