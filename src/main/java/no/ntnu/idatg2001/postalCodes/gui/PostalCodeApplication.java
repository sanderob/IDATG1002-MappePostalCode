package no.ntnu.idatg2001.postalCodes.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PostalCodeApplication extends Application {

    private static Stage stage;
    private static Scene scene;


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Postal Code Registry Application");
        scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/mainView.fxml")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
