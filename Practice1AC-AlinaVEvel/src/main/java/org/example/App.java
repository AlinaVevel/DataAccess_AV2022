package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.scene.MainControllerStudents;


/**
 * Hello world!
 *
 */
public class App extends Application
{
    SqlConnector connector = SqlConnector.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene/main.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(450);
        stage.setWidth(630);
        stage.show();


        MainControllerStudents mainController = loader.getController();
        mainController.setMainSceneContract( student -> {
            connector.insertStudent(student);
        });

    }
}
