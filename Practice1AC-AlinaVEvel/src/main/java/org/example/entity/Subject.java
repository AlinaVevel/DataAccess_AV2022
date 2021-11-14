package org.example.entity;

public class Subject {

    private int code;
    private String name;
    private int year;
    private int hours;
    private int course_id;


    public Subject(int code, String name, int year, int hours, int course_id) {
        this.code = code;
        this.name = name;
        this.year = year;
        this.hours = hours;
        this.course_id = course_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
