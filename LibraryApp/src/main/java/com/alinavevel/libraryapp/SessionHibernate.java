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

/**
 * The type Session hibernate.
 */
public class SessionHibernate {
    private static SessionHibernate instance = null;
    /**
     * The Max book.
     */
    int maxBook = 3;
    /**
     * The Fine.
     */
    int fine = 15;
    /**
     * The Lending days.
     */
    int lendingDays = 30;


    /**
     * Open session session.
     *
     * @return the session
     * @throws Exception the exception
     */
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

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SessionHibernate getInstance() {
        if (instance == null) {
            instance = new SessionHibernate();
        }
        return instance;
    }


    /**
     * Insert user.
     *
     * @param user the user
     * @throws Exception the exception
     */
    public void insertUser(UsersJPAEntity user) throws Exception {

        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit(); // End of transaction


    }

    /**
     * Insert book.
     *
     * @param book the object of BookEntity
     * @throws Exception the exception
     */
    public void insertBook(BooksJPAEntity book) throws Exception {

        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit(); // End of transaction
    }

    /**
     * Insert lending. If insert is success return true
     *
     * @param lending the object of LendingEntity
     * @return the boolean
     */
    public boolean insertLending(LendingJPAEntity lending) {
        try {
            if (updatingCopiesBorrow(lending.getBook())) {
                Session session = openSession();
                Transaction transaction = session.beginTransaction();
                session.save(lending);
                transaction.commit();
                return true;
            }


        } catch (Exception e) {

        }
        return false;
    }

    /**
     * Gets user by id.
     *
     * @param user_id the user id
     * @return the user by id
     * @throws Exception the exception
     */
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

