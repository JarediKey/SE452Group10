package com.group10.se452_g10.model;

import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;



public class Student extends User {

    public Student(String firstName, String lastName) {
        super(firstName,lastName);

    }


}
