package org.example;

import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * Function exercise
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu();


        String input = scanner.nextLine();
        while (!input.equals("4")) {
            switch (input) {
                case "1":
                    System.out.print("Input job ");
                    input = scanner.nextLine();
                    callFunctionSerchEmployee(input);
                    break;
                case "2":
                    System.out.print("Input department ");
                    input = scanner.nextLine();
                    callFuncEmployeeInDep(Integer.parseInt(input));
                    break;
                case "3":
                    System.out.print("Input pattern ");
                    input = scanner.nextLine();
                    callFunctionSerchEmployeeWitchLetter(input);
                    break;

            }
            Menu();
            input = scanner.nextLine();
        }
    }

    public static void Menu() {
        System.out.println("Menu");
        System.out.println("1. Search for employee with specific job");
        System.out.println("2. Search for employee in specific department");
        System.out.println("3. Search for employee, that start with letter ...");
        System.out.println("4. Exit");
    }

    public static void callFunctionSerchEmployee(String job) {
        Connection conn = null;
        CallableStatement statement = null;
        ResultSet rs = null;
        String toCall = "{call listofemployee(" + "'" + job + "'" + ")}";
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees", "postgres", "1101739");

            statement = conn.prepareCall(toCall);
            rs = statement.executeQuery();
            System.out.println("Name" + "\t" + "Job");

            while (rs.next()) {
                System.out.println(rs.getString(2) + "\t " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(conn);
        }
    }

    public static void callFuncEmployeeInDep(int department) {
        Connection conn = null;
        CallableStatement statement = null;
        ResultSet rs = null;
        String toCall = "{call listofemployeeindepart(" + department + ")}";
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees", "postgres", "1101739");

            statement = conn.prepareCall(toCall);
            rs = statement.executeQuery();
            System.out.println("Name" + "\t" + "Job        " + "\t" + "Department");

            while (rs.next()) {
                System.out.println(rs.getString(2) + "\t " + rs.getString(3) + "\t " + rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(conn);
        }
    }

    public static void callFunctionSerchEmployeeWitchLetter(String letter) {
        Connection conn = null;
        CallableStatement statement = null;
        ResultSet rs = null;
        String toCall = "{call listofemployeewithpattern(" + "'" + letter + "'" + ")}";
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees", "postgres", "1101739");

            statement = conn.prepareCall(toCall);
            rs = statement.executeQuery();
            System.out.println("Name" + "\t" + "Job");

            while (rs.next()) {
                System.out.println(rs.getString(2) + "\t " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(conn);
        }
    }


}
