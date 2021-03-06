package View_Controller;

import Model.Alerts;
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

public class CustomerEditViewController {
    
    @FXML
    ObservableList<String> countryList = FXCollections.observableArrayList(
    "USA", "Japan", "Australia", "Russia", "Britain");
    
    private Database objDbClass;
    private Connection con;
    private String country;
    
    @FXML
    private TextField customerIDField;

    @FXML
    private TextField nameField;
    
    @FXML
    private TextField activeField;

    @FXML
    private TextField addressOneField;

    @FXML
    private TextField addressTwoField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField zipCodeField;

    @FXML
    private TextField phoneField;
    
    @FXML
    private ChoiceBox<String> countryBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        // Clicking Cancel returns the user to the Customer View
        Parent customerViewParent = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
        Scene customerViewScene = new Scene(customerViewParent);
        Stage customerViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerViewStage.setScene(customerViewScene);
        customerViewStage.show();
    }

    @FXML
    void handleSave(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        PreparedStatement ps;
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            // Establish variables for populate the database statement
            Timestamp stamp = DateAndTime.getTimestamp();
            String name = nameField.getText();
            String active = activeField.getText();
            String address1 = addressOneField.getText();
            String address2 = addressTwoField.getText();
            String city = cityField.getText();
            String zip = zipCodeField.getText();
            String phone = phoneField.getText();
            country = countryBox.getSelectionModel().getSelectedItem();
            // Checking for blank fields
            if (ErrorCheck.nullCheck(active) == false) {
                    return;
                }
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
            // Retrive the country ID from the database
            int countryKey = 0;
            String countryCheck = "SELECT countryid FROM country WHERE country = '" + country + "'";
            ResultSet rs1 = con.createStatement().executeQuery(countryCheck);
            while (rs1.next()) {
            countryKey = rs1.getInt("countryid");
            }
            // Retrieve the city ID from the database, or create a new city if necessary
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
            // Retrieve the address ID from the database, or create a new address if necessary
            int addressKey = 0;
            String addressCheck = "SELECT addressid FROM address WHERE address = '" + address1 + "' AND address2 = '" + address2 + "' AND postalCode = '" + zip + "' AND phone = '" + phone + "'";
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
            // Update the database record with the edited customer data
            String SQL = "UPDATE customer "
                    + "SET customerName = '" + nameField.getText() + "', "
                    + "addressId = '" + addressKey + "', "
                    + "active = '" + active + "', "
                    + "lastUpdate = '" + stamp + "', "
                    + "lastUpdateBy = '" + LoginScreenController.currentUser + "' "
                    + "WHERE customerid = " + customerIDField.getText() + ";";
            ps = con.prepareStatement(SQL);
            ps.executeUpdate();
            // Display pop up alert confirming successful edit
            Alerts.editCustomerConfirmation.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the user to the Customer View
        Parent customerViewParent = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
        Scene customerViewScene = new Scene(customerViewParent);
        Stage customerViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerViewStage.setScene(customerViewScene);
        customerViewStage.show();
    }
    
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            // Retrieve the selected customer's data from the database
            String SQL = "SELECT customer.customerid, customer.customerName, customer.active, address.address, address.address2, city.city, country.country, address.postalCode, address.phone FROM customer INNER JOIN address on address.addressid = customer.addressId INNER JOIN city on city.cityid = address.cityId INNER JOIN country on country.countryid = city.countryId WHERE customerid = " + CustomerViewController.selectedCustomerID + ";";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                // Populate fields from query results
                customerIDField.setText(rs.getString("customerid"));
                nameField.setText(rs.getString("customerName"));
                activeField.setText(rs.getString("active"));
                addressOneField.setText(rs.getString("address"));
                addressTwoField.setText(rs.getString("address2"));
                cityField.setText(rs.getString("city"));
                country = rs.getString("country");
                zipCodeField.setText(rs.getString("postalCode"));
                phoneField.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Set initial country choicebox value, and populate choices
        countryBox.setValue(country);
        countryBox.setItems(countryList);
    }
}
