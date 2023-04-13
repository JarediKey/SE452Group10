package com.group10.se452_g10.model;

import java.util.ArrayList;

public class Teacher extends User {

    public ArrayList<Classes> class_list;
    public Teacher(String t_firstName, String t_lastName) {
        super(t_firstName, t_lastName);

    }
    public void setClasses(ArrayList<Classes>  classes) {

        this.class_list = classes;
    }

    public Classes getClasslist(){
       return class_list.get(1);
    }
}
