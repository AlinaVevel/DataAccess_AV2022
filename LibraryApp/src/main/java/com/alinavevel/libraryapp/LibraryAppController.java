package com.alinavevel.libraryapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryAppController extends SessionHibernate {

    SessionHibernate connection = new SessionHibernate();
    States estado = States.ACCAUNT;
    ArrayList<UsersJPAEntity> users = new ArrayList<>();
    ArrayList<BooksJPAEntity> booksList = new ArrayList<>();
    ArrayList<LendingJPAEntity> lendinglist = new ArrayList<>();

    //region fxml
    @FXML
    private GridPane RegistrationPane;

    @FXML
    private AnchorPane window;

    @FXML
    private ChoiceBox userChoiseBox;

    @FXML
    private TextField userCodeBorrow;

    @FXML
    private TextField nameFromChoiceBox;


    @FXML
    private ImageView account;

    @FXML
    private ImageView books;

    @FXML
    private GridPane borowPane;

    @FXML
    private ImageView borrowBook;

    @FXML
    private ImageView confirm;

    @FXML
    private GridPane confirmDeletPane;

    @FXML
    private GridPane confirmModiifPane;

    @FXML
    private ImageView delet;

    @FXML
    private ImageView exit;

    @FXML
    private DatePicker inputBirthday;

    @FXML
    private TextField inputCode;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    @FXML
    private GridPane menuPane;

    @FXML
    private ImageView modify;

    @FXML
    private GridPane registrationBooks;

    @FXML
    private ImageView returnBook;

    @FXML
    private ImageView search;

    @FXML
    private TextField inputIsbn;

    @FXML
    private TextField inputTitle;

    @FXML
    private TextField inputPublisher;

    @FXML
    private Slider inputCopies;

    @FXML
    private TextField isbnCodeBorrow;

    @FXML
    private ChoiceBox isbnChoiseBox;

    @FXML
    private TextField isbnName;


    public LibraryAppController() {
    }

    //endregion

    public void accauntClick() {
        accauntFields();
    }

    public void accauntFields() {
        RegistrationPane.setVisible(true);
        registrationBooks.setVisible(false);
        borowPane.setVisible(false);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputBirthday.setDisable(true);
        inputCode.setDisable(true);
        estado = States.ACCAUNT;
    }

    public void addUserClick() {

        if (estado == States.ACCAUNT) {
            inputCode.setDisable(false);
            inputFirstName.setDisable(false);
            inputLastName.setDisable(false);
            inputBirthday.setDisable(false);
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            estado = States.ADDUSER;
        }
        if (estado == States.BOOK) {
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            inputIsbn.setDisable(false);
            inputTitle.setDisable(false);
            inputCopies.setDisable(false);
            inputPublisher.setDisable(false);
            estado = States.ADDBOOK;

        }


    }

    public void bookFields() {
        registrationBooks.setVisible(true);
        RegistrationPane.setVisible(false);
        borowPane.setVisible(false);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputIsbn.setDisable(true);
        inputTitle.setDisable(true);
        inputCopies.setDisable(true);
        inputPublisher.setDisable(true);
        inputIsbn.setDisable(true);
        estado = States.BOOK;

    }

    public void booksClick() {
        bookFields();
    }

    public void returnBookClick() {
        registrationBooks.setVisible(false);
        RegistrationPane.setVisible(false);
        confirmDeletPane.setVisible(true);
        borowPane.setVisible(true);
        nameFromChoiceBox.setVisible(true);
        userChoiseBox.setVisible(false);
        isbnName.setVisible(true);
        isbnName.setDisable(false);
        isbnChoiseBox.setVisible(false);
        nameFromChoiceBox.setDisable(false);
        userCodeBorrow.setDisable(true);
        isbnCodeBorrow.setDisable(true);
        isbnName.setDisable(true);

        estado = States.RETURNBOOK;
    }

    public void borrowBookClick() {
        registrationBooks.setVisible(false);
        RegistrationPane.setVisible(false);
        confirmDeletPane.setVisible(true);
        borowPane.setVisible(true);
        nameFromChoiceBox.setVisible(true);
        userChoiseBox.setVisible(false);
        isbnName.setVisible(true);
        isbnName.setDisable(false);
        isbnChoiseBox.setVisible(false);
        nameFromChoiceBox.setDisable(false);
        userCodeBorrow.setDisable(true);
        isbnCodeBorrow.setDisable(true);

        estado = States.BORROWBOOK;
    }

    public void modifyClick() {
        if (estado == States.ACCAUNT) {
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            inputFirstName.setDisable(false);
            inputLastName.setDisable(false);
            inputBirthday.setDisable(false);
            inputCode.setDisable(true);
            estado = States.MODIFYUSER;
        }
        if (estado == States.BOOK) {
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            inputIsbn.setDisable(true);
            inputCopies.setDisable(false);
            inputPublisher.setDisable(false);
            inputTitle.setDisable(false);
            estado = States.MODIFYBOOK;
        }
    }

    public void submit() {
        if (estado == States.ADDUSER) {
            addUser();
            clearFieldAccaunt();
            panelsDown();
            estado = States.ACCAUNT;
        }
        if (estado == States.SEARCHUSER) {
            searchUser();
            panelsDown();
            estado = States.ACCAUNT;
        }

        if (estado == States.MODIFYUSER) {
            updateUser();

            panelsDown();
            estado = States.ACCAUNT;
        }

        if (estado == States.ADDBOOK) {
            addBook();
            panelsDown();
            estado = States.BOOK;
        }
        if (estado == States.SEARCHBOOK) {
            searchBook();
            panelsDown();
            estado = States.BOOK;
        }

        if (estado == States.MODIFYBOOK) {
            updateBook();
            panelsDown();
            estado = States.BOOK;
        }

        if (estado == States.BORROWBOOK) {


            if (isbnName.getText().isEmpty() || nameFromChoiceBox.getText().isEmpty()) {
                AlterDialogError("FAILED", "Some fields are empty");
            } else if (connection.countBook(userCodeBorrow.getText())) {
                AlterDialogError("ERROR", "This user has already 3 books");
            } else {
                if (connection.bookIsAvailable(codeBook(isbnName.getText()))) {

                    try {
                        LendingJPAEntity lending = new LendingJPAEntity();
                        lending.setBorrower(userCodeBorrow.getText());
                        lending.setBook(isbnCodeBorrow.getText());
                        long now = System.currentTimeMillis();
                        Date sqlDate = new Date(now);
                        lending.setLendingdate(sqlDate);
                        if (connection.insertLending(lending, isbnCodeBorrow.getText())) {
                            AlterDialogConfirmation("Enjoy this book", "You have one month!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    alterDialogReservation();
                }
            }


        }
        if (estado == States.RETURNBOOK) {
            LendingJPAEntity lending = new LendingJPAEntity();
            lending.setBorrower(userCodeBorrow.getText());
            lending.setBook(isbnCodeBorrow.getText());
            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);
            lending.setReturningdate(sqlDate);
            Date date = connection.getLendingdate(userCodeBorrow.getText());
            lending.setLendingdate(date);
            connection.updatingLending(lending);
            AlterDialogConfirmation("DONE!", "Book are returned");
        }
    }

    public void panelsDown() {

        confirmDeletPane.setVisible(false);
        confirmModiifPane.setVisible(true);
    }

    public void searchClick() {
        if (estado == States.ACCAUNT) {
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            inputCode.setDisable(false);
            estado = States.SEARCHUSER;
        }
        if (estado == States.BOOK) {
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            inputIsbn.setDisable(false);
            estado = States.SEARCHBOOK;
        }


    }

    public void returnClick() {
        registrationBooks.setVisible(false);
        RegistrationPane.setVisible(true);
        borowPane.setVisible(false);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputBirthday.setDisable(true);
        inputCode.setDisable(true);
        estado = States.ACCAUNT;
    }

    public void searchUserBorrow() {
        if (estado == States.RETURNBOOK) {
            listOfUsers();


        }
        if (estado == States.BORROWBOOK) {
            listOfUsers();


        }


    }

    public void searchNameBook() {
        if (estado == States.RETURNBOOK) {
            listOfBook(userCodeBorrow.getText());
        }
        if (estado == States.BORROWBOOK) {
            String name = isbnName.getText();
            List<String> choices = new ArrayList<>();
            booksList = connection.listOfBooks(name);
            for (BooksJPAEntity book : booksList) {
                //isbnChoiseBox.getItems().add(book.getTitle());
                choices.add(book.getTitle());
            }

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Books", choices);
            dialog.setTitle("Choice Book");
            dialog.setHeaderText("List of Books");
            dialog.setContentText("Choose your book:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                isbnName.setText(result.get());
                isbnCodeBorrow.setText(codeBook(isbnName.getText()));

            }

        }


    }

    public void listOfBook(String name) {

        List<String> choices = new ArrayList<>();
        lendinglist = connection.listOfLending(name);
        BooksJPAEntity book = new BooksJPAEntity();
        for (LendingJPAEntity lending : lendinglist) {
            booksList = connection.listOfBooks(lending.getBook());
            try {
                book = connection.getBookById(lending.getBook());
            } catch (Exception e) {
                e.printStackTrace();
            }
            choices.add(book.getTitle());
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Books", choices);
        dialog.setTitle("Choice Book");
        dialog.setHeaderText("List of Books");
        dialog.setContentText("Choose your book:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            isbnName.setText(result.get());
            isbnCodeBorrow.setText(connection.getBookByName(isbnName.getText()));

        }
    }

    public void listOfUsers() {
        String name = nameFromChoiceBox.getText();
        List<String> choices = new ArrayList<>();
        users = connection.listOfUsers(name);
        if(users.size() > 0){
            for (UsersJPAEntity user : users) {
                choices.add(user.getName() + " " + user.getSurname());

            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Users", choices);
            dialog.setTitle("List of Users");
            dialog.setHeaderText("List of Users");
            dialog.setContentText("Choose user:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                nameFromChoiceBox.setText(result.get());
                userCodeBorrow.setText(codeUser(nameFromChoiceBox.getText()));

            }

        }
        else{
            AlterDialogError("Error", "User don't found");
        }

    }

    public void addUser() {

        String code = inputCode.getText();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();

        Date date = null;
        try {
            date = Date.valueOf(inputBirthday.getValue());

        } catch (Exception e) {

        }
        if (code.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            AlterDialogError("Insert failed", "Some fields are empty");
            accauntFields();
        }
        if (connection.userExists(code)) {
            AlterDialogError("Insert failed", "User exists");
            accauntFields();
        }
        if (!code.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()) {
            UsersJPAEntity user = new UsersJPAEntity();
            user.setCode(code);
            user.setName(firstName);
            user.setSurname(lastName);
            user.setBirthdate(date);
            try {
                connection.insertUser(user);
                AlterDialogConfirmation("User registered", "DONE!");
                clearFieldAccaunt();
                accauntFields();
            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
            }

        }


    }

    public void addBook() {
        String isbn = inputIsbn.getText();
        String title = inputTitle.getText();
        String publisher = inputPublisher.getText();
        int copies = (int) inputCopies.getValue();

        if (isbn.isEmpty() || title.isEmpty() || publisher.isEmpty()) {
            AlterDialogError("Insert failed", "Some fields are empty");
            bookFields();
            clearBookFields();

        }

        if (connection.bookExists(isbn)) {
            AlterDialogError("Insert failed", "Book exists");
            bookFields();
            clearBookFields();
        }
        if (!isbn.isEmpty() && !publisher.isEmpty() && !title.isEmpty()) {
            BooksJPAEntity book = new BooksJPAEntity();
            book.setIsbn(isbn);
            book.setCopies(copies);
            book.setTitle(title);
            book.setPublisher(publisher);
            book.setCover("");
            book.setOutline("");
            try {
                connection.insertBook(book);
                AlterDialogConfirmation("Book registered", "DONE!");
                bookFields();
                clearBookFields();
            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
                bookFields();
                clearBookFields();
            }

        }

    }

    public void updateUser() {
        String code = inputCode.getText();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();

        Date date = null;
        try {
            date = Date.valueOf(inputBirthday.getValue());

        } catch (Exception e) {

        }
        if (code.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || date == null) {
            AlterDialogError("Error", "Some fields are empty");
        }
        if (!code.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && date != null) {

            try {
                UsersJPAEntity user = new UsersJPAEntity();
                user.setCode(code);
                user.setName(firstName);
                user.setSurname(lastName);
                user.setBirthdate(date);
                connection.updatingUser(user);
                AlterDialogConfirmation("User updated", "DONE!");
            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
            }
        }
        clearFieldAccaunt();
        accauntFields();


    }

    public void updateBook() {
        String isbn = inputIsbn.getText();
        String title = inputTitle.getText();
        String publisher = inputPublisher.getText();
        int copies = (int) inputCopies.getValue();
        if (isbn.isEmpty() || title.isEmpty() || publisher.isEmpty()) {
            AlterDialogError("Error", "Some fields are empty");
        }
        if (!isbn.isEmpty() && !title.isEmpty() && !publisher.isEmpty()) {

            try {
                BooksJPAEntity book = new BooksJPAEntity();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setCopies(copies);
                book.setPublisher(publisher);
                connection.updatingBook(book);
                AlterDialogConfirmation("Book updated", "DONE!");
                bookFields();
                clearBookFields();
            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
                bookFields();
                clearBookFields();
            }
        }
    }

    public void searchUser() {

        UsersJPAEntity user;
        String code = inputCode.getText();
        if (code.isEmpty()) {
            AlterDialogError("Error", "User code can't be empty");
        } else {
            try {
                user = connection.getUserById(code);
                if (user == null) {
                    AlterDialogError("Error", "User don't exists");
                    clearFieldAccaunt();
                    accauntFields();
                } else {
                    inputFirstName.setText(user.getName());
                    inputLastName.setText(user.getSurname());
                    inputBirthday.setValue(LocalDate.parse(user.getBirthdate().toString()));

                }

            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
            }

        }

    }

    public void searchBook() {
        BooksJPAEntity book;
        String isbn = inputIsbn.getText();
        if (isbn.isEmpty()) {
            AlterDialogError("Error", "ISBN can't be empty");
            bookFields();
        }
        else{
            try {
                book = connection.getBookById(isbn);
                if (book == null) {
                    AlterDialogError("Error", "Book don't exists");
                    bookFields();
                } else {
                    inputIsbn.setText(book.getIsbn());
                    inputTitle.setText(book.getTitle());
                    inputCopies.setValue(book.getCopies());
                    inputPublisher.setText(book.getPublisher());

                }
            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
            }

        }

    }

    public void AlterDialogError(String canntDoIt, String why) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error alert");
        alert.setHeaderText(canntDoIt);
        alert.setContentText(why);
        alert.showAndWait();
    }

    public void alterDialogReservation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Book Reservation");
        alert.setHeaderText("This book is not available now");
        alert.setContentText("Would you like to make a reservation of this book?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void AlterDialogConfirmation(String what, String success) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(what);
        alert.setContentText(success);
        alert.showAndWait();
    }

    public String codeUser(String name) {

        String[] nameS = name.split(" ");
        String firstName = nameS[0];
        String lastName = nameS[1];
        String code = null;
        for (UsersJPAEntity user : users) {
            if (user.getName().equals(firstName) && user.getSurname().equals(lastName)) {
                code = user.getCode();
            }
        }
        return code;
    }

    public String codeBook(String name) {

        String code = null;
        for (BooksJPAEntity book : booksList) {
            if (book.getTitle().equals(name)) {
                code = book.getIsbn();
            }
        }
        return code;
    }

    public void clearFieldAccaunt() {
        inputCode.setText("");
        inputBirthday.setValue(null);
        inputFirstName.setText("");
        inputLastName.setText("");
    }

    public void clearBookFields() {
        inputIsbn.setText("");
        inputCopies.setValue(1);
        inputTitle.setText("");
        inputPublisher.setText("");
    }

    public void exitClick(){
        Platform.exit();
    }

}