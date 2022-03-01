package com.alinavevel.libraryapp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Library app controller.
 */
public class LibraryAppController extends SessionHibernate {

    /**
     * The Connection.
     */
    SessionHibernate connection = new SessionHibernate();
    /**
     * The Estado.
     */
    States estado = States.ACCAUNT;
    /**
     * The Users.
     */
    ArrayList<UsersJPAEntity> users = new ArrayList<>();
    /**
     * The Books list.
     */
    ArrayList<BooksJPAEntity> booksList = new ArrayList<>();
    /**
     * The Lendinglist.
     */
    ArrayList<LendingJPAEntity> lendinglist = new ArrayList<>();
    /**
     * The Api.
     */
    ReservationAPI api = new ReservationAPI();

    BookAPI apiBook = new BookAPI();

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
    private GridPane borowPane;


    @FXML
    private GridPane confirmDeletPane;

    @FXML
    private GridPane confirmModiifPane;


    @FXML
    private DatePicker inputBirthday;

    @FXML
    private TextField inputCode;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;


    @FXML
    private GridPane registrationBooks;

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


    /**
     * Instantiates a new Library app controller.
     */
    public LibraryAppController() {
    }

    //endregion

    /**
     * Accaunt click.
     * Method for button Accaunt
     */
    public void accauntClick() {
        RegistrationPane.setVisible(true);
        registrationBooks.setVisible(false);
        borowPane.setVisible(false);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputBirthday.setDisable(true);
        inputCode.setDisable(true);
        inputCode.setText("");
        inputBirthday.setValue(null);
        inputFirstName.setText("");
        inputLastName.setText("");
        estado = States.ACCAUNT;
    }

    /**
     * Add user click.
     * Method adding user
     */
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

    /**
     * Books click.
     * This method is for the button book
     */
    public void booksClick() {
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
        inputIsbn.setText("");
        inputCopies.setValue(1);
        inputTitle.setText("");
        inputPublisher.setText("");
        estado = States.BOOK;
    }

