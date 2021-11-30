package org.example.entity;

/**
 * Class subject with attributes <b>idCourse</b>, <b>name</b>
 *

 */
public class Course {
    private int idCourse;
    private String name;


    /**
     * Constructor of a new Course with specific parameters
     *
     * @param idCourse the id course
     * @param name     the name
     * @see Course#Course()
     */
    public Course(int idCourse, String name) {
        this.idCourse = idCourse;
        this.name = name;
    }

    /**
     * Default constructor of a new Course.
     * @see Course#Course()
     */
    public Course(){}

    /**
     * Sets id course. {@link Course#idCourse}
     *
     * @param idCourse the id course
     */
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    /**
     * Sets name. Gets name. {@link Course#name}
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id course. Gets name. {@link Course#idCourse}
     *
     * @return the id course
     */
    public int getIdCourse() {
        return idCourse;
    }

    /**
     * Gets name. {@link Course#name}
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
