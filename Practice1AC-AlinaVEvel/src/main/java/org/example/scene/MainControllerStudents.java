package org.example.scene;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.SqlConnector;
import org.example.entity.Course;
import org.example.entity.Student;

import java.util.ArrayList;

// class for inflate java fx
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



    @FXML
    ChoiceBox choiceStudent;

    @FXML
    ChoiceBox choiceCourse;

    @FXML
    ChoiceBox report_student_id;

    @FXML
    Button enrollButton;

    @FXML
    TextArea textArea;

    @FXML
    Button printButton;




    @FXML
    void initialize() {
        initChoiceBoxes();
        initChoiceBoxReport();
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
                    else if (connector.ifStudentExists(uuid) == -1){

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

                        if (student != null) {
                            connector.insertStudent(student);
                            inputIdCard.clear();
                            inputFirstName.clear();
                            inputLastName.clear();
                            inputEmail.clear();
                            inputPhone.clear();
                            choiceStudent.getItems().clear();
                            choiceCourse.getItems().clear();
                            initChoiceBoxes();



                        }
                    }
                }
        );
        enrollButton.setOnAction(
                actionEvent -> {
                    String idStudent = (String) choiceStudent.getValue();
                    String nameCourse = (String) choiceCourse.getValue();
                    int idCourse = connector.getIdCourse(nameCourse);
                    if(connector.checkStudentForenrollment(idStudent) == -1){
                        AlterDialogError("Error", "Student cann't enroll in this course, because he has pending subjects");
                    }
                    else if(connector.checkStudentInEnrollment(idStudent, idCourse) == -1){
                        AlterDialogError("Error", "Student allredy finished this course");
                    }
                    else{
                        connector.inserEnrollment(idStudent, idCourse);
                    }

                }
        );
        printButton.setOnAction(
                actionEvent ->{
                    ArrayList<String> values = connector.studentsReport("123456789A");
                    String allValues = "";
                    for (String s:values) {
                        allValues = allValues + " " + s + "\n";
                    }
                    textArea.setText(allValues);
                }
        );
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
            //choiceStudent.getItems().set(0, student.getId());
        }


        for (Course course : connector.getCourses()) {
            choiceCourse.getItems().add(course.getName());
        }



    }

    private void initChoiceBoxReport(){
        for (Student student : connector.getStudents()) {
            report_student_id.getItems().add(student.getId());
            //choiceStudent.getItems().set(0, student.getId());
        }
    }


}
