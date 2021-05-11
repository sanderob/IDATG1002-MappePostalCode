package no.ntnu.idatg2001.postalCodes.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import no.ntnu.idatg2001.postalCodes.components.PostalCode;
import no.ntnu.idatg2001.postalCodes.components.PostalCodeRegistry;
import no.ntnu.idatg2001.postalCodes.fileSerialization.ReadFile;
import no.ntnu.idatg2001.postalCodes.fileSerialization.ReadType;
import no.ntnu.idatg2001.postalCodes.gui.factory.ViewGenerator;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the main view FXML-file
 * @author Sander Osvik Brekke
 * @version 10.05.2021
 */
public class MainViewController implements Initializable {


    // Defines the FXML-fields used by the controller

    @FXML
    private TableView<PostalCode> tableView;

    @FXML
    private TableColumn<PostalCode, Integer> postalCodeColumn;

    @FXML
    private TableColumn<PostalCode, String> postalAreaColumn;

    @FXML
    private TableColumn<PostalCode, Integer> municipalityCodeColumn;

    @FXML
    private TableColumn<PostalCode, String> municipalityNameColumn;

    @FXML
    private TableColumn<PostalCode, String> categoryColumn;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField searchBar;

    @FXML
    private Label searchLabel;

    @FXML
    private CheckMenuItem darkModeItem;

    private PostalCodeRegistry postalCodeRegistry;
    private ViewGenerator vg;

    /**
     * Setter method for the searchBarChangeListener, used for searching through and filtering the list in the tableview.
     * If the new search term is longer than the last search term (new character is added), it only searches through
     *  the current result list. If the search term is shorter, however, it searches through the complete list from the
     *  registry. The search method is dynamic and updates the status bar to show number of results.
     *
     *  If there are 0 search results, the complete list is shown
     */
    private void setSearchBarChangeListener() {
        this.searchBar.textProperty().addListener((observableValue, oldString, newString) -> {
            this.searchLabel.setText(null);
            ObservableList<PostalCode> oldList;
            ObservableList<PostalCode> newList;
            if (oldString.length() < newString.length()) {
                oldList = tableView.getItems();
            }
            else {
                oldList = postalCodeRegistry.getList();
            }
            newList = FXCollections.observableArrayList();
            for (PostalCode postalCode : oldList) {
                if (postalCode.getPostalCode().contains(newString) || postalCode.getPostalArea().toLowerCase()
                        .contains(newString.toLowerCase())) {
                    newList.add(postalCode);
                }
            }
            tableView.setItems(newList);
            if (newList.isEmpty()) {
                this.setStatusLabelText("");
                searchLabel.setText("0 search results for " + newString);
            } else if (newString.length() == 0) {
                this.setStatusLabelText("");
            }
            else {
                this.setStatusLabelText(this.tableView.getItems().size() + " search results found for: " + newString);
            }
        });
    }

    /**
     * Method called when the user clicks the "import public register"-button, to import the public postal code list
     */
    @FXML
    private void readFromBringClick() {
        readFromFile(ReadType.PUBLIC);
    }

    /**
     * Method called when the user clicks the "import custom registry"-button, to import from a custom .txt-file.
     */
    @FXML
    private void readFromCustomClick() {
        readFromFile(ReadType.CUSTOM);
    }

    /**
     * Method used by the ReadFile-class to choose a file when the user clicks the custom import button.
     * @return File to load and read
     */
    public static File getFile() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        return chooser.showOpenDialog(PostalCodeApplication.getStage());
    }

    public static MainViewController getController() {
        return PostalCodeApplication.getLoader().getController();
    }

    /**
     * Method called by either readFromBringClick() or by readFromCustomClick(), to load and read the files chosen
     * @param type the type of import (public or custom)
     */
    private void readFromFile(ReadType type) {
        this.progressBar.setDisable(false);
        this.progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        new Thread(() -> {
            Platform.runLater(() -> ReadFile.importFromFile(type));
            Platform.runLater(() -> this.progressBar.setProgress(0));
            this.progressBar.setDisable(true);
        }).start();
    }

    /**
     * The method called when the user clicks the clear-button to empty the registry
     */
    @FXML
    private void clearClick() {
        if (vg.getClearAlertBox()) {
            int size = this.postalCodeRegistry.getList().size();
            this.postalCodeRegistry.getList().clear();
            this.setStatusLabelText("Cleared " + size + " Postal codes from the registry");
        }
    }

    /**
     * Method called when the user clicks the about-button
     */
    @FXML
    private void aboutClick() {
        vg.getAboutBox();
    }

    /**
     * Method called when the user clicks the "category explanation"-button
     */
    @FXML
    private void categoryClick() {
        vg.getCategoryExplanationBox();
    }

    /**
     * Method called when the user clicks the "delete postal number"-button to remove a postal number from the list
     */
    @FXML
    private void deleteClick() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            if (vg.getDeleteAlertBox()) {
                postalCodeRegistry.removePostalCode(tableView.getSelectionModel().getSelectedItem());
            }
        } else {
            vg.getNotSelectedAlert();
        }
    }

    /**
     * Method called when the user clicks to change the dark mode button
     */
    @FXML
    private void darkModeClick() {
        if (!darkModeItem.isSelected()) {
            PostalCodeApplication.getScene().getStylesheets().remove("fxml/dark-mode.css");
        }
        else if (darkModeItem.isSelected()){
            PostalCodeApplication.getScene().getStylesheets().add("fxml/dark-mode.css");
        }
    }

    /**
     * Method used to set the text of the status label in the GUI
     * @param string the string to be put as status
     */
    public void setStatusLabelText(String string) {
        statusLabel.setText(string);
    }


    /**
     * Method called when the program starts, to initialize the table view list with correct columns and content
     */
    private void initializeTable() {
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        postalAreaColumn.setCellValueFactory(new PropertyValueFactory<>("postalArea"));
        municipalityCodeColumn.setCellValueFactory(new PropertyValueFactory<>("municipalityCode"));
        municipalityNameColumn.setCellValueFactory(new PropertyValueFactory<>("municipalityName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableView.setItems(postalCodeRegistry.getList());
    }

    /**
     * First method to be called when the fxml-file is being loaded
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.vg = new ViewGenerator();
        this.postalCodeRegistry = PostalCodeRegistry.getInstance();
        this.initializeTable();
        this.setSearchBarChangeListener();
    }
}
