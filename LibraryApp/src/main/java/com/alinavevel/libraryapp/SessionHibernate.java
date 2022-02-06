package com.alinavevel.libraryapp;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;
import java.util.logging.Level;

public class SessionHibernate {
    private static SessionHibernate instance = null;

    //METHOD TO SUPPRESS HIBERNATE WARNING MESSAGES AND RUN SESSION FACTORY
    public Session openSession() throws Exception {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger =
                org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory sessionFactory =
                new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session == null) {
            throw new Exception("Error opening session!");
        }
        return session;
    }

    public static SessionHibernate getInstance() {
        if (instance == null) {
            instance = new SessionHibernate();
        }
        return instance;
    }


    public void insertUser(String code, String name, String lastNAme, Date birthDate) {

        try ( Session session = openSession() ) {
            Transaction transaction = session.beginTransaction();
            UsersJPAEntity user = new UsersJPAEntity();
            user.setCode(code);
            user.setName(name);
            user.setSurname(lastNAme);
            user.setBirthdate(birthDate);

            session.save( user );

            transaction.commit(); // End of transaction
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
        try {
            openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBook(String isbn, String title, int copies, String publisher){
        try ( Session session = openSession() ) {
            Transaction transaction = session.beginTransaction();
            BooksJPAEntity book = new BooksJPAEntity();
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setCopies(copies);
            book.setPublisher(publisher);

            session.save( book );

            transaction.commit(); // End of transaction
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
        try {
            openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UsersJPAEntity getUserById(String user_id) {

        Session session = null;
        UsersJPAEntity userToReturn = new UsersJPAEntity();
        try {
            session = openSession();
            Query<UsersJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.UsersJPAEntity");
            List<UsersJPAEntity> employees = myQuery.list();
            for ( Object employeeObject : employees ) {
                UsersJPAEntity user = (UsersJPAEntity) employeeObject;
                if(user.getCode().equals(user_id)) {
                    userToReturn = user;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return userToReturn;
    }

    public BooksJPAEntity getBookById(String isbn){
        Session session = null;
        BooksJPAEntity bookToReturn = new BooksJPAEntity();
        try {
            session = openSession();
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.BooksJPAEntity");
            List<BooksJPAEntity> books = myQuery.list();
            for ( Object bookObject : books ) {
                BooksJPAEntity book = (BooksJPAEntity) bookObject;
                if(book.getIsbn().equals(isbn)) {
                    bookToReturn =book;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return bookToReturn;
    }

    public boolean updatingUser(String codeUp, String name, String surname, Date birthDate){
        boolean result = false;
        try ( Session session = openSession() ) {
            Query<UsersJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "UsersJPAEntity where code='"
                            + codeUp + "'");
            List<UsersJPAEntity> users = myQuery.list();
            Transaction transaction = session.beginTransaction();
            UsersJPAEntity user = (UsersJPAEntity) users.get(0);
            user.setCode(codeUp);
            user.setName(name);
            user.setSurname(surname);
            user.setBirthdate(birthDate);
            session.update(user);
            transaction.commit(); // End of transaction
            result = true;
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
        return result;

    }

    public boolean updatingBook(String isbn, String title, int copies, String publisher){
        boolean result = false;
        try ( Session session = openSession() ) {
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "BooksJPAEntity where isbn='"
                            + isbn + "'");
            List<BooksJPAEntity> books = myQuery.list();
            Transaction transaction = session.beginTransaction();
            BooksJPAEntity book = (BooksJPAEntity) books.get(0);
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setPublisher(publisher);
            book.setCopies(copies);
            session.update(book);
            transaction.commit(); // End of transaction
            result = true;
        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
        return result;
    }
}
