package org.example;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.scene.MainControllerStudents;
import org.xml.sax.SAXException;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class SqlConnector with attributes <b>url</b>, <b>user</b>, <b>password</b>, <b>statement</b>, <b>conn</b>
 *
 */
public class SqlConnector {



    private static final String url = "jdbc:postgresql://localhost:5432/VTInstitute";
    private static final String user = "postgres";
    private static final String password = "1101739";
    private static SqlConnector instance = null;
    private Statement statement;
    /**
     * The instance of class connection.
     */
    Connection conn;


    /**
     * Constructor - get new object
     *
     * @see #SqlConnector #SqlConnector#SqlConnector()
     *
     */
    protected SqlConnector() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Method for instance, if instance is null get new one {@link SqlConnector#instance}
     *
     * @return the instance of object
     */
    public static SqlConnector getInstance() {
        if (instance == null) {
            instance = new SqlConnector();
        }
        return instance;
    }

    /**
     * Method that return if student exists or not. This method use a SQL connection to connect to DB and use a stored function
     *
     * @param studentId the student id
     * @return integer result 0 if not exists or -1 if exists
     *
     */
    public int ifStudentExists(String studentId) {
        int result = 0;
        try {

            PreparedStatement statement = conn.prepareStatement("SELECT ifuserexist('" + studentId + "')");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method that insert the student in data base using PreparedStatement and stored function in DB
     *
     * @param student the instance of the student class
     *  SQLExceprion if an error occurred
     */
    public void insertStudent(Student student) {
        try {
            PreparedStatement pr = conn.prepareStatement("INSERT INTO students(idcard, firstname, lastname, email, phone) VALUES(?,?,?,?,?)");
            pr.setString(1, student.getId());
            pr.setString(2, student.getName());
            pr.setString(3, student.getLastName());
            pr.setString(4, student.getEmail());
            pr.setString(5, student.getNumber());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method looking for dates in database and add this data in ArrayList
     *
     * @param idStudent the id student
     * @return ArrayList of students with course name, subject name and scores
     */
    public ArrayList<String> studentsReport(String idStudent) {
        ArrayList<String> values = new ArrayList<>();
        String addStirng = null;
        try {

            Statement statment = conn.createStatement();
            String Sentence = "select courses.name as nameC, subjects.name as nameSub, scores.score as score from courses, subjects, scores, enrollment \n" +
                    "\t\t\twhere subjects.course_id = courses.code and scores.subjectid = subjects.code and scores.enrollmentid = enrollment.code \n" +
                    "\t\t\tand enrollment.student ='" + idStudent + "'";
            ResultSet rs = statment.executeQuery(Sentence);
            while (rs.next()) {
                addStirng = rs.getString(1) + " - " +
                        rs.getString(2) + " : " + "\t" + rs.getInt(3);
                values.add(addStirng);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    /**
     * Method that insert new enrollment in database
     *
     * @param studentId the student id
     * @param courseId  the course id
     */
    public void insertEnrollment(String studentId, int courseId) {
        MainControllerStudents ms = new MainControllerStudents();
        try {
            PreparedStatement statement = conn.prepareStatement("CALL enrollstudent('" + studentId + "' , " + courseId + ")");

            statement.executeUpdate();
            ms.AlterDialogConfirmation("The student has successfully enroll", "DONE");

        } catch (SQLException e) {
            ms.AlterDialogError("Error",e.getMessage());
        }

    }

    /**
     * Method that call function from data base and check if student has pending scores
     *
     * @param idStudent the id student
     * @return integer result 0 if all ok or -1 if has pending scores
     */
    public int checkStudentForenrollment(String idStudent) {
        int result = 0;
        try {

            PreparedStatement statement = conn.prepareStatement("SELECT studenscore('" + idStudent + "')");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method that is checking if student is in table enrollment with the same course where the student will enroll, in data base. That method use
     * PreparedStatement and stored function in DB
     * @param idStudent the id student
     * @param courseId  the course id
     * @return integer result 0 if cann,  or -1 if the course its the same
     */
    public int checkStudentInEnrollment(String idStudent, int courseId) {
        int result = 0;
        try {

            PreparedStatement statement = conn.prepareStatement("SELECT studentenrollsamecourse('" + idStudent + "'  , " + courseId + ")");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method that add to ArrayList all courses from data base table courses
     *
     * @return ArrayList of all courses that are in DB
     */
    public ArrayList<Course> getCourses() {
        String query = "SELECT * FROM courses";
        ArrayList<Course> listaData = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listaData.add(readCourses(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaData;
    }

    /**
     * Method that is looking in array id course
     *
     * @param nameCourse the name course
     * @return the id of the course
     */
    public int getIdCourse(String nameCourse) {
        int code = 0;
        for (int i = 0; i < getCourses().size(); i++) {
            if (getCourses().get(i).getName().equals(nameCourse)) {
                code = getCourses().get(i).getIdCourse();
            }
        }

        return code;
    }

    /**
     * Method that add to ArrayList all students from data base table students
     *
     * @return ArrayList of students
     */
    public ArrayList<Student> getStudents() {
        String query = "SELECT * FROM students";
        ArrayList<Student> listaData = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                listaData.add(readStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaData;
    }


    private Course readCourses(ResultSet rs) throws SQLException {
        int id = rs.getInt(rs.findColumn("code"));
        String name = rs.getString(rs.findColumn("name"));

        return new Course(id, name);
    }

    private Student readStudent(ResultSet rs) throws SQLException {
        String id = rs.getString(rs.findColumn("idcard"));
        String name = rs.getString(rs.findColumn("firstname"));
        String lastName = rs.getString(rs.findColumn("lastname"));
        String email = rs.getString(rs.findColumn("email"));
        String phone = rs.getString(rs.findColumn("phone"));

        return new Student(id, name, lastName, email, phone);
    }

    /**
     * Method that read dates from XML and insert all data in database, if this transaction produced an error,
     * rollback if not commit
     *
     * @param file the file
     * @return boolean true if the transaction was successful
     * @throws SAXException if sax has an error
     */
    public boolean transactionXML(File file) throws SAXException {
        MainControllerStudents ms = new MainControllerStudents();
        boolean transaction = true;
        MyRunner runner = new MyRunner();
        List<Student> lines;
        List<Course> linesCourse;
        List<Subject> lineSubject;



        PreparedStatement st = null;
        try {
            try {
                lines = runner.parseStudent(file);
                linesCourse = runner.parseCourse(file);
                lineSubject = runner.parseSubject(file);
                conn.setAutoCommit(false);
                st = conn.prepareStatement("insert into students(idcard, firstname,lastname,email,phone) values(?,?,?,?,?)");
                for (int i = 0; i < lines.size(); i++) {
                    st.setString(1, lines.get(i).getId());
                    st.setString(2, lines.get(i).getName());
                    st.setString(3, lines.get(i).getLastName());
                    st.setString(4, lines.get(i).getEmail());
                    st.setString(5, lines.get(i).getNumber());
                    st.executeUpdate();
                }

                st = conn.prepareStatement("insert into courses(code, name) values(?,?)");
                for (int i = 0; i < linesCourse.size(); i++) {
                    st.setInt(1, linesCourse.get(i).getIdCourse());
                    st.setString(2, linesCourse.get(i).getName());
                    st.executeUpdate();
                }

                st = conn.prepareStatement("insert into subjects(code, name,year,hourse,course_id) values(?,?,?,?,?)");
                for (int i = 0; i < lineSubject.size(); i++) {
                    st.setInt(1, lineSubject.get(i).getCode());
                    st.setString(2, lineSubject.get(i).getNameSubject());
                    st.setInt(3, lineSubject.get(i).getYear());
                    st.setInt(4, lineSubject.get(i).getHours());
                    st.setInt(5, lineSubject.get(i).getCourse_id());
                    st.executeUpdate();
                }
                conn.commit();
                ms.AlterDialogConfirmation("Thansaction was successful", "DONE");

            } catch (SQLException e) {
                try {
                    conn.rollback();
                    transaction = false;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                String error = e.getMessage();
                ms.AlterDialogError("Error", error);
            }
            }catch(Exception e){
                ms.AlterDialogError("Error", e.getMessage());
                transaction = false;
            }





        return transaction;

    }
}















