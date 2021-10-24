package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String SQL_CREATE = "ALTER TABLE COURSES"
                + "("
                + " CODE serial,"
                + " NAME varchar(100) NOT NULL,"
                + " PRIMARY KEY (CODE)"
                + ")";

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VTInstitute", "postgres", "1101739");

            PreparedStatement pr = conn.prepareStatement(SQL_CREATE);

            pr.execute();
            pr.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