    /**
     * Search for concret book, if exists return true, if not this book doesn't exist
     *
     * @param isbn the isbn
     * @return the boolean
     */
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

        }
        return false;
    }

    /**
     * Book is available boolean.
     * If book has count >0 return true, if not false
     * @param isbn the isbn
     * @return the boolean
     */
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

        }
        return false;
    }

    /**
     * User exists boolean.
     * if user exists return true, else return false
     * @param code the code
     * @return the boolean
     */
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

        }
        return false;
    }

    /**
     * Count book boolean.
     *This method look how mucho books has the user, if > 3 the user can't borrow
     * @param code the code
     * @return the boolean
     */
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
                if (user.getBorrower().equals(code) && user.getReturningdate() == null) {
                    sum++;
                    if (sum >= maxBook) {
                        return true;
                    }
                }
            }


        } catch (Exception e) {

        }
        return false;
    }

    public boolean userHasPendingBooks(String code) {
        Session session = null;
        int sum = 0;
        try {
            session = openSession();
            Query<LendingJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.LendingJPAEntity");
            List<LendingJPAEntity> count = myQuery.list();
            for (Object userObject : count) {
                LendingJPAEntity user = (LendingJPAEntity) userObject;
                if (user.getBorrower().equals(code) && user.getReturningdate() == null) {
                    return true;
                }
            }


        } catch (Exception e) {

        }
        return false;
    }

    public boolean bookIsPendingToReturn(String code) {
        Session session = null;
        int sum = 0;
        try {
            session = openSession();
            Query<LendingJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.LendingJPAEntity");
            List<LendingJPAEntity> count = myQuery.list();
            for (Object userObject : count) {
                LendingJPAEntity user = (LendingJPAEntity) userObject;
                if (user.getBook().equals(code) && user.getReturningdate() == null) {
                    return true;
                }
            }


        } catch (Exception e) {

        }
        return false;
    }

    /**
     * Gets book by id.
     *
     * @param isbn the isbn
     * @return the book by id
     * @throws Exception the exception
     */
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

    /**
     * Get lendingdate date.
     * This method returns the lendin=g date of the concret book
     * @param borrower the borrower
     * @return the date
     */
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

        }
        return date;
    }

    /**
     * Gets book by name.
     * This method this isbn of the book
     * @param name the name
     * @return the book by name
     */
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

        }
        return nameBook;
    }


    /**
     * List of users array list.
     *
     * @param name the name
     * @return the array list
     */
    public ArrayList<UsersJPAEntity> listOfUsers(String name) {
        Session session = null;
        ArrayList<UsersJPAEntity> usersFill = new ArrayList<UsersJPAEntity>();
        try {
            session = openSession();
            Query<UsersJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "UsersJPAEntity where lower(name) like lower('" + name + "%')");
            List<UsersJPAEntity> users = myQuery.list();
            for (Object userObject : users) {
                UsersJPAEntity user = (UsersJPAEntity) userObject;
                usersFill.add(user);
            }
        } catch (Exception e) {

        }
        return usersFill;


    }

    /**
     * List of books array list.
     *
     * @param name the name
     * @return the array list
     */
    public ArrayList<BooksJPAEntity> listOfBooks(String name) {
        Session session = null;
        ArrayList<BooksJPAEntity> booksFill = new ArrayList<BooksJPAEntity>();
        try {
            session = openSession();
            Query<BooksJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "BooksJPAEntity where lower(title) like lower('" + name + "%')");
            List<BooksJPAEntity> books = myQuery.list();
            for (Object bookObject : books) {
                BooksJPAEntity book = (BooksJPAEntity) bookObject;
                booksFill.add(book);

            }
        } catch (Exception e) {

        }
        return booksFill;


    }

    /**
     * List of lending array list.
     *
     * @param code the code
     * @return the array list
     */
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

        }
        return list;

    }

    /**
     * Same book boolean.
     * This method look if the user has borrowed this book previously
     * @param code     the code
     * @param nameBook the name book
     * @return the boolean
     */
    public boolean sameBook(String code, String nameBook) {
        Session session = null;

        try {
            session = openSession();
            Query<LendingJPAEntity> myQuery = session.createQuery("from com.alinavevel.libraryapp.LendingJPAEntity");
            List<LendingJPAEntity> lendings = myQuery.list();
            for (Object lendingObject : lendings) {
                LendingJPAEntity lending = (LendingJPAEntity) lendingObject;
                if (lending.getBorrower().equals(code) && lending.getBook().equals(nameBook)) {
                    return true;
                }
            }
        } catch (Exception e) {

        }
        return false;

    }

    /**
     * Set fine boolean.
     * This method set the fine to a user if the user returns late the book
     * @param code the code
     * @param date the date
     * @return the boolean
     */
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

        }
        return true;
        // End of transaction
    }

    /**
     * Updating user.
     *
     * @param user the user
     * @throws Exception the exception
     */
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

    /**
     * Updating lending boolean.
     *
     * @param lending the lending
     * @return the boolean
     */
    public boolean updatingLending(LendingJPAEntity lending) {
        boolean result = false;
        try (Session session = openSession()) {
            Query<LendingJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp.LendingJPAEntity where book='" + lending.getBook() + "' and borrower='" + lending.getBorrower() + "'");
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

        }
        return result;

    }

    /**
     * If user is fined boolean.
     *
     * @param userCode the user code
     * @param date     the date
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean ifUserIsFined(String userCode, Date date) throws Exception {
        Session session = openSession();
        UsersJPAEntity user;
        Query<UsersJPAEntity> myQuery =
                session.createQuery("from com.alinavevel.libraryapp. " +
                        "UsersJPAEntity where code='"
                        + userCode + "'");
        List<UsersJPAEntity> lendings = myQuery.list();
        for (Object lendingObject : lendings) {
            UsersJPAEntity lending = (UsersJPAEntity) lendingObject;
            if(lending.getFined() != null){
                if (lending.getFined().after(date)){
                    return true;
                }
            }
            if(lending.getFined() == null){
                return false;
            }
        }
        return true;

    }

    /**
     * Updating book.
     *
     * @param book the book
     * @throws Exception the exception
     */
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

    /**
     * Updating copies return boolean.
     *
     * @param isbn the isbn
     * @return the boolean
     */
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

        }
        return result;
    }

    /**
     * Updating copies borrow boolean.
     *
     * @param isbn the isbn
     * @return the boolean
     */
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

        }
        return result;
    }


    public void deleteUser( String userCode ) {
        try ( Session session = openSession() ) {
            Query<UsersJPAEntity> myQuery =
                    session.createQuery("from com.alinavevel.libraryapp. " +
                            "UsersJPAEntity where code='"
                            + userCode + "'");
            List<UsersJPAEntity> users = myQuery.list();
            if ( users.size() > 0 ) {
                Transaction transaction = session.beginTransaction();
                session.delete(users.get(0));
                transaction.commit(); // End of transaction
            }

        }
        catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }


}
