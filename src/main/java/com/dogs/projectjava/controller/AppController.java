package com.dogs.projectjava.controller;

import com.dogs.projectjava.entity.Dog;
import com.dogs.projectjava.entity.DogEntity;
import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.entity.UserDetails;
import com.dogs.projectjava.service.DogApi;
import com.dogs.projectjava.service.DogService;
import com.dogs.projectjava.service.UserDetailsService;
import com.dogs.projectjava.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final UserDetailsService userDetailsService;

    @Value("${countries}")
    private List<String> countries;

    @Autowired
    public AppController(DogService dogService, UserService userService, UserDetailsService userDetailsService) {
        this.dogService = dogService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String showHome(Model model){
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
    @PostMapping("/list")
    public String newDog(@RequestParam(name = "dogNameSearch") String name,Model model){
        System.out.println(name);
        try{
            String nameTrimmed = name.replace("+","").trim().toLowerCase();
            List<DogEntity> dogEntityList = dogService.getAllDogs();
            var searchedDog = dogEntityList.stream().filter(dogEntity -> dogEntity.getName().trim().toLowerCase().contains(nameTrimmed)).findFirst().orElseThrow(null);
            if(searchedDog != null){
                model.addAttribute("dog",searchedDog);
                return "dog-from-list";
            }else{
                model.addAttribute("searchedDog",name);
                return "not-found";
            }
        }catch (Exception e){
            model.addAttribute("searchedDog",name);
            return "not-found";
        }
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

    @GetMapping("/admin/details")
    public String userDetails(@RequestParam("username")String username,Model model){
        var userDetails = userDetailsService.getUserDetails(username);
        var user = userService.findByUsername(username);
        model.addAttribute("userDetails",userDetails);
        model.addAttribute("user",user);
        return "user-details";
    }

    @GetMapping("/admin/updateForm")
    public String updateForm(@RequestParam("username") String username, Model model){

        var user = userService.findByUsername(username);
        var userDetails = userDetailsService.getUserDetails(username);
        model.addAttribute("userDetails",userDetails);
        model.addAttribute("userToUpdate",user);
        model.addAttribute("countries",countries);
        return "user-update";
    }
    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("userDetails") UserDetails userDetails){
        userDetailsService.saveUserDetails(userDetails);
        return "redirect:/admin/list";
    }

}
