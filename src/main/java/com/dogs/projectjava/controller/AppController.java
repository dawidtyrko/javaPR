package com.dogs.projectjava.controller;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import com.dogs.projectjava.service.DogApi;
import com.dogs.projectjava.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {
    private final DogApi dogApi = new DogApi();

    @Autowired
    public AppController(DogService dogService) {
        this.dogService = dogService;
    }

    private final DogService dogService;

    private Dog dog;

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/system/dogs")
    public String showDogs(Model model){
        dog = dogApi.getDogByName("labrador");
        model.addAttribute("dog",dog);
        dogService.saveDogToDatabase(dog);
        return "dogs";
    }
//    @GetMapping("/dogs/save")
//    public String saveDog(Model model){
//        //dogService.saveDogToDatabase(dog);
//        return "save-dog";
//    }
    @GetMapping("/list")
    public String showAllDogs(Model model){
        List<DogEntity> dogEntityList = dogService.getAllDogs();
        model.addAttribute("dogs", dogEntityList);
        return "show-dogs";
    }

//    @PostMapping("/add-to-favorites/{dogId}")
//    @ResponseBody
//    public String addToFavorites(@PathVariable int dogId) {
//
//        DogEntity dog = dogService.getDogById(dogId);
//
//
//        dogService.addDogToFavorites(user, dog);
//
//        return "Dog added to favorites!";
//    }

}
