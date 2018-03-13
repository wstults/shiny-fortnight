package View_Controller;

import Model.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerDetailViewController {

    private Database objDbClass;
    private Connection con;
    
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
    private TextField countryField;

    @FXML
    private TextField zipCodeField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button closeButton;

    @FXML
    void handleClose(ActionEvent event) throws IOException {
        // Clicking Close returns the user to the Customer View
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
            String SQL = "SELECT customer.customerName,  customer.active, address.address, address.address2, city.city, country.country, address.postalCode, address.phone FROM customer INNER JOIN address on address.addressid = customer.addressId INNER JOIN city on city.cityid = address.cityId INNER JOIN country on country.countryid = city.countryId WHERE customerid = " + CustomerViewController.selectedCustomerID + ";";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                // Populate fields from query results
                nameField.setText(rs.getString("customerName"));
                activeField.setText(rs.getString("active"));
                addressOneField.setText(rs.getString("address"));
                addressTwoField.setText(rs.getString("address2"));
                cityField.setText(rs.getString("city"));
                countryField.setText(rs.getString("country"));
                zipCodeField.setText(rs.getString("postalCode"));
                phoneField.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
