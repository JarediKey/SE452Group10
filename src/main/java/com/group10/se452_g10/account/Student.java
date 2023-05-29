package com.group10.se452_g10.account;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "students")
public class Student extends User {



    protected String guardian_name;
    protected int guardian_number;

    protected long gpa;

    public Student() {
    }

    public Student(String firstName, String lastName, long age) {
        super(firstName, lastName, age);
    }

    public void setGuardian_number(int guardian_number) {
        this.guardian_number = guardian_number;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public void setGpa(long gpa) {
        this.gpa = gpa;
    }
}
