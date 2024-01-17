package com.dogs.projectjava.controller;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.service.DogApi;
import com.dogs.projectjava.service.DogService;
import com.dogs.projectjava.service.UserService;
import jakarta.persistence.EntityNotFoundException;
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
//    @GetMapping("/list/{name}")
//    public String newDog(@PathVariable String name,Model model){
//
//            DogEntity dog = dogService.findDogByName(name);
//            if(dog != null){
//                model.addAttribute("dog",dog);
//                return "dog-from-list";
//            }else{
//                return "show-dogs";
//            }
//    }


    @GetMapping("/admin/list")
    public String showUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "users-list";
    }

    @GetMapping("/list")
    public String showAllDogs(Model model){
        List<DogEntity> dogEntityList = dogService.getAllDogs();
        List<String> dogNames = dogService.findDistinctNames();
        model.addAttribute("names",dogNames);
        model.addAttribute("dogs", dogEntityList);
        return "show-dogs";
    }
    @GetMapping("/admin/dogList")
    public String showDogs(Model model){
        List<DogEntity> dogEntityList = dogService.getAllDogs();
        List<String> dogNames = dogService.findDistinctNames();
        model.addAttribute("names",dogNames);
        model.addAttribute("dogs", dogEntityList);
        return "dog-list";
    }
    @GetMapping("/admin/delete")
    public String deleteDog(@RequestParam("dogId") int id){
        dogService.deleteById(id);
        return "redirect:/admin/dogList";
    }
    @GetMapping("/admin/disable")
    public String disableUser(@RequestParam("username")String username){
        userService.disableByUsername(username);
        return "redirect:/admin/list";
    }
    @GetMapping("/admin/enable")
    public String enableUser(@RequestParam("username")String username){
        userService.enableByUsername(username);
        return "redirect:/admin/list";
    }


}
