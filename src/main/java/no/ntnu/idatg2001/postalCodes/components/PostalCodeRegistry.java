package no.ntnu.idatg2001.postalCodes.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for a PostalCodeRegistry, containing postal codes and all necessary information
 * @author Sander Osvik Brekke
 * @version 10.05.2021
 */
public class PostalCodeRegistry {

    private static PostalCodeRegistry postalCodeRegistry;
    private final ObservableList<PostalCode> list;

    /**
     * Private method for creating an instance
     */
    private PostalCodeRegistry() {
        this.list = FXCollections.observableArrayList();
    }

    /**
     * Public method for getting an instance, using a singleton instantiation pattern
     * @return the current registry
     */
    public static PostalCodeRegistry getInstance() {
        if (postalCodeRegistry == null) {
            postalCodeRegistry = new PostalCodeRegistry();
        }
        return postalCodeRegistry;
    }

    /**
     * Method for returning the list of a registry object
     * @return the list of postal codes for the current registry
     */
    public ObservableList<PostalCode> getList() {
        return this.list;
    }

    /**
     * Method to check if a postal code already is present in the list of a registry
     * @param pc the postal code to check if present
     * @return true or false, if the postal code is present
     */
    public boolean postalCodeExists(PostalCode pc) {
        for (PostalCode newPC : this.list) {
            if (newPC.getPostalCode().equals(pc.getPostalCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to add a postal code object to the list of the current registry
     * @param postalCodeObject the postal code object to add
     */
    public void addPostalCode(PostalCode postalCodeObject) {
        this.list.add(postalCodeObject);
    }
}
