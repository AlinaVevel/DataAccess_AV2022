package org.example;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "postgres";
        String password = "1101739";
        String inputName;
        int inputYear;
        int inputHours;
        Connection con = null;
        Scanner scanner = new Scanner(System.in);
        try {
            con = DriverManager.getConnection(url, user,password);
            PreparedStatement ps = con.prepareStatement("Insert into subjects (name, year, hourse ) values( ?, ?,? )");


            String exit = " ";


            while (!exit.equals("n")) {

                System.out.print("Input name ");
                inputName = scanner.nextLine();
                if(inputName.isEmpty()){
                    break;
                }
                System.out.print("Input year ");
                inputYear = Integer.parseInt(scanner.nextLine());

                System.out.print("Input hourse ");
                inputHours = Integer.parseInt(scanner.nextLine());


                ps.setString(1, inputName);
                ps.setInt(2, inputYear);
                ps.setInt(3, inputHours);

                System.out.println("If you want to exit, press n");
                exit = scanner.nextLine();

                ps.executeUpdate();



            }

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
