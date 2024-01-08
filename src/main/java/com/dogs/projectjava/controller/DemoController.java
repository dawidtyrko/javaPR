package com.dogs.projectjava.controller;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.service.DogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class DemoController {
    private final DogService dogService = new DogService();

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/dogs")
    public String showDogs(Model model){
        Optional<Dog> dog = dogService.getDogByName("labrador");
        model.addAttribute("dog",dog);
        return "dogs";
    }
}
