package com.dogs.projectjava;

import com.dogs.projectjava.Dto.UserDto;
import com.dogs.projectjava.controller.UserController;
import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.mapper.UserMapper;
import com.dogs.projectjava.repo.UserRepository;
import com.dogs.projectjava.service.UserDetailsService;
import com.dogs.projectjava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.dogs.projectjava")
@AutoConfigureMockMvc

public class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private  UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserController userController;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {

        User user1 = new User("test","test",1);
        User user2 = new User("test1","test",1);
        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        UserDto userDto1 = new UserDto("test","test","test","test","test");
        UserDto userDto2 = new UserDto("test1","test","test","test","test");
        List<UserDto> userDtos = Arrays.asList(userDto1, userDto2);

        when(userMapper.entityToDetails(user1)).thenReturn(userDto1);
        when(userMapper.entityToDetails(user2)).thenReturn(userDto2);


        ResponseEntity<List<UserDto>> responseEntity = userController.getAllUsers();


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(userDtos);
    }

    @Test
    public void testGetUserByUsername() {

        String username = "testUser";
        User user = new User(username,"test",1);
        when(userService.findByUsername(username)).thenReturn(user);

        UserDto userDto = new UserDto("test","test","test","test","test");
        when(userMapper.entityToDetails(user)).thenReturn(userDto);


        ResponseEntity<?> responseEntity =  userController.getUser(username);


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(userDto);
    }
}
