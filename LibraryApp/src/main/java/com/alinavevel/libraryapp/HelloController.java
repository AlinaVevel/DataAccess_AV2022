package com.alinavevel.libraryapp;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.sql.Date;

public class HelloController {
    SessionHibernate connection = SessionHibernate.getInstance();
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

    public void accauntClick(){
        registrationBooks.setVisible(false);
        RegistrationPane.setVisible(true);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
        inputFirstName.setDisable(true);
        inputLastName.setDisable(true);
        inputBirthday.setDisable(true);


    }

    public void booksClick(){
        registrationBooks.setVisible(true);
        RegistrationPane.setVisible(false);
        confirmModiifPane.setVisible(true);
        confirmDeletPane.setVisible(false);
    }


    public void addUserClick() {

        inputFirstName.setDisable(false);
        inputLastName.setDisable(false);
        inputBirthday.setDisable(false);
        confirmModiifPane.setVisible(false);
        confirmDeletPane.setVisible(true);
    }

    public void submit() {

        String code = inputCode.getText();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();
        Date date = Date.valueOf(inputBirthday.getValue());

        if (code.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || code.isBlank()) {

            //error alterDialog
        }
        else{
            Users user = new Users(code, firstName, lastName, date);

            connection.insertUser(user);


        }

    }
}