package View_Controller;

import Model.Database;
import Model.DateAndTime;
import Model.ErrorCheck;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerAddViewController {
    
    private Database objDbClass;
    private Connection con;
    
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
    void handleClose(ActionEvent event) throws IOException {
        Parent customerViewParent = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
        Scene customerViewScene = new Scene(customerViewParent);
        Stage customerViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerViewStage.setScene(customerViewScene);
        customerViewStage.show();
    }

    @FXML
    void handleSave(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            
            Timestamp stamp = DateAndTime.getTimestamp();
            String name = nameField.getText();
            String address1 = addressOneField.getText();
            String address2 = addressTwoField.getText();
            String city = cityField.getText();
            
            String zip = zipCodeField.getText();
            String phone = phoneNumberField.getText();
            String country = countryBox.getSelectionModel().getSelectedItem();
            if (ErrorCheck.nullCheck(address1) == false) {
                    return;
                }
            if (ErrorCheck.nullCheck(city) == false) {
                    return;
                }
            if (ErrorCheck.nullCheck(zip) == false) {
                    return;
                }
            if (ErrorCheck.nullCheck(phone) == false) {
                    return;
                }
            if (ErrorCheck.phoneCheck(phone) == false) {
                    return;
                }
            int countryKey = 0;
            String countryCheck = "SELECT countryid FROM country WHERE country = '" + country + "'";
            ResultSet rs1 = con.createStatement().executeQuery(countryCheck);
            while (rs1.next()) {
            countryKey = rs1.getInt("countryid");
            
            }
            int cityKey = 0; 
            String cityCheck = "SELECT cityid FROM city WHERE city = '" + city + "'";
            ResultSet rs2 = con.createStatement().executeQuery(cityCheck);
            if (rs2.next()) {
                cityKey = rs2.getInt("cityid");
            } else {
                String cityInsert = "INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                    + "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1 = con.prepareStatement(cityInsert);
                ps1.setString(1, city);
                ps1.setInt(2, countryKey);
                ps1.setTimestamp(3, stamp);
                ps1.setString(4, LoginScreenController.currentUser);
                ps1.setTimestamp(5, stamp);
                ps1.setString(6, LoginScreenController.currentUser);
                ps1.executeUpdate();
                ResultSet rs3 = con.createStatement().executeQuery(cityCheck);
                while (rs3.next()) {
                cityKey = rs3.getInt("cityid");
                }
            }
            int addressKey = 0;
            String addressCheck = "SELECT addressid FROM address WHERE address = '" + address1 + "'";
            ResultSet rs4 = con.createStatement().executeQuery(addressCheck);
            if (rs4.next()) {
                addressKey = rs4.getInt("addressid");
            } else {
                String addressInsert = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps2 = con.prepareStatement(addressInsert);
                ps2.setString(1, address1);
                ps2.setString(2, address2);
                ps2.setInt(3, cityKey);
                ps2.setString(4, zip);
                ps2.setString(5, phone);
                ps2.setTimestamp(6, stamp);
                ps2.setString(7, LoginScreenController.currentUser);
                ps2.setTimestamp(8, stamp);
                ps2.setString(9, LoginScreenController.currentUser);
                ps2.executeUpdate();
                ResultSet rs5 = con.createStatement().executeQuery(addressCheck);
                while (rs5.next()) {
                addressKey = rs5.getInt("addressid");
                }
            }
            
            String sql = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps3 = con.prepareStatement(sql);
            ps3.setString(1, name);
            ps3.setInt(2, addressKey);
            ps3.setInt(3, 1);
            ps3.setTimestamp(4, stamp);
            ps3.setString(5, LoginScreenController.currentUser);
            ps3.setTimestamp(6, stamp);
            ps3.setString(7, LoginScreenController.currentUser);
            ps3.executeUpdate();

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        
        Parent customerViewParent = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
        Scene customerViewScene = new Scene(customerViewParent);
        Stage customerViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerViewStage.setScene(customerViewScene);
        customerViewStage.show();
    }
    
    @FXML
    private void initialize() {
        countryBox.setValue("USA");
        countryBox.setItems(countryList);
    }

}
