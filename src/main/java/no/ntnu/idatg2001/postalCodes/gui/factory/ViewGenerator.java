package no.ntnu.idatg2001.postalCodes.gui.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Class for creating different elements needed in the GUI
 * @author Sander Osvik Brekke
 * @version 10.05.2021
 */
public class ViewGenerator {

    /**
     * Shows the about box, shown when the user clicks the about-button, with all relevant text
     */
    public void getAboutBox() {
        Alert alert = DialogFactory.getInstance("INFORMATION");
        alert.setTitle("Postal Code Registry Application – About");
        alert.setHeaderText("ABOUT");
        alert.setContentText("This is an application created by (C) Sander O. Brekke. \n " +
                "Made with love on May 10th 2021.");
        alert.showAndWait();
    }

    /**
     * Shows the category explanation box, shown when the user clicks the category explanation-button, with all
     * relevant text
     */
    public void getCategoryExplanationBox() {
        Alert alert = DialogFactory.getInstance("INFORMATION");
        alert.setTitle("Postal Code Registry Application – Help");
        alert.setHeaderText("Postal Code Category Guide");
        alert.setContentText("B = Address and post box \n" +
                "G = Address \n" +
                "P = Post box \n" +
                "S = Service postal number  \n" +
                "F = Several uses");
        alert.showAndWait();
    }

    /**
     * Method that asks the user if it really wants to clear the list of items
     * @return true or false
     */
    public boolean getClearAlertBox() {
        Alert alert = DialogFactory.getInstance("CONFIRMATION");
        alert.setTitle("WARNING!");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Are you sure you want to clear the list? \n All items will be gone. This action is " +
                "irreversible");

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }

    /**
     * Method that asks the user if it really wants to delete the selected item
     * @return true or false
     */
    public boolean getDeleteAlertBox() {
        Alert alert = DialogFactory.getInstance("CONFIRMATION");
        alert.setTitle("WARNING!");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Are you sure you want to delete the selected item? \n This action is " +
                "irreversible");

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }

    /**
     * Method to tell the user that there is not selected an object when the deletion is triggered
     */
    public void getNotSelectedAlert() {
        Alert alert = DialogFactory.getInstance("INFORMATION");
        alert.setTitle("Failed to delete");
        alert.setHeaderText("No selected object");
        alert.setContentText("Failed to delete because there is no object selected to delete");
    }
}
