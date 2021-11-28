package org.example;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.scene.MainControllerStudents;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * The class My handler.
 */
public class MyHandler extends DefaultHandler {

    /**
     * The Connector.
     */
    SqlConnector connector = SqlConnector.getInstance();


    private List<Student> students = new ArrayList<>();
    private Student student;

    private String nameStudent;
    private String lastNameStudent;
    private String idCardStudent;
    private String emailStudent;
    private String phoneStudent;

    private List<Course> courses = new ArrayList<>();
    private Course course;

    private int idCourse;
    private String nameCourse;

    private List<Subject> subjects = new ArrayList<>();
    private Subject subject;

    private int code;
    private String name;
    private int year;
    private int hours;
    private int courseId;


    private StringBuilder builder = new StringBuilder();



    /**
     * Method that open a tag
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     */
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        switch (qName) {
            case "student":
                student = new Student();
                idCardStudent = attributes.getValue("id");

                break;
            case "firstname":
                builder.delete(0, builder.length());
                break;
            case "lastname":
                builder.delete(0, builder.length());
                break;
            case "email":
                builder.delete(0, builder.length());
                break;
            case "phone":
                builder.delete(0, builder.length());
                break;
        }

        switch (qName) {
            case "course":
                course = new Course();
                idCourse = Integer.parseInt(attributes.getValue("id"));

                break;
            case "name":
                builder.delete(0, builder.length());
                break;
        }

        switch (qName) {
            case "subject":
                subject = new Subject();
                code = Integer.parseInt(attributes.getValue("id"));
                courseId = Integer.parseInt(attributes.getValue("course"));
                break;
            case "name":
                builder.delete(0, builder.length());
                break;
            case "year":
                builder.delete(0, builder.length());
                break;
            case "hours":
                builder.delete(0, builder.length());
                break;
        }
    }

    /**
     * Method that received the valor of tag
     *
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        switch (qName) {
            case "firstname":
                nameStudent = builder.toString();
                break;
            case "lastname":
                lastNameStudent = builder.toString();
                break;
            case "email":
                emailStudent = builder.toString();
                break;
            case "phone":
                phoneStudent = builder.toString();
                break;
            case "student":
                student.setId(idCardStudent);
                student.setName(nameStudent);
                student.setLastName(lastNameStudent);
                student.setEmail(emailStudent);
                student.setNumber(phoneStudent);
                students.add(student);
        }

        switch (qName) {

            case "name":
                nameCourse = builder.toString();
                break;
            case "course":
                course.setIdCourse(idCourse);
                course.setName(nameCourse);
                courses.add(course);
        }

        switch (qName) {
            case "name":
                name = builder.toString();
                break;
            case "year":
                year = Integer.parseInt(builder.toString());
                break;
            case "hours":
                hours = Integer.parseInt(builder.toString());
                break;
            case "subject":
                subject.setCode(code);
                subject.setCourse_id(courseId);
                subject.setNameSubject(name);
                subject.setYear(year);
                subject.setHours(hours);
                subjects.add(subject);
                break;
        }
    }

    /**
     * Gets students.
     *
     * @return the list of students
     */
    public List<Student> getStudents() {

        return students;
    }

    /**
     * Gets courses.
     *
     * @return the courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Gets subject.
     *
     * @return the subject
     */
    public List<Subject> getSubject() {
        return subjects;
    }


}
