package org.example.entity;

/**
 * Class student with attributes <b>id</b>, <b>name</b>, <b>lastname</b>, <b>email</b>, <b>number</b>
 * This clase is for creating a new student
 *
 */
public class Student {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String number;


    /**
     * Constructor of class students with specific parameters
     * @param id - id of student
     * @param name -  name of student
     * @param lastName - last name of student
     * @param email - email of student
     * @param number - phone number of student
     * @see Student#Student()
     */
    public Student(String id, String name, String lastName, String email, String number) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
    }

    /**
     * Default constructor     *
     * @see Student#Student()
     */
    public Student(){}

    /**
     * Method to get student's id {@link Student#id}
     * @return the student's id
     */
    public String getId() {
        return id;
    }

    /**
     * Procedure for set id of student {@link Student#id}
     * @param id - the student's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to get student's name {@link Student#name}
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get student's last name {@link Student#lastName}
     * @return the student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to get student's email {@link Student#email}
     * @return the student's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to get student's number {@link Student#number}
     * @return the student's phone number
     */
    public String getNumber() {
        return number;
    }


    /**
     * Procedure for set name of student {@link Student#name}
     * @param name - the student's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Procedure for set last name of student {@link Student#lastName}
     * @param lastName - the student's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Procedure for set email of student {@link Student#email}
     * @param email - the student's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Procedure for set number of student {@link Student#number}
     * @param number - the student's phone number
     */
    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * Method that returns a string representation of the object.
     * @return String - a string representation of the object Student.
     */
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
