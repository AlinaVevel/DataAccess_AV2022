package org.example;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    SqlConnector connector = SqlConnector.getInstance();


    private List<Student> students = new ArrayList<>();
    private Student student;

    private List<Course> courses = new ArrayList<>();
    private Course course;

    private List<Subject> subjects = new ArrayList<>();
    private Subject subject;

    private StringBuilder builder = new StringBuilder();

    private int code;
    private String name;
    private int year;
    private int hours;
    private int courseId;


    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        switch (qName) {
            case "student":
                student = new Student();
                student.setId(attributes.getValue("id"));
                students.add(student);
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
                course.setIdCourse(Integer.parseInt(attributes.getValue("id")));
                courses.add(course);
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

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        switch (qName) {
            case "firstname":
                student.setName(builder.toString());
                break;
            case "lastname":
                student.setLastName(builder.toString());
                break;
            case "email":
                student.setEmail(builder.toString());
                break;
            case "phone":
                student.setNumber(builder.toString());
                break;
        }

        switch (qName) {

            case "name":
                course.setName(builder.toString());
                break;
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

    public List<Student> getStudents() {

        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Subject> getSubject() {
        return subjects;
    }


}
