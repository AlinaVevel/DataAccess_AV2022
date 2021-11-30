package org.example.scene;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.MyRunner;
import org.example.SqlConnector;
import org.example.entity.Course;
import org.example.entity.Student;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class MainControllerStudents that controls the main.fxml of javafx
 *

 */
public class MainControllerStudents {

    /**
     * The Connector.
     */
    SqlConnector connector = SqlConnector.getInstance();


    /**
     * The Error text.
     */
    @FXML
    Label errorText;

    /**
     * The Input id card.
     */
    @FXML
    TextField inputIdCard;

    /**
     * The Registration b.
     */
    @FXML
    Button registrationB;

    /**
     * The Input first name.
     */
    @FXML
    TextField inputFirstName;

    /**
     * The Input last name.
     */
    @FXML
    TextField inputLastName;

    /**
     * The Input email.
     */
    @FXML
    TextField inputEmail;

    @FXML
    TextField inputIdEnroll;

    @FXML
    TextField outputNameEnroll;

    /**
     * The Input phone.
     */
    @FXML
    TextField inputPhone;


    /**
     * The Choice student.
     */
    @FXML
    ChoiceBox choiceStudent;

    /**
     * The Choice course.
     */
    @FXML
    ChoiceBox choiceCourse;

    /**
     * The Report student id.
     */
    @FXML
    ChoiceBox report_student_id;

    /**
     * The Enroll button.
     */
    @FXML
    Button enrollButton;

    /**
     * The Text area.
     */
    @FXML
    TextArea textArea;

    /**
     * The Print button.
     */
    @FXML
    Button printButton;

    /**
     * The btnXml button
     */
    @FXML
    Button btnXml;

    @FXML
    Button btnSearch;


    /**
     * Method to initialize the elements of fxml
     */
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

                    String regEmail = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
                    String regPhone = "^[0-9]{9}";



                    if (uuid.isEmpty() || name.isEmpty() || lastname.isEmpty() || uuid.isBlank()) {

                        AlterDialogError("Can not add student", "Name, Last name and id card can't be empty");
                    } else if (connector.ifStudentExists(uuid) == -1) {

                        AlterDialogError("Can not add student", "Student exist");


                    }

                    else if(!email.isEmpty() && !email.matches(regEmail)) {
                        AlterDialogError("Error", "Email bad formated");
                    }
                     else if(!number.isEmpty() && !number.matches(regPhone)){
                        AlterDialogError("Error", "Number bad formated");
                    }

                    else {
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

                    }

                }
        );

        btnSearch.setOnAction(

                actionEvent -> {
                    String idStudent = inputIdEnroll.getText();
                    String name = connector.getNameStudent(idStudent);
                    if(name == null){
                        AlterDialogError("Error", "Student don't find");
                        inputIdEnroll.setText("");
                        }
                    else{
                        outputNameEnroll.setText(name);
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
                        try {
                            if (connector.transactionXML(file)) {

                                initChoiceBoxes();
                            }
                        } catch (SAXException e) {
                            AlterDialogError("Error", e.getMessage());
                        }

                    } else {
                        AlterDialogError("Can not to finish transaction", "Error in file");
                    }
                }
        );

    }

    /**
     * Method that show the Alter Dialog of ERROR
     *
     * @param canntDoIt - this string will say that is an error
     * @param why       - this string will explain why
     */
    public void AlterDialogError(String canntDoIt, String why) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error alert");
        alert.setHeaderText(canntDoIt);
        alert.setContentText(why);
        alert.showAndWait();
    }

    /**
     * Method that show the Alter Dialog of CONFIRMATION
     *
     * @param what    - explain what happened
     * @param success - this string will explain that the execution was successful
     */
    public void AlterDialogConfirmation(String what, String success) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(what);
        alert.setContentText(success);
        alert.showAndWait();
    }

    /**
     * Method that full the choiceBoxes with the data
     *
     */
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

    /**
     * Method that write in file
     *
     * @param reportValue - this string will be written
     * @param file        - in this file will write the program
     */
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

    /**
     * Method that return String that receive from a data base
     *
     * @return String of dates
     */
    public String stringReport() {
        String idStudent = (String) report_student_id.getValue();
        ArrayList<String> values = connector.studentsReport(idStudent);
        StringBuilder allValues = new StringBuilder();
        for (String s : values) {
            allValues.append(" ").append(s).append("\n");
        }
        return allValues.toString();
    }

    /**
     * Method that open FileChooser and call method write in file if file is not null
     *
     */
    private void hndlOpenFile() {
        FileChooser fileChooser = new FileChooser();
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
