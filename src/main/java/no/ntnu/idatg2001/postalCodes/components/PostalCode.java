package no.ntnu.idatg2001.postalCodes.components;

/**
 * Class for a postal code
 * @author Sander Osvik Brekke
 * @version 10.05.2021
 */
public class PostalCode {

    private final String postalCode;
    private final String postalArea;
    private final String municipalityCode;
    private final String municipalityName;
    private final String category;

    /**
     * Constructor for the postal code object
     * @param postalCode the code
     * @param postalArea the postal code area
     * @param municipalityCode the municipality code
     * @param municipalityName the municipality name
     * @param category the category
     */
    public PostalCode(String postalCode, String postalArea, String municipalityCode, String municipalityName, String category) {
        this.postalCode = postalCode;
        this.postalArea = postalArea;
        this.municipalityCode = municipalityCode;
        this.municipalityName = municipalityName;
        this.category = category;
    }

    /**
     * Getter for the category
     * @return category of the postal code
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Getter for the postal code
     * @return the postal code of the object
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Getter for the postal area
     * @return the postal area of the postal code number
     */
    public String getPostalArea() {
        return postalArea;
    }

    /**
     * Getter for the municipality code
     * @return the municipality code of the postal code number municipality
     */
    public String getMunicipalityCode() {
        return municipalityCode;
    }

    /**
     * Getter for the municipality name
     * @return the municipality name of the postal code object
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    /**
     * toString-method for the postal code object
     * @return String that represents a postal code
     */
    @Override
    public String toString() {
        return "PostalCode{" +
                "postalCode='" + postalCode + '\'' +
                ", postalArea='" + postalArea + '\'' +
                ", municipalityCode='" + municipalityCode + '\'' +
                ", municipalityName='" + municipalityName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
