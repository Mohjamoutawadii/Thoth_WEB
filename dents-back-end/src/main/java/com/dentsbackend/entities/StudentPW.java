package com.dentsbackend.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPW {
    @EmbeddedId
    private StudentPWPK id;
    @Lob
    private byte[] imageFront;
    private String resultat;
    private Date date;
    private String time;
    private String Validation;
    private String remarque;
    @ManyToOne
    @JoinColumn(name="student_id",referencedColumnName="id",insertable = false,updatable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name="pw_id",referencedColumnName="id",insertable = false,updatable = false)
    private PW pw;
}
