package com.ACSI.Authentification.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ACSI.Authentification.AuthService.AuthService;
@Controller
@CrossOrigin(origins = "*")
public class controller {
  @Autowired
  AuthService authService;
  @GetMapping
  public String index(Model model){
    model.addAttribute("something", "Hello from Thymeleaf!");
    return "index";
  }
  @GetMapping("/dashboard")
  public String dashboard(){
    return "dashboard";
  }
  @GetMapping("/welcome")
  public String welcome(){
    return "welcome";
  }
}
