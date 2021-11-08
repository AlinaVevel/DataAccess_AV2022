package org.example.entity;


public class Student {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String number;

    public Student(String id, String name, String lastName, String email, String number) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
