package com.group10.se452_g10.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Teacher extends User {

    private List<String> classes;
    public Teacher(String t_firstName, String t_lastName) {
        super(t_firstName, t_lastName);

    }
    public void setClasses(List<String> classes) {

        this.classes = classes;
    }
}
