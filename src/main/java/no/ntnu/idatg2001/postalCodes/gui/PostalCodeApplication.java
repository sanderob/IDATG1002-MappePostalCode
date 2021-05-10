package no.ntnu.idatg2001.postalCodes.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GUI application class for loading FXML and starting the GUI of the application
 * @author Sander Osvik Brekke
 * @version 10.05.2021
 */
public class PostalCodeApplication extends Application {

    private static Stage globalStage;
    private static Scene scene;
    private static FXMLLoader loader;


    /**
     * The start method ran by the main method of the class
     * @param stage the stage where the scene is going to be loaded
     * @throws IOException if there is an issue with loading the FXML-file
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Postal Code Registry Application");
        loader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Getter for the current GUI stage
     * @return current GUI stage
     */
    public static Stage getStage() {
        return globalStage;
    }

    /**
     * Getter for the current scene
     * @return current scene
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * Getter for the current FXMLoader, used to get an instance of the controller class
     * @return current FXMLLoader
     */
    public static FXMLLoader getLoader() {
        return loader;
    }

    public static void main(String[] args) {
        launch();
    }
}
