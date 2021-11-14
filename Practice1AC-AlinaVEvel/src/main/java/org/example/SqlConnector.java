package org.example;


import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Subject;

import java.sql.*;
import java.util.ArrayList;


//class for working with BD
public class SqlConnector {

    private static final String url = "jdbc:postgresql://localhost:5432/VTInstitute";
    private static final String user = "postgres";
    private static final String password = "1101739";

    private Statement statement;
    Connection conn;

    private SqlConnector() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SqlConnector instance = null;

    //function for creating instance, its for not creating to many objects(copies)
    public static SqlConnector getInstance() {
        if (instance == null) {
            instance = new SqlConnector();
        }
        return instance;
    }

    //check if student exist in BD
   public int ifStudentExists(String studentId){
       int result = 0;
       try {

           PreparedStatement statement = conn.prepareStatement("SELECT ifuserexist('"+ studentId + "')");
           ResultSet rs = statement.executeQuery();
           while (rs.next()) {
               result = rs.getInt(1);
           }


       } catch (SQLException e) {
           e.printStackTrace();
       }
       return result;
   }




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

    //function for tab student
    public ArrayList<String> studentsReport(String idStudent){
        ArrayList<String> values = new ArrayList<>();
        String addStirng = null;
        try {

            Statement statment = conn.createStatement();
            String Sentence = "select courses.name as nameC, subjects.name as nameSub, scores.score as score from courses, subjects, scores, enrollment \n" +
                    "\t\t\twhere subjects.course_id = courses.code and scores.subjectid = subjects.code \n" +
                    "\t\t\tand enrollment.student ='" + idStudent + "'";
            ResultSet rs = statment.executeQuery(Sentence);
            while (rs.next()) {
               addStirng =  rs.getString(1) + " - " +
                        rs.getString(2)  + " : " + "\t" + rs.getInt(3);
               values.add(addStirng);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    public void inserEnrollment(String studentId, int courseId){
        try {
            PreparedStatement statement = conn.prepareStatement("CALL enrollstudent('"+ studentId + "' , " + courseId + ")");

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int checkStudentForenrollment(String idStudent){
        int result = 0;
        try {

            PreparedStatement statement = conn.prepareStatement("SELECT studenscore('"+ idStudent + "')");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //function for checking if student enroll in the same course
    public int checkStudentInEnrollment(String idStudent, int courseId){
        int result = 0;
        try {

            PreparedStatement statement = conn.prepareStatement("SELECT studentenrollsamecourse('"+ idStudent + "'  , " + courseId + ")");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



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

    public int getIdCourse(String nameCourse){
        int code = 0;
        for(int i = 0; i < getCourses().size(); i++){
            if(getCourses().get(i).getName().equals(nameCourse)){
                code =  getCourses().get(i).getIdCourse();
            }
        }
        return code;
    }

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


}
