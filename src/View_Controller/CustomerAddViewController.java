package View_Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CustomerAddViewController {
    
    @FXML
    ObservableList<String> countryList = FXCollections.observableArrayList(
    "USA", "Japan", "Australia", "Russia", "Britain");

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressOneField;

    @FXML
    private TextField addressTwoField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField zipCodeField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button saveButton;

    @FXML
    private Button closeButton;

    @FXML
    private ChoiceBox<String> countryBox;
    

    @FXML
    void handleClose(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {

    }
    
    @FXML
    private void initialize() {
        countryBox.setValue("USA");
        countryBox.setItems(countryList);
    }

}
