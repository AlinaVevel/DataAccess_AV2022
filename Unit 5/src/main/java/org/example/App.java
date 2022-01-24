package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    static Session openSession() throws Exception {
        SessionFactory sessionFactory =
                new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            throw new Exception("Error opening session!");
        }
        return session;

    }

    public static void main(String[] args) {
        SessionFactory sessionFactory =
                new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Session successfully opened!");
        } else {
            System.out.println("Error opening session!");
        }

        Query<EmployeeEntity2> myQuery =
                session.createQuery("from org.example.EmployeeEntity2");
        List<EmployeeEntity2> employees = myQuery.list();
        for (Object employeeObject : employees) {
            EmployeeEntity2 employee = (EmployeeEntity2) employeeObject;
            System.out.printf("Number : %d Name: %s \r\n", employee.getEmpno(),
                    employee.getEname());
        }

        //updateEmployee(7521);
        //insertDepartment();
        deleteEmployee(7521);


    }

    public static void deleteEmployee( int employeeNumber ) {
        try ( Session session = openSession() ) {
            Query<EmployeeEntity> myQuery =
                    session.createQuery("from org.example." + "EmployeeEntity where empno='"+String.valueOf(employeeNumber)+ "' ");
            List<EmployeeEntity> employees = myQuery.list();
            if ( employees.size() > 0 ) {
                Transaction transaction = session.beginTransaction();
                session.delete(employees.get(0));
                transaction.commit(); // End of transaction
                System.out.println("The employee has been deleted.");
            }
            else
                System.out.println("Employee not found.");
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }


    public static void updateEmployee(int employeeNumber) {
        try (Session session = openSession()) {
            String sqlString = "from org.example." +
                    "EmployeeEntity2 where empno='" + String.valueOf(employeeNumber) + "' ";
            Query<EmployeeEntity2> myQuery =
                    session.createQuery(sqlString );
            List<EmployeeEntity2> employees = myQuery.list();
            Transaction transaction = session.beginTransaction();
            EmployeeEntity2 employee = (EmployeeEntity2) employees.get(0);
            employee.setDeptno(40);
            session.update(employee);
            transaction.commit(); // End of transaction
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void insertDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Department name?: ");
        String dname = scanner.nextLine();
        System.out.print("Department location?: ");
        String dloc = scanner.nextLine();
        try ( Session session = openSession() ) {
            Transaction transaction = session.beginTransaction();
            DeptEntity department = new DeptEntity();
            department.setDname( dname );
            department.setLoc( dloc );
            session.save( department );
            transaction.commit(); // End of transaction
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }

}
