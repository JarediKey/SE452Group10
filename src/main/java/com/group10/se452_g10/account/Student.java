package com.group10.se452_g10.account;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "students")
public class Student extends User {

    protected String guardianName;
    protected int guardianNumber;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void setGuardianNumber(int guardianNumber) {
        this.guardianNumber = guardianNumber;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }
}
