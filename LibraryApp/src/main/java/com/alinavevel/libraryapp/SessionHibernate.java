package com.alinavevel.libraryapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionHibernate {
    private static SessionHibernate instance = null;

    Session openSession() {
        SessionFactory sessionFactory =
                new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            try {
                throw new Exception("Error opening session!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return session;
    }

    public static SessionHibernate getInstance() {
        if (instance == null) {
            instance = new SessionHibernate();
        }
        return instance;
    }


    public void insertUser(Users user) {

        try ( Session session = openSession() ) {
            Transaction transaction = session.beginTransaction();

            session.save( user );
            transaction.commit(); // End of transaction
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
}
