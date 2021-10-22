package org.example;

import org.apache.commons.dbutils.DbUtils;

import java.sql.*;

/**
 Exercise 4.1.1 with modification 5.1
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "1101739";
        Connection con = null;
        con = DriverManager.getConnection(url, user,
                password);
        Statement statement = con.createStatement();
        String SQLsentence = "SELECT * FROM subjects ORDER BY code";
        ResultSet rs = statement.executeQuery(SQLsentence);
        try {


            System.out.println("Code" + "\t" + "Name" + "\t" + "Year" + "\t" + "Hourse");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t " +
                        rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(con);

        }





    }

    }



