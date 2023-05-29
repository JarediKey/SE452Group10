package com.group10.se452_g10.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@Entity(name = "teachers")
public class Teacher extends User {


    public Teacher(){

    }
    public Teacher(String firstName, String lastName, long age) {
        super(firstName, lastName, age);
    }
}
