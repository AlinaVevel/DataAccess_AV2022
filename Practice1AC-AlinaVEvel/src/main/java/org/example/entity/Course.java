package org.example.entity;

public class Course {
    private int idCourse;
    private String name;


    public Course(int idCourse, String name) {
        this.idCourse = idCourse;
        this.name = name;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public String getName() {
        return name;
    }
}
