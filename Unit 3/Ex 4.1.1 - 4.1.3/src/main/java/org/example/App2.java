package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App2 {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "1101739";
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user,
                    password);
            Statement statement = con.createStatement();
            statement.executeUpdate("ALTER TABLE subjects ADD COLUMN hourse INT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