    /**
     * Return book click.
     * Method for return book button
     */
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
        isbnCodeBorrow.setText("");
        isbnName.setText("");
        userCodeBorrow.setText("");
        nameFromChoiceBox.setText("");
        estado = States.RETURNBOOK;
    }

    /**
     * Borrow book click.
     * Method for borrow book button
     */
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
        isbnCodeBorrow.setText("");
        isbnName.setText("");
        userCodeBorrow.setText("");
        nameFromChoiceBox.setText("");
        estado = States.BORROWBOOK;
    }

    /**
     * Modify click.
     * Method for button modify
     */
    public void modifyClick() {
        if (estado == States.ACCAUNT) {
            if(!inputCode.getText().isEmpty() || !inputCode.getText().isBlank()){
                confirmModiifPane.setVisible(false);
                confirmDeletPane.setVisible(true);
                inputFirstName.setDisable(false);
                inputLastName.setDisable(false);
                inputBirthday.setDisable(false);
                inputCode.setDisable(true);
                estado = States.MODIFYUSER;
            }
        }
        if (estado == States.BOOK) {
            if(!inputIsbn.getText().isEmpty()|| !inputIsbn.getText().isBlank()){
                confirmModiifPane.setVisible(false);
                confirmDeletPane.setVisible(true);
                inputIsbn.setDisable(true);
                inputCopies.setDisable(false);
                inputPublisher.setDisable(false);
                inputTitle.setDisable(false);
                estado = States.MODIFYBOOK;
            }
        }
    }

    /**
     * Method for button submit. That is for two tabs.Here we see in what state we are
     */
    public void submit() {
        if (estado == States.ADDUSER) {
            addUser();
            accauntClick();
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
            long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);
            if (isbnName.getText().isEmpty() || nameFromChoiceBox.getText().isEmpty()) {
                AlterDialogError("FAILED", "Some fields are empty");
            }else if(isbnCodeBorrow.getText().isEmpty() || isbnCodeBorrow.getText().isEmpty()){
                AlterDialogError("Error", "First you need to choose user and book");
            }
            else if (connection.countBook(userCodeBorrow.getText())) {
                AlterDialogError("ERROR", "This user has already 3 books");
            } else if (connection.sameBook(userCodeBorrow.getText(), isbnCodeBorrow.getText())) {
                AlterDialogError("ERROR", "The user had allredy this book");
            }else {
                try {
                    if(connection.ifUserIsFined(userCodeBorrow.getText(), sqlDate)){
                        AlterDialogError("ERROR", "The user is fined! He can't borrow the book");
                    }
                    else if(connection.bookIsAvailable(connection.getBookByName(isbnName.getText()))) {

                        LendingJPAEntity lending = new LendingJPAEntity();
                        lending.setBorrower(userCodeBorrow.getText());
                        lending.setBook(isbnCodeBorrow.getText());
                        lending.setLendingdate(sqlDate);
                        if (connection.insertLending(lending)) {
                            AlterDialogConfirmation("Enjoy this book", "You have one month!");
                        }
                    }else if (!connection.bookIsAvailable(isbnName.getText())) {

                        ReservationJPAEntityFinal reservation = new ReservationJPAEntityFinal();
                        reservation.setBorrower(userCodeBorrow.getText());
                        reservation.setBook(isbnCodeBorrow.getText());
                        reservation.setDate(sqlDate);
                        alterDialogReservation(reservation);
                    } else {
                        AlterDialogError("Error", "Book or user dont exist, you need to select the user and the book! ");
                    }
                } catch (Exception e) {
                    AlterDialogError("Some ERROR", e.getCause().getMessage());
                }
            }

            borrowBookClick();
        }
        if (estado == States.RETURNBOOK) {
            if (isbnName.getText().isEmpty() || nameFromChoiceBox.getText().isEmpty()) {
                AlterDialogError("FAILED", "Some fields are empty");

            }
            else{
                returnBook();

            }
            returnBookClick();

        }
    }

    /**
     * Add user.
     * Method for adding user
     */
    public void addUser() {

        String code = inputCode.getText();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();
        Date date = null;
        String regName = "^[a-zA-Z]*$";
        try {
            date = Date.valueOf(inputBirthday.getValue());

        } catch (Exception e) {
            AlterDialogError("Error", e.getCause().getMessage());
        }
        if (code.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || code.isBlank() || firstName.isBlank() || lastName.isBlank() || date == null) {
            AlterDialogError("Insert failed", "Some fields are empty");
        } else if (firstName.length() > 25) {
            AlterDialogError("Error", "Name can't be mote than 25 characters");
        } else if (!firstName.matches(regName) || !lastName.matches(regName)) {
            AlterDialogError("Error", "Only characters");
        } else if (code.length() > 8) {
            AlterDialogError("Error", "The code can't be more than 13 characters");
        } else if (date.toLocalDate().isAfter(LocalDate.now())) {
            AlterDialogError("Error", "The date can't be after than today ");
        } else if (connection.userExists(code)) {
            AlterDialogError("Insert failed", "User exists");
        } else if (!code.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !code.isBlank() && !firstName.isBlank() && !lastName.isBlank() && !connection.userExists(code) && date != null) {
            UsersJPAEntity user = new UsersJPAEntity();
            user.setCode(code);
            user.setName(firstName);
            user.setSurname(lastName);
            user.setBirthdate(date);
            try {
                connection.insertUser(user);
                AlterDialogConfirmation("User registered", "DONE!");
            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
            }
        }
        accauntClick();
    }

    /**
     * Method for returning boobs
     */
    public void returnBook(){
        LendingJPAEntity lending = new LendingJPAEntity();
        lending.setBorrower(userCodeBorrow.getText());
        lending.setBook(isbnCodeBorrow.getText());
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        lending.setReturningdate(sqlDate);
        Date dateLending = connection.getLendingdate(userCodeBorrow.getText());
        lending.setLendingdate(dateLending);
        connection.updatingLending(lending);
        LocalDate dateReturning = lending.getReturningdate().toLocalDate();
        LocalDate maxDateReturn = dateLending.toLocalDate().plusDays(30);
        if(dateReturning.isAfter(maxDateReturn)){
            AlterDialogError("ATTENTION", "The user extends the returning date, he can't borrow books in 15 days");
        }
        AlterDialogConfirmation("DONE!", "Book are returned");
        String code = api.getRequest(isbnCodeBorrow.getText());
        if(code != null){
            try {
                UsersJPAEntity user = connection.getUserById(code);
                String name = user.getName();

                AlterDialogConfirmation("This book has reservation", "Wold you like to send an e-mail to " + name + " ?");
            } catch (Exception e) {
               AlterDialogError("Error", e.getCause().getMessage());
            }

        }
    }


    /**
     * Method for adding books
     */
    public void addBook() {
        String isbn = inputIsbn.getText();
        String title = inputTitle.getText();
        String publisher = inputPublisher.getText();
        int copies = (int) inputCopies.getValue();
        if (isbn.isEmpty() || title.isEmpty() || publisher.isEmpty() || isbn.isBlank() || title.isBlank() || publisher.isBlank()) {
            AlterDialogError("Insert failed", "Some fields are empty");
        } else if (connection.bookExists(isbn)) {
            AlterDialogError("Insert failed", "Book exists");
        } else if (title.length() > 90) {
            AlterDialogError("Error", "Title can't be more than 90 characters");
        } else if (isbn.length() > 13) {
            AlterDialogError("Error", "ISBN can't be more than 13 character");
        } else if (publisher.length() > 60) {
            AlterDialogError("Error", "Publisher can't be more than 60 characters");
        } else if (!isbn.isEmpty() && !title.isEmpty() && !publisher.isEmpty() && !isbn.isBlank() && !title.isBlank() && !publisher.isBlank() && !connection.bookExists(isbn)) {
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
                booksClick();
            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());
                           }
        }
       booksClick();

    }

    /**
     * Method for updating users
     */
    public void updateUser() {
        String code = inputCode.getText();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();
        String regName = "^[a-zA-Z]*$";
        Date date = null;
        try {
            date = Date.valueOf(inputBirthday.getValue());

        } catch (Exception e) {

        }

        if (code.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || date == null || code.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            AlterDialogError("Error", "Some fields are empty");
        } else if (lastName.length() > 25) {
            AlterDialogError("Error", "Last name can't be mote than 25 characters");
        } else if (firstName.length() > 25) {
            AlterDialogError("Error", "Name can't be mote than 25 characters");
        } else if (code.length() > 8) {
            AlterDialogError("Error", "The code can't be more than 13 characters");
        } else if (!firstName.matches(regName) || !lastName.matches(regName)) {
            AlterDialogError("Error", "Only characters");
        } else if (date.toLocalDate().isAfter(LocalDate.now())) {
            AlterDialogError("Error", "The date can't be after than today ");
        } else if (!code.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && date != null && !code.isBlank() || !firstName.isBlank() || !lastName.isBlank()) {

            try {
                UsersJPAEntity user = new UsersJPAEntity();
                user.setCode(code);
                user.setName(firstName);
                user.setSurname(lastName);
                user.setBirthdate(date);
                connection.updatingUser(user);
                AlterDialogConfirmation("User updated", "DONE!");

            } catch (Exception e) {
                AlterDialogError("Error", e.getCause().getCause().getMessage());
            }
        }
        accauntClick();
    }

    /**
     * Method for updating books
     */
    public void updateBook() {
        String isbn = inputIsbn.getText();
        String title = inputTitle.getText();
        String publisher = inputPublisher.getText();
        int copies = (int) inputCopies.getValue();
        if (isbn.isEmpty() || title.isEmpty() || publisher.isEmpty()) {
            AlterDialogError("Error", "Some fields are empty");
        } else if (title.length() > 90) {
            AlterDialogError("Error", "Title can't be more than 90 characters");
        } else if (isbn.length() > 13) {
            AlterDialogError("Error", "ISBN can't be more than 13 character");
        } else if (publisher.length() > 60) {
            AlterDialogError("Error", "Publisher can't be more than 60 characters");
        } else if (!isbn.isEmpty() && !title.isEmpty() && !publisher.isEmpty() && !isbn.isBlank() && !title.isBlank() && !publisher.isBlank()) {

            try {
                BooksJPAEntity book = new BooksJPAEntity();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setCopies(copies);
                book.setPublisher(publisher);
                connection.updatingBook(book);
                AlterDialogConfirmation("Book updated", "DONE!");

            } catch (Exception e) {
                AlterDialogError("Error", e.getMessage());

            }
        }
        booksClick();
    }

    /**
     * Search user.
     * Method for searching user
     */
    public void searchUser() {
        UsersJPAEntity user;
        String code = inputCode.getText();
        if (code.isEmpty() || code.isBlank()) {
            AlterDialogError("Error", "User code can't be empty");
            accauntClick();
        } else {
            try {
                user = connection.getUserById(code);
                if (user == null) {
                    AlterDialogError("Error", "User don't exists");
                   accauntClick();
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

    /**
     * Search book.
     * Method for searching books
     */
    public void searchBook() {
        BooksJPAEntity book;
        String isbn = inputIsbn.getText();
        if (isbn.isEmpty() || isbn.isBlank()) {
            AlterDialogError("Error", "ISBN can't be empty");
            booksClick();
        } else {
            try {
                book = connection.getBookById(isbn);
                if (book == null) {
                    AlterDialogError("Error", "Book don't exists");
                    booksClick();
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

    /**
     * Panels down.
     * For up and down panel
     */
    public void panelsDown() {

        confirmDeletPane.setVisible(false);
        confirmModiifPane.setVisible(true);
    }

    /**
     * Method for searching button, the functionality depends on the States
     */
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

    /**
     * Method for return button
     */
    public void returnClick() {
        switch (estado) {
            case ACCAUNT -> accauntClick();
            case BOOK -> booksClick();
            case SEARCHBOOK -> booksClick();
            case SEARCHUSER -> accauntClick();
            case MODIFYUSER -> accauntClick();
            case MODIFYBOOK -> booksClick();
            case ADDUSER -> accauntClick();
            case ADDBOOK -> booksClick();
            case BORROWBOOK -> borrowBookClick();
            case RETURNBOOK -> returnBookClick();
        }
    }

    public void deleteClick(){
        if(estado == States.ACCAUNT){
            alterDialogDeleting();

        }
        if(estado == States.BOOK){
            alterDeleteBook();
        }
    }

    public void deleting(){
        if(inputCode.getText().isEmpty() || inputCode.getText().isBlank()){
            AlterDialogError("Error", "You need to search the user first");
        }
        else{
            connection.deleteUser(inputCode.getText());
        }
    }

    public void deletingBook(){
        if(inputIsbn.getText().isEmpty() || inputIsbn.getText().isBlank()){
            AlterDialogError("Error", "You need to search the book first");
        }
        else{
            apiBook.DeleteRequest(inputIsbn.getText());
        }
    }

    /**
     * Method for searching user for fill the choice box in panel return and borrow
     */
    public void searchUserBorrow() {
        if (estado == States.RETURNBOOK) {
            listOfUsers();
        }
        if (estado == States.BORROWBOOK) {
            listOfUsers();
        }
    }

    /**
     * Method for fill choice box with books. In borrow panel wi will have all books
     * and in return books only books that have the user atb the moment
     */
    public void searchNameBook() {
        if (estado == States.RETURNBOOK) {
            listOfBook(userCodeBorrow.getText());
        }
        if (estado == States.BORROWBOOK) {
            String name = isbnName.getText();
            List<String> choices = new ArrayList<>();
            booksList = connection.listOfBooks(name);
            if (booksList.size() > 0) {
                for (BooksJPAEntity book : booksList) {
                    choices.add(book.getTitle());
                }
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Books", choices);
                dialog.setTitle("Choice Book");
                dialog.setHeaderText("List of Books");
                dialog.setContentText("Choose your book:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    for(BooksJPAEntity book: booksList){
                        if(book.getTitle().equals(result.get())){
                            isbnName.setText(result.get());
                            isbnCodeBorrow.setText(book.getIsbn());
                            isbnName.setDisable(true);
                        }
                    }
                }


            }
            else{
                AlterDialogError("Error", "Couldn't found books");
                borrowBookClick();
            }


        }
    }




    /**
     * List of book.
     * Method that fill choice box with books that have the user in lendings
     * @param name the name
     */
    public void listOfBook(String name) {
        List<String> choices = new ArrayList<>();
        lendinglist = connection.listOfLending(name);
        BooksJPAEntity book = new BooksJPAEntity();
        if(lendinglist.size() > 0){
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
            Optional<String> result = dialog.showAndWait();

           if (result.isPresent()) {
                if(!result.get().equals("Books")){
                    isbnName.setText(result.get());
                    isbnCodeBorrow.setText(connection.getBookByName(isbnName.getText()));
                }
            }

        }
        else{
            AlterDialogError("Error", "THIS USER DON'T HAVE BOOKS");
            returnBookClick();

        }



    }

    /**
     * List of users.
     * Method that fill choice box with all users
     */
    public void listOfUsers() {
        String name = nameFromChoiceBox.getText();
        List<String> choices = new ArrayList<>();
        users = connection.listOfUsers(name);
        if (users.size() > 0) {
            for (UsersJPAEntity user : users) {
                choices.add(user.getName() + " " + user.getSurname());
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Users", choices);
            dialog.setTitle("List of Users");
            dialog.setHeaderText("List of Users");
            dialog.setContentText("Choose user:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if(!result.get().equals("Users"))  {
                    nameFromChoiceBox.setText(result.get());
                    for(UsersJPAEntity user: users){
                        if((user.getName() + " " + user.getSurname()).equals(result.get())){
                            userCodeBorrow.setText(user.getCode());
                            nameFromChoiceBox.setDisable(true);
                        }
                    }
                }
            }
        } else {
            AlterDialogError("Error", "User don't found");
        }
    }

    /**
     * Alter dialog error.
     * Error window     *
     * @param canntDoIt the cannt do it
     * @param why       the why
     */
    public void AlterDialogError(String canntDoIt, String why) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText(canntDoIt);
        alert.setContentText(why);
        alert.showAndWait();
    }

    /**
     * Alter dialog reservation.
     * Window for make reservation
     * @param reservation the reservation
     */
    public void alterDialogReservation(ReservationJPAEntityFinal reservation) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Book Reservation");
        alert.setHeaderText("This book is not available now");
        alert.setContentText("Would you like to make a reservation of this book?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            api.postRequest(reservation);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void alterDeleteBook() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setHeaderText("Deleting book from app");
        alert.setContentText("Would you like to delete this book?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(connection.bookIsPendingToReturn(inputIsbn.getText()) || api.getRequestBook(inputIsbn.getText())){
                AlterDialogError("ERROR", "This book has pending returning or has reservation!");
                booksClick();
            }
            else{
                deletingBook();
                AlterDialogConfirmation("DONE", "Book deleted!");
                booksClick();

            }

        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void alterDialogDeleting(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User");
        alert.setHeaderText("Deleting user from App");
        alert.setContentText("Would you like to delete this user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if(connection.userHasPendingBooks(inputCode.getText()) || api.getRequestUser(inputCode.getText())){
                AlterDialogError("ERROR", "This user has pending books or reservation!");
                accauntClick();
            }
            else{
                deleting();
                AlterDialogConfirmation("DONE", "User deleted!");
                accauntClick();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    /**
     * Alter dialog confirmation.
     * Confirmation window
     * @param what    the what
     * @param success the success
     */
    public void AlterDialogConfirmation(String what, String success) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(what);
        alert.setContentText(success);
        alert.showAndWait();
    }


    /**
     * Exit click.
     */
    public void exitClick() {
        Platform.exit();
    }

}