package com.project.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeVeiwController {

    @GetMapping("/")
    public String goHome() {
        return "home";
    }
}
