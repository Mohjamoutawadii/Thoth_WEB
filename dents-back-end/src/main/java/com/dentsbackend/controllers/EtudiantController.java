package com.dentsbackend.controllers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dentsbackend.entities.Groupe;
import com.dentsbackend.entities.PW;
import com.dentsbackend.entities.Professor;
import com.dentsbackend.entities.Student;
import com.dentsbackend.entities.StudentPW;
import com.dentsbackend.entities.StudentPWPK;
import com.dentsbackend.entities.User;
import com.dentsbackend.repositories.GroupeRepository;
import com.dentsbackend.repositories.PWRepository;
import com.dentsbackend.repositories.StudentPWRepository;
import com.dentsbackend.repositories.StudentRepository;
import com.dentsbackend.repositories.UserRepository;
import com.dentsbackend.services.CustomUserDetails;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController {

    @Autowired
    PWRepository PWRepository;

    @Autowired
    GroupeRepository groupeRepository;

    @Autowired
    StudentRepository sr;

    @Autowired
    UserRepository ur;

    @Autowired
    GroupeRepository gr;

    @Autowired
    StudentPWRepository spr;

    @Autowired
    PWRepository pwr;

    @PostMapping("/register")
    public ResponseEntity<Object> ajouterService(@RequestBody Student student) {
        Student st = sr.findByUserName(student.getUserName());
        if (!(student.getPassword().equals(st.getPassword()))) {
            return new ResponseEntity<Object>("User Name ou Password Incorrect",
                    HttpStatus.FORBIDDEN);

        } else {

            return ResponseEntity.ok(st);
        }

    }

    @PostMapping("/check")
    public ResponseEntity<String> check(@RequestParam("email") String email) {
        try {
            List<User> users = ur.findAll();

            for (User user : users) {
                if (user.getEmail().equals(email)) {

                    return ResponseEntity.ok("Email trouvé dans la base de données");
                }
            }
            return ResponseEntity.ok("Email non trouvé dans la base de données");

        } catch (Exception e) {

            return new ResponseEntity<>("Erreur lors de la recherche dans la base de données",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<PW> getPWByGroupe(@RequestParam("code") String code) {
        return pwr.findByGroupesCode(code);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody Student student) {

        if (student.getPassword() != null) {
            Student st = sr.findByEmail(student.getEmail());
            st.setPassword(student.getPassword());
            return ResponseEntity.ok(sr.save(st));

        } else {
            return new ResponseEntity<>("Requête invalide : le mot de passe est null", HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/changeProfil")
    public ResponseEntity<Object> changeProfil(@RequestBody Student student, @RequestParam("email") String email) {
        if (student.getEmail() != null && student.getFirstName() != null && student.getLastName() != null
                && student.getUserName() != null) {
            Student st = sr.findByEmail(email);

            if (st != null) {
                st.setEmail(student.getEmail());
                st.setFirstName(student.getFirstName());
                st.setLastName(student.getLastName());
                st.setUserName(student.getUserName());
                if (student.getPhoto() != null) {
                    st.setPhoto(student.getPhoto());
                }
                return ResponseEntity.ok(sr.save(st));
            } else {
                return new ResponseEntity<>("Aucun étudiant trouvé avec cet e-mail", HttpStatus.FORBIDDEN);
            }
        } else {

            return new ResponseEntity<>("Requête invalide : Les champs ne peuvent pas être vides",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveStudentPW")
    public ResponseEntity<StudentPW> ajouterStudentPW(@RequestBody StudentPW studentPW) {
        try {
            if (studentPW != null && studentPW.getStudent() != null && studentPW.getPw() != null) {

                StudentPWPK studentPWPK = new StudentPWPK();
                studentPWPK.setStudent_id(studentPW.getStudent().getId());
                studentPWPK.setPw_id(studentPW.getPw().getId());
                studentPW.setId(studentPWPK);

                StudentPW savedStudentPW = spr.save(studentPW);

                return ResponseEntity.ok(savedStudentPW);
            } else {

                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/studentpw")
    public List<StudentPW> getPw(@RequestParam("id") Long id) {

        List<StudentPW> studentpws = spr.findByStudentId(id);
        return studentpws;

    }

   


}
