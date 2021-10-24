package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String SQL_CREATE = "ALTER TABLE SUBJECTS ADD COURSE_ID INT";
        String SQL_UPDATE ="ALTER TABLE SUBJECTS ADD CONSTRAINT fk_course_id FOREIGN KEY (COURSE_ID) REFERENCES COURSES(CODE)";






        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VTInstitute", "postgres", "1101739");

            PreparedStatement pr = conn.prepareStatement(SQL_CREATE);
            conn.prepareStatement(SQL_UPDATE);


            pr.executeUpdate();
            pr.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
