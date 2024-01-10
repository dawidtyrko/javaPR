package com.dogs.projectjava.controller;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.service.DogApi;
import com.dogs.projectjava.service.DogService;
import com.dogs.projectjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {
    private final DogApi dogApi = new DogApi();
    private final DogService dogService;
    private Dog dog;
    private final UserService userService;
    @Autowired
    public AppController(DogService dogService, UserService userService) {
        this.dogService = dogService;
        this.userService = userService;
    }



    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @PostMapping("/system/dogs")
    public String showDogs(@RequestParam(name = "dogName")String name,Model model){
        dog = dogApi.getDogByName(name);
        model.addAttribute("searchedDog",name);
        model.addAttribute("dog",dog);
        if(dog.getName()==null){
            return "not-found";
        }else{
            dogService.saveDogToDatabase(dog);
            return "dogs";
        }
    }
    @GetMapping("/system/getNewDog")
    public String getNewDog(Model model){
        return "new-dog";
    }
    @GetMapping("/admin/list")
    public String showUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "users-list";
    }

    @GetMapping("/list")
    public String showAllDogs(Model model){
        List<DogEntity> dogEntityList = dogService.getAllDogs();
        model.addAttribute("dogs", dogEntityList);
        return "show-dogs";
    }


}
