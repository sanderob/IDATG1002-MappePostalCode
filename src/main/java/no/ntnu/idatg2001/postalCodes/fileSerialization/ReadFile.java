package no.ntnu.idatg2001.postalCodes.fileSerialization;

import no.ntnu.idatg2001.postalCodes.components.PostalCode;
import no.ntnu.idatg2001.postalCodes.components.PostalCodeRegistry;
import no.ntnu.idatg2001.postalCodes.gui.MainViewController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;

/**
 * Class for reading from a file and adding the file content to the registry
 * @author Sander Osvik Brekke
 * @version 10.05.2021
 */
public class ReadFile {

    private ReadFile(){}


    //Defining the global class fields
    private static File file;
    private static String fileName;
    private static PostalCodeRegistry postalCodeRegistry;

    /**
     * Method for choosing the file object for the public reading
     */
    private static void readFromPublic() {
        file = new File("src/main/resources/postalCodeFile/postalcoderegister.txt");
    }

    /**
     * Method for choosing the file object for the custom reading
     */
    private static void readFromCustomFile() {
        file = MainViewController.getFile();

    }

    /**
     * Method for reading the file that has been chosen. Reads all files with the same order of fields per line,
     * expecting a standard format
     */
    private static void readFile() {
        PostalCode postalCode;
        String thisLine;
        if (file == null) {
            throw new NullPointerException("No file is selected");
        }
        else  {
            try (FileReader fr = new FileReader(file.getAbsolutePath(), Charset.forName("windows-1252"));
                 BufferedReader br = new BufferedReader(fr)) {
                while ((thisLine = br.readLine()) != null) {
                    String[] array = thisLine.split("\t");
                    if (array.length == 5) {
                        postalCode = new PostalCode(String.format("%04d", Integer.parseInt(array[0])), array[1],
                                String.format("%04d", Integer.parseInt(array[2])), array[3], array[4]);
                        if (!(postalCodeRegistry.postalCodeExists(postalCode))) {
                            postalCodeRegistry.addPostalCode(postalCode);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The method organizing the file object and the read method, to update the table view and status label with the
     * imported postal codes
     * @param type The type of import
     */
    public static void importFromFile(ReadType type) {
        postalCodeRegistry = PostalCodeRegistry.getInstance();
        MainViewController mainViewController = MainViewController.getController();
        int oldSize = postalCodeRegistry.getList().size();
        if (type == ReadType.PUBLIC) {
            readFromPublic();
            fileName = "The public registry";
        }
        else if (type == ReadType.CUSTOM) {
            readFromCustomFile();
            if (file != null) {
                fileName = file.getName();
            }
        }
        if (file != null) {
            readFile();
            int newSize = postalCodeRegistry.getList().size();
            mainViewController.setStatusLabelText("Loaded " + (newSize - oldSize) +
                    " postal codes from " + fileName);
        } else {
            mainViewController.setStatusLabelText("No file was uploaded");
        }
    }
}
