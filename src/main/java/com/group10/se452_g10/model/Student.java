package com.group10.se452_g10.model;

import java.util.Date;
import java.util.UUID;

public class Student {
    private final UUID studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Long studentNumber;
    private String studentAddress;
    private String gaurdianName;
    private String guardianNumber;
    private Date studentdob;
    private Long studentSSN;
    private String studentGender;


    public Student(String firstName, String lastName) {
        this.studentId  = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = firstName.substring(0,2) +
                lastName.substring(0,2) + "@depaul.edu";
    }
}
