package com.dogs.projectjava.controller;

import com.dogs.projectjava.Dto.UserDto;
import com.dogs.projectjava.exceptionHandler.ErrorResponse;
import com.dogs.projectjava.mapper.UserMapper;
import com.dogs.projectjava.service.UserDetailsService;
import com.dogs.projectjava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UserMapper userMapper;
    private ErrorResponse errorResponse;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){

        var response = userService.getAllUsers().stream().map(userMapper::entityToDetails).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable String username){

        try {
            var user = userService.findByUsername(username);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("User not found"));
            }
            var userResponse = userMapper.entityToDetails(user);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred"));
        }
    }
}
