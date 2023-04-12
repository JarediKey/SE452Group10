package com.group10.se452_g10.model;

import java.rmi.StubNotFoundException;
import java.util.ArrayList;

public class Classes {

    private ArrayList<Student> students =new ArrayList<Student>();
    private String class_name;
    private int class_id;
    private int num_students_per_class;
    private int num_registered;


    public Classes(String class_name) {
        this.class_name = class_name;

    }

    public void addStudent(Student aStudent)
    {
        students.add(aStudent);
    }

    public String getClass_name() {
        return class_name;
    }

    public int getClass_id() {
        return class_id;
    }

    public int getNum_students_per_class() {
        return num_students_per_class;
    }

    //Get number of students refisted to course
    public int getNum_registered() {
        num_registered = students.size();
        return num_registered;
    }
}
