package com.group10.se452_g10.model;

import java.util.Date;
import java.util.UUID;

public abstract class User {
    private String firstName;
    private String lastName;
    private final String email;
    private Long phone_Number;
    private String Address;
    private Long SSN;
    private Date dob;
    private String gender;
    private final UUID Id;
    private String gaurdianName;
    private String guardianNumber;


    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = firstName.substring(0, 2) +
                lastName.substring(0, 2) + "@depaul.edu";
        this.Id = UUID.randomUUID();

    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setSSN(Long SSN){
        this.SSN = SSN;
    }
    public void setAddress(String addy){
        this.Address = addy;
    }
    public void setDOB(Date dob){
        this.dob = dob;
    }
    public void setNumber(Long number){
        this.phone_Number = number;
    }

    public void setGaurdianName(String gaurdianName) {
        this.gaurdianName = gaurdianName;
    }
    public void setGuardianNumber(String guardianNumber) {
        this.guardianNumber = guardianNumber;
    }
}
