package View_Controller;

import Model.Alerts;
import Model.BusinessHoursException;
import Model.Database;
import Model.DateAndTime;
import Model.ErrorCheck;
import Model.OverlapException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppointmentAddViewController {

    private Database objDbClass;
    private Connection con;
    
    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    
    @FXML
    private TextField customerIDField;

    @FXML
    private TextField customerField;
    
    @FXML
    private TextField titleField;

    @FXML
    private TextField typeField;
    
    @FXML
    private TextField locationField;

    @FXML
    private TextField startField;

    @FXML
    private TextField endField;

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        // Pressing Cancel returns the user to the CustomerView
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
        // Retrieves customerid and customerName from the DB to populate the form
        try {
            String SQL = "SELECT customerid, customerName FROM customer WHERE customerid = '" + CustomerViewController.selectedCustomerID + "'";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                customerIDField.setText(rs.getString("customerid"));
                customerField.setText(rs.getString("customerName"));
            }        
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Lambda expression to handle Save button press
        saveButton.setOnAction((event) -> {
            PreparedStatement ps;
            try {
                Timestamp stamp = DateAndTime.getTimestamp();
                String customerID = customerIDField.getText();
                String title = titleField.getText();
                String type = typeField.getText();
                String location = locationField.getText();
                String start = startField.getText();
                String end = endField.getText();
                // Checking for blank fields
                if (ErrorCheck.nullCheck(title) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(type) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(location) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(start) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(end) == false) {
                    return;
                }
                // Perform necessary time conversions
                LocalDateTime utcStart = DateAndTime.utcTime(start);
                LocalDateTime utcEnd = DateAndTime.utcTime(end);
                // Put appointment times through exception checks
                ErrorCheck.officeHoursCheck(start);
                ErrorCheck.officeHoursCheck(end);
                ErrorCheck.overlapCheck(start, end);
                // Create the new appointment in the database
                String sql = "INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, customerID);
                ps.setString(2, title);
                ps.setString(3, type);
                ps.setString(4, location);
                ps.setString(5, LoginScreenController.currentUser);
                ps.setString(6, "none");
                ps.setString(7, utcStart.toString());
                ps.setString(8, utcEnd.toString());
                ps.setTimestamp(9, stamp);
                ps.setString(10, LoginScreenController.currentUser);
                ps.setTimestamp(11, stamp);
                ps.setString(12, LoginScreenController.currentUser);
                ps.executeUpdate();
                // Display pop up alert confirming successful scheduling
                Alerts.newApptConfirmation.run();
                // Return the user to the Customer View
                Parent customerViewParent = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
                Scene customerViewScene = new Scene(customerViewParent);
                Stage customerViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                customerViewStage.setScene(customerViewScene);
                customerViewStage.show();
            } catch (SQLException ex){
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(AppointmentAddViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BusinessHoursException ex) {
                Logger.getLogger(AppointmentAddViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OverlapException ex) {
                Logger.getLogger(AppointmentAddViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
