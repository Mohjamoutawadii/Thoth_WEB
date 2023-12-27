package com.dentsbackend.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dentsbackend.entities.Professor;
import com.dentsbackend.entities.Role;
import com.dentsbackend.entities.User;
import com.dentsbackend.repositories.ProfessorRepository;
import com.dentsbackend.repositories.UserRepository;
import com.dentsbackend.services.CustomUserDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

    @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  private UserDetailsService userDetailsService;

 
  


  @GetMapping("/login")
  public String login(Model model,User user ) {

    model.addAttribute("user", user);
      return "login";
  }
  

  @GetMapping("/check")
  public String role(Authentication authentication) {
    CustomUserDetails user  = (CustomUserDetails)authentication.getPrincipal();
    boolean isAdmin = false;
    for (Role role : user.getRoles()){
      if(role.getName().equals("ROLE_ADMIN")){
        isAdmin = true;
      }
    }
      if(isAdmin){
        return "redirect:/admin";
      }else{
        return "redirect:/prof";
      }
  }
  

  
}
