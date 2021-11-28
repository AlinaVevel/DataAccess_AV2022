package org.example.entity;


/**
 * Class subject with attributes <b>code</b>, <b>nameSubject</b>, <b>year</b>, <b>hours</b>, <b>course_id</b>

 *
 */
public class Subject {

    private int code;
    private String nameSubject;
    private int year;
    private int hours;
    private int course_id;


    /**
     * Method to get subject's id {@link Subject#code}
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Method to get subject's name {@link Subject#nameSubject}
     *
     * @return the name subject
     */
    public String getNameSubject() {
        return nameSubject;
    }

    /**
     * Method to set subject's id {@link Subject#nameSubject}
     *
     * @param nameSubject the name subject
     */
    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    /**
     * Method to get subject's year {@link Subject#year}
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Method to set subject's year {@link Subject#year}
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Method to get subject's hours {@link Subject#hours}
     *
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Method to set subject's hours {@link Subject#hours}
     *
     * @param hours the hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * Method to get id from table course{@link Subject#course_id}
     *
     * @return the course id
     */
    public int getCourse_id() {
        return course_id;
    }

    /**
     * Sets course id.
     *
     * @param course_id the course id
     */
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
