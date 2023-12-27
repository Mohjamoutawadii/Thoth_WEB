package com.dentsbackend.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dentsbackend.entities.Professor;
import com.dentsbackend.entities.Role;
import com.dentsbackend.repositories.ProfessorRepository;
import com.dentsbackend.repositories.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin")
public class ProfessorController {

  @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  RoleRepository roleRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private UserDetailsService userDetailsService;

  @GetMapping
  public String admin(Model model,Authentication authentication) {
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      model.addAttribute("userdetail", userDetails);
  }
    List<Professor> professors = professorRepository.findAll();
    List<String> encodedPhotos = new ArrayList<>();
    for (Professor professor : professors) {
      byte[] photo = professor.getPhoto();
      if (photo != null && photo.length > 0) {
        String encodedPhoto = Base64.getEncoder().encodeToString(photo);
        encodedPhotos.add(encodedPhoto);
      } else {
        encodedPhotos.add("");
      }
    }
    model.addAttribute("professors", professors);
    model.addAttribute("encodedPhotos", encodedPhotos);
    return "admin";
  }

  @GetMapping("/add")
  public String showAddForm(Model model,Authentication authentication) {
     if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      model.addAttribute("userdetail", userDetails);
  }
    List<Professor> professors = professorRepository.findAll();
    List<String> encodedPhotos = new ArrayList<>();
    for (Professor professor : professors) {
      byte[] photo = professor.getPhoto();
      if (photo != null && photo.length > 0) {
        String encodedPhoto = Base64.getEncoder().encodeToString(photo);
        encodedPhotos.add(encodedPhoto);
      } else {
        encodedPhotos.add("");
      }
    }
    model.addAttribute("professors", professors);
    model.addAttribute("encodedPhotos", encodedPhotos);
    model.addAttribute("mode", "add");
    model.addAttribute("professor",new Professor());
    return "admin";
  }

  @PostMapping("/addProf")
  public String save(Professor professor, Model model, @RequestParam("file") MultipartFile photoFile) {
    Optional<Role> role=roleRepository.findByName("ROLE_PROFESSOR");
    Role roole=role.get();
    professor.setRoles(Set.of(roole));

   
    String a = passwordEncoder.encode(professor.getPassword());
    professor.setPassword(a);
    if (photoFile != null && !photoFile.isEmpty()) {
      try {
        byte[] photoBytes = photoFile.getBytes();
        professor.setPhoto(photoBytes);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    professorRepository.save(professor);

    return "redirect:/admin";
  }

  @GetMapping("/edit/{id}")
  public String editProfesssor(@PathVariable("id") long id, Model model,Authentication authentication) {
     if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      model.addAttribute("userdetail", userDetails);
  }
    Professor professor = professorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    if (professor.getPhoto() != null) {
      String encodedPhoto = Base64.getEncoder().encodeToString(professor.getPhoto());
      model.addAttribute("encodedPhoto", encodedPhoto);

    }
    List<Professor> professors = professorRepository.findAll();
    List<String> encodedPhotos = new ArrayList<>();
    for (Professor professor1 : professors) {
      byte[] photo = professor1.getPhoto();
      if (photo != null && photo.length > 0) {
        String encodedPhoto = Base64.getEncoder().encodeToString(photo);
        encodedPhotos.add(encodedPhoto);
      } else {
        encodedPhotos.add("");
      }
    }
    model.addAttribute("professors", professors);
    model.addAttribute("encodedPhotos", encodedPhotos);
    model.addAttribute("professor", professor);
    model.addAttribute("mode", "update");

    return "admin";
  }

  @PostMapping("/update/{id}")
  public String updateProfessor(@PathVariable("id") long id, Professor professor, Model model,
      @RequestParam("file") MultipartFile photoFile) {
        Optional<Role> role=roleRepository.findByName("ROLE_PROFESSOR");
    Role roole=role.get();
    professor.setRoles(Set.of(roole));
  
  
    professor.setId(id);
    String a = passwordEncoder.encode(professor.getPassword());
    professor.setPassword(a);
    if (photoFile != null && !photoFile.isEmpty()) {
      try {
        byte[] photoBytes = photoFile.getBytes();
        professor.setPhoto(photoBytes);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    professorRepository.save(professor);

    return "redirect:/admin";
  }

  @GetMapping("/delete/{id}")
  public String deleteProfessor(@PathVariable("id") long id, Model model,Authentication authentication) {
     try{if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      model.addAttribute("userdetail", userDetails);
  }
   
    Professor professor = professorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    professorRepository.delete(professor);

    return "redirect:/admin";
  }catch(Exception e){
    e.printStackTrace();
    return "error500";
  }
}

}
