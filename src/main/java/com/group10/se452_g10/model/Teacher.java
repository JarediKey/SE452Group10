package com.group10.se452_g10.model;

import java.util.Date;
import java.util.UUID;

public class Teacher {

    private UUID teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private Long teacherNumber;
    private String teacherAddress;

    private Long studentSSN;
    private Date teacherdob;

    private String teacherGender;


    public Teacher(String firstName, String lastName) {
        this.teacherId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = firstName.substring(0,2) +
                lastName.substring(0,2) + "@depaul.edu";


    }
}
