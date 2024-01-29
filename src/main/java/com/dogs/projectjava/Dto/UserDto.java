package com.dogs.projectjava.Dto;

import com.dogs.projectjava.entity.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private User user;
}
