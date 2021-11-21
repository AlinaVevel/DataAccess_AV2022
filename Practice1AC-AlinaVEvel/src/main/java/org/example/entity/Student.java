package org.example.entity;

/**
 * Class student with attributes <b>id</b>, <b>name</b>, <b>lastname</b>, <b>email</b>, <b>number</b>
 * @autor Alina Vevel
 *
 */
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
    public Student(){}

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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
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
