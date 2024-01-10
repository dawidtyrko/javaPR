package com.dogs.projectjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessPage(){
        return "access-denied";
    }
}
