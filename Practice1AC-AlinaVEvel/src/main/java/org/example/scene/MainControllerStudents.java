package org.example.scene;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.SqlConnector;
import org.example.entity.Course;
import org.example.entity.Student;

import java.awt.*;
import java.sql.Connection;


public class MainControllerStudents {

    SqlConnector connector = SqlConnector.getInstance();

    @FXML
    Label errorText;

    @FXML
    TextField inputIdCard;

    @FXML
    Button registrationB;

    @FXML
    TextField inputFirstName;

    @FXML
    TextField inputLastName;

    @FXML
    TextField inputEmail;

    @FXML
    TextField inputPhone;

    private MainSceneContract mainSceneContract;

    @FXML
    ChoiceBox choiceStudent;

    @FXML
    ChoiceBox choiceCourse;


    Connection conn;

    @FXML
    void initialize() {
        initChoiceBoxes();
        registrationB.setOnAction(
                actionEvent -> {

                    String uuid = inputIdCard.getText();
                    String name = inputFirstName.getText();
                    String lastname = inputLastName.getText();
                    String email = inputEmail.getText();
                    String number = inputPhone.getText();

                    if(uuid.isEmpty() || name.isEmpty() || lastname.isEmpty() || email.isEmpty()){

                        AlterDialogError("Can not add student", "Only phone can be empty");
                    }
                    else if (connector.ifExist(uuid)){

                        AlterDialogError("Can not add student", "User exist");
                    }
                    else{
                        Student student = new Student(
                                uuid,
                                name,
                                lastname,
                                email,
                                number

                        );

                        if (mainSceneContract != null) {
                            mainSceneContract.onRegistration(student);
                            inputIdCard.clear();
                            inputFirstName.clear();
                            inputLastName.clear();
                            inputEmail.clear();
                            inputPhone.clear();


                        }
                    }
                }
        );
    }

    public void setMainSceneContract(MainSceneContract mainSceneContract) {
        this.mainSceneContract = mainSceneContract;
    }

    public void AlterDialogError(String canntDoIt, String why){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error alert");
        alert.setHeaderText(canntDoIt);
        alert.setContentText(why);

        alert.showAndWait();
    }


    private void initChoiceBoxes() {

        for (Student student : connector.getStudents()) {
            choiceStudent.getItems().add(student.getId());


        }

        for (Course course : connector.getCourses()) {
            choiceCourse.getItems().add(course.getName());
        }

    }
}
