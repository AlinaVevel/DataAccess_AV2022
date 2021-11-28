package org.example;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.scene.MainControllerStudents;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type My runner.
 */
public class MyRunner {
    MainControllerStudents ms = new MainControllerStudents();

    private SAXParser createSaxParser() {


        SAXParser saxParser = null;

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            saxParser = factory.newSAXParser();

            return saxParser;
        } catch (ParserConfigurationException | SAXException ex) {

            ms.AlterDialogError("Error", ex.getMessage());
        }

        return saxParser;
    }

    /**
     * the method that Parse student and return student list
     *
     * @param file the file
     * @return the list of students
     * @throws  SAXException
     */
    public List<Student> parseStudent(File file) throws IOException, SAXException {

        MyHandler handler = new MyHandler();

        File xmlDocument = file;



            SAXParser parser = createSaxParser();
            parser.parse(xmlDocument, handler);



         return handler.getStudents();
    }

    /**
     * the method that Parse course and return course list
     *
     * @param file the file
     * @return the list of courses
     * @throws SAXException
     */
    public List<Course> parseCourse(File file) throws IOException, SAXException {

        MyHandler handler = new MyHandler();

        File xmlDocument = file;



            SAXParser parser = createSaxParser();
            parser.parse(xmlDocument, handler);



        return handler.getCourses();
    }

    /**
     * the method that Parse subject and return subject list
     *
     * @param file the file
     * @return the list of subjects
     * @throws SAXException
     */
    public List<Subject> parseSubject(File file) throws SAXException, IOException {

        MyHandler handler = new MyHandler();

        File xmlDocument = file;



            SAXParser parser = createSaxParser();
            parser.parse(xmlDocument, handler);



        return handler.getSubject();
    }



}
