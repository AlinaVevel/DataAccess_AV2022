package org.example;


import org.example.entity.Course;
import org.example.entity.Student;

import java.sql.*;
import java.util.ArrayList;

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

    public static SqlConnector getInstance() {
        if (instance == null) {
            instance = new SqlConnector();
        }
        return instance;
    }

    public boolean ifExist(String id) {

        boolean isUserExists = false;
        try (PreparedStatement ps = conn.prepareStatement("select idcard from students where idcard = id")) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isUserExists = true;

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return isUserExists = false;
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
