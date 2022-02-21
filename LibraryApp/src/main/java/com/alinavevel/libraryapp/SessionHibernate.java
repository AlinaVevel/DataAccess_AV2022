package com.alinavevel.libraryapp;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SessionHibernate {
    private static SessionHibernate instance = null;
    int maxBook = 3;
    int fine = 15;
    int lendingDays = 30;


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


    public void insertUser(UsersJPAEntity user) throws Exception {

        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit(); // End of transaction


    }

    public void insertBook(BooksJPAEntity book) throws Exception {

        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit(); // End of transaction


    }

    public boolean insertLending(LendingJPAEntity lending, String isbn) {

        try {
            if (updatingCopiesBorrow(isbn)) {
                Session session = openSession();
                Transaction transaction = session.beginTransaction();
                session.save(lending);
                transaction.commit();
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public UsersJPAEntity getUserById(String user_id) throws Exception {

        Session session = null;
        UsersJPAEntity userToReturn = null;

        session = openSession();
        Query<UsersJPAEntity> myQuery =
                session.createQuery("from com.alinavevel.libraryapp.UsersJPAEntity");
        List<UsersJPAEntity> employees = myQuery.list();
        for (Object employeeObject : employees) {
            UsersJPAEntity user = (UsersJPAEntity) employeeObject;
            if (user.getCode().equals(user_id)) {
                userToReturn = user;
            }


        }
        return userToReturn;
    }

    public boolean bookExists(String isbn) {
        Session session = null;

        try {
            session = openSession();
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.BooksJPAEntity");
            List<BooksJPAEntity> books = myQuery.list();
            for (Object bookObject : books) {
                BooksJPAEntity book = (BooksJPAEntity) bookObject;
                if (book.getIsbn().equals(isbn)) {
                    return true;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean bookIsAvailable(String isbn) {
        Session session = null;

        try {
            session = openSession();
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.BooksJPAEntity");
            List<BooksJPAEntity> books = myQuery.list();
            for (Object bookObject : books) {
                BooksJPAEntity book = (BooksJPAEntity) bookObject;
                if (book.getIsbn().equals(isbn) && book.getCopies() >= 1) {
                    return true;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean userExists(String code) {
        Session session = null;

        try {
            session = openSession();
            Query<UsersJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.UsersJPAEntity");
            List<UsersJPAEntity> users = myQuery.list();
            for (Object userObject : users) {
                UsersJPAEntity user = (UsersJPAEntity) userObject;
                if (user.getCode().equals(code)) {
                    return true;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean countBook(String code) {
        Session session = null;
        int sum = 0;
        try {
            session = openSession();
            Query<LendingJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.LendingJPAEntity");
            List<LendingJPAEntity> count = myQuery.list();
            for (Object userObject : count) {
                LendingJPAEntity user = (LendingJPAEntity) userObject;
                if (user.getBorrower().equals(code)) {
                    sum++;
                    if (sum >= maxBook) {
                        return true;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public BooksJPAEntity getBookById(String isbn) throws Exception {
        Session session = null;
        BooksJPAEntity bookToReturn = null;
        session = openSession();
        Query<BooksJPAEntity> myQuery =
                session.createQuery("from com.alinavevel.libraryapp.BooksJPAEntity");
        List<BooksJPAEntity> books = myQuery.list();
        for (Object bookObject : books) {
            BooksJPAEntity book = (BooksJPAEntity) bookObject;
            if (book.getIsbn().equals(isbn)) {
                bookToReturn = book;
            }
        }


        return bookToReturn;
    }

    public Date getLendingdate(String borrower){
        Session session = null;
        Date date = null;
        try {
            session = openSession();
            Query<LendingJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.LendingJPAEntity");
            List<LendingJPAEntity> books = myQuery.list();
            for (Object bookObject : books) {
                LendingJPAEntity book = (LendingJPAEntity) bookObject;
                if (book.getBorrower().equals(borrower)) {
                    date = book.getLendingdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getBookByName(String name) {
        Session session = null;
        String nameBook = null;
        try {
            session = openSession();
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.BooksJPAEntity");
            List<BooksJPAEntity> books = myQuery.list();
            for (Object bookObject : books) {
                BooksJPAEntity book = (BooksJPAEntity) bookObject;
                if (book.getTitle().equals(name)) {
                    nameBook = book.getIsbn();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nameBook;
    }


    public ArrayList<UsersJPAEntity> listOfUsers(String name) {
        Session session = null;
        ArrayList<UsersJPAEntity> usersFill = new ArrayList<UsersJPAEntity>();
        try {
            session = openSession();
            Query<UsersJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.UsersJPAEntity");
            List<UsersJPAEntity> users = myQuery.list();
            for (Object userObject : users) {
                UsersJPAEntity user = (UsersJPAEntity) userObject;
                if (user.getName().toLowerCase().contains(name.toLowerCase())) {
                    usersFill.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersFill;


    }

    public ArrayList<BooksJPAEntity> listOfBooks(String name) {
        Session session = null;
        ArrayList<BooksJPAEntity> booksFill = new ArrayList<BooksJPAEntity>();
        try {
            session = openSession();
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.BooksJPAEntity");
            List<BooksJPAEntity> books = myQuery.list();
            for (Object bookObject : books) {
                BooksJPAEntity book = (BooksJPAEntity) bookObject;
                if (book.getTitle().toLowerCase().contains(name.toLowerCase())) {
                    booksFill.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksFill;


    }

    public ArrayList<LendingJPAEntity> listOfLending(String code) {
        Session session = null;
        ArrayList<LendingJPAEntity> list = new ArrayList<LendingJPAEntity>();
        try {
            session = openSession();
            Query<LendingJPAEntity> myQuery = session.createQuery("from com.alinavevel.libraryapp.LendingJPAEntity");
            List<LendingJPAEntity> lendings = myQuery.list();
            for (Object lendingObject : lendings) {
                LendingJPAEntity lending = (LendingJPAEntity) lendingObject;
                if (lending.getBorrower().equals(code) && lending.getReturningdate() == null) {
                    list.add(lending);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public boolean setFine(String code, LocalDate date){
        Session session = null;
        try {
            session = openSession();
            Query<UsersJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "UsersJPAEntity where code='"
                            + code + "'");

            Transaction transaction = session.beginTransaction();
            UsersJPAEntity user = new UsersJPAEntity();
            List<UsersJPAEntity> books = myQuery.list();
            user = (UsersJPAEntity) books.get(0);
            user.setFined(Date.valueOf(date));
            session.update(user);
            transaction.commit(); // End of transaction
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        // End of transaction
    }

    public void updatingUser(UsersJPAEntity user) throws Exception {
        Session session = null;
        UsersJPAEntity save;
        session = openSession();
        Query<UsersJPAEntity> myQuery =
                session.createQuery("from com.alinavevel.libraryapp. " +
                        "UsersJPAEntity where code='"
                        + user.getCode() + "'");
        Transaction transaction = session.beginTransaction();
        List<UsersJPAEntity> books = myQuery.list();
        save = (UsersJPAEntity) books.get(0);
        save.setName(user.getName());
        save.setSurname(user.getSurname());
        save.setBirthdate(user.getBirthdate());
        session.update(save);
        transaction.commit(); // End of transaction


    }

    public boolean updatingLending(LendingJPAEntity lending) {
        boolean result = false;
        try (Session session = openSession()) {
            Query<LendingJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "LendingJPAEntity where book='"
                            + lending.getBook() +  "'");
            List<LendingJPAEntity> lendings = myQuery.list();
            Transaction transaction = session.beginTransaction();
            LendingJPAEntity len = (LendingJPAEntity) lendings.get(0);
            len.setReturningdate(lending.getReturningdate());
            session.update(len);
            transaction.commit(); // End of transaction
            updatingCopiesReturn(lending.getBook());
            LocalDate dateLending = lending.getLendingdate().toLocalDate();
            LocalDate dateReturning = lending.getReturningdate().toLocalDate();
            LocalDate maxDateReturn = dateLending.plusDays(lendingDays);
            LocalDate filed = dateReturning.plusDays(fine);
            if(dateReturning.isAfter(maxDateReturn)){
                setFine(lending.getBorrower(),filed);
            }
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;

    }

    public void updatingBook(BooksJPAEntity book) throws Exception {

        Session session = openSession();
        BooksJPAEntity save = new BooksJPAEntity();
        Query<BooksJPAEntity> myQuery =
                session.createQuery("from com.alinavevel.libraryapp. " +
                        "BooksJPAEntity where isbn='"
                        + book.getIsbn() + "'");
        List<BooksJPAEntity> books = myQuery.list();
        Transaction transaction = session.beginTransaction();
        save = (BooksJPAEntity) books.get(0);
        save.setTitle(book.getTitle());
        save.setPublisher(book.getPublisher());
        save.setCopies(book.getCopies());
        session.update(save);
        transaction.commit(); // End of transaction


    }
    public boolean updatingCopiesReturn(String isbn) {
        boolean result = false;
        try (Session session = openSession()) {
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "BooksJPAEntity where isbn='"
                            + isbn + "'");
            List<BooksJPAEntity> books = myQuery.list();
            Transaction transaction = session.beginTransaction();
            BooksJPAEntity book = (BooksJPAEntity) books.get(0);
            book.setIsbn(isbn);
            int copies = book.getCopies();
            copies++;
            book.setCopies(copies);
            session.update(book);
            transaction.commit(); // End of transaction
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean updatingCopiesBorrow(String isbn) {
        boolean result = false;
        try (Session session = openSession()) {
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "BooksJPAEntity where isbn='"
                            + isbn + "'");
            List<BooksJPAEntity> books = myQuery.list();
            Transaction transaction = session.beginTransaction();
            BooksJPAEntity book = (BooksJPAEntity) books.get(0);
            book.setIsbn(isbn);
            int copies = book.getCopies();
            copies--;
            book.setCopies(copies);
            session.update(book);
            transaction.commit(); // End of transaction
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
