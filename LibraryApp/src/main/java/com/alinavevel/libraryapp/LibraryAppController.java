package com.alinavevel.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.sql.Date;
import java.time.LocalDate;

public class LibraryAppController extends SessionHibernate {

    SessionHibernate connection = new SessionHibernate();
    States estado = States.ACCAUNT;

//region fxml
    @FXML
    private GridPane RegistrationPane;

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

    //endregion


    public void accauntClick(){
        RegistrationPane.setVisible(true);
        registrationBooks.setVisible(false);
        borowPane.setVisible(false);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputBirthday.setDisable(true);
        estado = States.ACCAUNT;


    }

    public void booksClick(){
        registrationBooks.setVisible(true);
        RegistrationPane.setVisible(false);
        borowPane.setVisible(false);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputIsbn.setDisable(true);
        inputTitle.setDisable(true);
        inputCopies.setDisable(true);
        inputPublisher.setDisable(true);
        inputIsbn.setDisable(false);


        estado = States.BOOK;
    }


    public void addUserClick() {

        if(estado == States.ACCAUNT){
            inputCode.setDisable(false);
            inputFirstName.setDisable(false);
            inputLastName.setDisable(false);
            inputBirthday.setDisable(false);
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            estado = States.ADDUSER;
        }
        if(estado == States.BOOK){
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            inputIsbn.setDisable(false);
            inputTitle.setDisable(false);
            inputCopies.setDisable(false);
            inputPublisher.setDisable(false);
            estado = States.ADDBOOK;

        }




    }

    public void borrowBookClick(){
        registrationBooks.setVisible(false);
        RegistrationPane.setVisible(false);

        confirmDeletPane.setVisible(true);
        borowPane.setVisible(true);
        estado = States.BORROWBOOK;
    }

    public void submit() {
        if(estado == States.ADDUSER) {
            addUser();
            panelsDown();
            estado = States.ACCAUNT;
        }
        if(estado == States.SEARCHUSER){
            searchUser();
            panelsDown();
            estado = States.ACCAUNT;
        }

        if(estado == States.MODIFYUSER){
            updateUser();
            panelsDown();
            estado = States.ACCAUNT;
        }

        if(estado == States.ADDBOOK){
            addBook();
            panelsDown();
            estado = States.BOOK;
        }
        if(estado == States.SEARCHBOOK){
            searchBook();
            panelsDown();
            estado = States.BOOK;
        }

        if(estado == States.MODIFYBOOK){
            updateBook();
            panelsDown();
            estado = States.BOOK;
        }
    }

    public void panelsDown(){

        confirmDeletPane.setVisible(false);
        confirmModiifPane.setVisible(true);
    }

    public void searchClick(){
        if(estado == States.ACCAUNT){
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            estado = States.SEARCHUSER;
        }
        if(estado == States.BOOK){
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            estado = States.SEARCHBOOK;
        }


    }

    public void addUser(){

        String code = inputCode.getText();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();

        Date date = null;
        try{
            date = Date.valueOf(inputBirthday.getValue());

        }
        catch (Exception e){

        }
        if(code.isEmpty() || firstName.isEmpty() || lastName.isEmpty()){
            AlterDialogError("Insert failed", "Some fields are empty");
        }
        if(connection.userExists(code)){
            AlterDialogError("Insert failed", "User exists");
        }
        else{
            connection.insertUser(code,firstName,lastName,date);
            AlterDialogConfirmation("User registered","DONE!" );
        }







    }

    public void addBook(){
        String isbn = inputIsbn.getText();
        String title = inputTitle.getText();
        String publisher = inputPublisher.getText();
        int copies = (int) inputCopies.getValue();

        if(isbn.isEmpty() || title.isEmpty() || publisher.isEmpty()){
            AlterDialogError("Insert failed", "Some fields are empty");
        }

        if(connection.bookExists(isbn)){
            AlterDialogError("Insert failed", "Book exists");
        }

        else{
            connection.insertBook(isbn,title,copies,publisher);
            AlterDialogConfirmation("Book registered","DONE!" );
        }

    }

    public void updateUser(){
        String code = inputCode.getText();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();
        Date date = Date.valueOf(inputBirthday.getValue());
        connection.updatingUser(code,firstName,lastName,date);
    }

    public void updateBook(){
        String isbn = inputIsbn.getText();
        String title = inputTitle.getText();
        String publisher = inputPublisher.getText();
        int copies = (int) inputCopies.getValue();
        connection.updatingBook(isbn,title, copies, publisher );
    }

    public void returnClick(){
        registrationBooks.setVisible(false);
        RegistrationPane.setVisible(true);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputBirthday.setDisable(true);
        estado = States.ACCAUNT;
    }

    public void modifyClick(){
        if(estado == States.ACCAUNT){
            confirmModiifPane.setVisible(false);
            confirmDeletPane.setVisible(true);
            inputFirstName.setDisable(false);
            inputLastName.setDisable(false);
            inputBirthday.setDisable(false);
            inputCode.setDisable(true);
            estado = States.MODIFYUSER;
        }
       if(estado == States.BOOK){
           confirmModiifPane.setVisible(false);
           confirmDeletPane.setVisible(true);
           inputIsbn.setDisable(true);
           inputCopies.setDisable(false);
           inputPublisher.setDisable(false);
           inputTitle.setDisable(false);
           estado = States.MODIFYBOOK;
       }
    }

    public void searchUser(){
        UsersJPAEntity user;
        String code = inputCode.getText();
        user = connection.getUserById(code);
        inputFirstName.setText(user.getName());
        inputLastName.setText(user.getSurname());
        inputBirthday.setValue(LocalDate.parse(user.getBirthdate().toString()));
    }

    public void searchBook(){
        BooksJPAEntity book;
        String isbn = inputIsbn.getText();
        book = connection.getBookById(isbn);
        inputIsbn.setText(book.getIsbn());
        inputTitle.setText(book.getTitle());
        inputCopies.setValue(book.getCopies());
        inputPublisher.setText(book.getPublisher());
    }

    public void AlterDialogError(String canntDoIt, String why) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error alert");
        alert.setHeaderText(canntDoIt);
        alert.setContentText(why);
        alert.showAndWait();
    }

    public void AlterDialogConfirmation(String what, String success) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(what);
        alert.setContentText(success);
        alert.showAndWait();
    }



}