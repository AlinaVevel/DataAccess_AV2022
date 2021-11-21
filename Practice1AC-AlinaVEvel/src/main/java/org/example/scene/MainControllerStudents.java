package org.example.scene;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.MyRunner;
import org.example.SqlConnector;
import org.example.entity.Course;
import org.example.entity.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    Button btnXml;


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

                    if (uuid.isEmpty() || name.isEmpty() || lastname.isEmpty()) {

                        AlterDialogError("Can not add student", "Name and lastname can not be empty");
                    } else if (connector.ifStudentExists(uuid) == -1) {

                        AlterDialogError("Can not add student", "Student exist");
                    } else {
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
                            report_student_id.getItems().clear();
                            initChoiceBoxes();
                            AlterDialogConfirmation("The student registered", "DONE");
                        }
                    }
                }
        );
        enrollButton.setOnAction(

                actionEvent -> {

                    String idStudent = (String) choiceStudent.getValue();
                    String nameCourse = (String) choiceCourse.getValue();
                    int idCourse = connector.getIdCourse(nameCourse);
                    if (connector.checkStudentForenrollment(idStudent) == -1) {
                        AlterDialogError("Error", "Student can't enroll in this course, because this student has pending subjects");
                    } else if (connector.checkStudentInEnrollment(idStudent, idCourse) == -1) {
                        AlterDialogError("Error", "Student already finished this course");
                    } else {
                        connector.insertEnrollment(idStudent, idCourse);
                        AlterDialogConfirmation("The student has successfully enroll", "DONE");
                    }

                }
        );
        report_student_id.setOnAction(
                actionEvent -> {

                    String idStudent = (String) report_student_id.getValue();
                    ArrayList<String> values = connector.studentsReport(idStudent);
                    StringBuilder allValues = new StringBuilder();
                    for (String s : values) {
                        allValues.append(" ").append(s).append("\n");
                    }
                    textArea.setText(allValues.toString());
                }
        );

        printButton.setOnAction(
                actionEvent -> {
                    //writeInFile(stringReport());
                    hndlOpenFile();
                }
        );

        btnXml.setOnAction(
                actionEvent -> {
                    FileChooser fileChooser = new FileChooser();//class for working with dialogWindow
                    fileChooser.setTitle("Open Document");//Title
                    FileChooser.ExtensionFilter extFilter =
                            new FileChooser.ExtensionFilter("HTML files (*.xml)", "*.xml");// only XML
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showOpenDialog(null);//show

                    if (file != null) {
                        // List<Student> lines = runner.parseUsers();
                        if (connector.transactionXML(file)) {
                            AlterDialogConfirmation("Thansaction was successful", "DONE");
                            initChoiceBoxes();
                        } else {
                            AlterDialogError("Can not to finish transaction", "An error occurred");
                        }

                    } else {
                        AlterDialogError("Can not to finish transaction", "Error in file");
                    }
                }
        );

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

    private void initChoiceBoxes() {

        for (Student student : connector.getStudents()) {
            choiceStudent.getItems().add(student.getId());
            report_student_id.getItems().add(student.getId());
            //choiceStudent.getItems().set(0, student.getId());
        }


        for (Course course : connector.getCourses()) {
            choiceCourse.getItems().add(course.getName());
        }
    }

    public void writeInFile(String reportValue, File file) {

        String idStudent = (String) report_student_id.getValue();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false)); //for append true
            writer.write("Student id: " + idStudent + "\n");
            writer.write(String.valueOf(reportValue));
            //writer.write("\n"); for append
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String stringReport() {
        String idStudent = (String) report_student_id.getValue();
        ArrayList<String> values = connector.studentsReport(idStudent);
        StringBuilder allValues = new StringBuilder();
        for (String s : values) {
            allValues.append(" ").append(s).append("\n");
        }
        return allValues.toString();
    }

    private void hndlOpenFile() {
        FileChooser fileChooser = new FileChooser();//Class for working with file
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");//only txt
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            writeInFile(stringReport(), file);
            //Save
            AlterDialogConfirmation("File saved", "DONE");

        }
    }


}
