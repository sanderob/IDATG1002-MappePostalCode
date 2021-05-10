package no.ntnu.idatg2001.postalCodes.gui.factory;

import javafx.scene.control.Alert;

/**
 * Factory class for dialog, information and alert boxes
 * @author Sander Osvik Brekke
 * @version 10.05.2021
 */
public class DialogFactory extends Alert {

    /**
     * Constructor for an Alert object
     * @param alertType the alert object created
     */
    private DialogFactory(Alert.AlertType alertType) {
        super(alertType);
    }

    /**
     * Method for getting an instance of the dialog object, called by other classes
     * @param type String, the type of Alert box
     * @return the Alert box object created by the factory.
     */
    public static Alert getInstance(String type) {
        return getDialogBox(type);
    }


    /**
     * Method containing the switch case to create the right kind of dialog box.
     * @param type String, type of alert box to create. Either "CONFIRMATION" or "INFORMATION".
     * @return the alert box created by the factory method.
     */
    private static Alert getDialogBox(String type){
        switch (type) {
            case "INFORMATION":
                return new DialogFactory(AlertType.INFORMATION);
            case "CONFIRMATION":
                return new DialogFactory(AlertType.CONFIRMATION);
            default:
                return null;
        }
    }
}
