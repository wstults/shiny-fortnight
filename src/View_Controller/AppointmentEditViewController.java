package View_Controller;

import Model.Alerts;
import Model.BusinessHoursException;
import Model.Database;
import Model.DateAndTime;
import Model.ErrorCheck;
import Model.OverlapException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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

public class AppointmentEditViewController {
    
    private Database objDbClass;
    private Connection con;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    
    @FXML
    private TextField appointmentIDField;

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
        // Clicking Cancel returns the user to the Appointments View
        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsViewStage.setScene(appointmentsViewScene);
        appointmentsViewStage.show();
    }

    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            // Retrieve the selected appointment from the database
            String SQL = "SELECT appointment.appointmentid, customer.customerName, appointment.title, appointment.description, appointment.location, appointment.start, appointment.end FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId WHERE appointmentid = '" + AppointmentsViewController.selectedAppointmentID + "'";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                // Perform necessary conversions from UTC time to local time
                Timestamp startStamp = rs.getTimestamp("start");
                LocalDateTime utcTime = DateAndTime.timestampToDateTime(startStamp);
                LocalDateTime zonedTime = DateAndTime.localTime(utcTime);
                Timestamp finalTime = Timestamp.valueOf(zonedTime);
                startField.setText(finalTime.toString().substring(0, 19));
                Timestamp endStamp = rs.getTimestamp("end");
                LocalDateTime utcTime2 = DateAndTime.timestampToDateTime(endStamp);
                LocalDateTime zonedTime2 = DateAndTime.localTime(utcTime2);
                Timestamp finalTime2 = Timestamp.valueOf(zonedTime2);
                // Populate fields from query results
                endField.setText(finalTime2.toString().substring(0, 19));
                appointmentIDField.setText(rs.getString("appointmentid"));
                customerField.setText(rs.getString("customerName"));
                typeField.setText(rs.getString("description"));
                titleField.setText(rs.getString("title"));
                locationField.setText(rs.getString("location"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Lambda expression for handling Save button click
        saveButton.setOnAction((event) -> {
            try {
                PreparedStatement ps;
                // Checking for blank fields
                if (ErrorCheck.nullCheck(titleField.getText()) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(typeField.getText()) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(locationField.getText()) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(startField.getText()) == false) {
                    return;
                }
                if (ErrorCheck.nullCheck(endField.getText()) == false) {
                    return;
                }
                // Convert date and time from local time to UTC
                LocalDateTime utcStart = DateAndTime.utcTime(startField.getText());
                LocalDateTime utcEnd = DateAndTime.utcTime(endField.getText());
                // Put appointment times through exception checks
                ErrorCheck.officeHoursCheck(startField.getText());
                ErrorCheck.officeHoursCheck(endField.getText());
                ErrorCheck.overlapCheck(startField.getText(), endField.getText());
                // Generate Timestamp objects from converted times
                Timestamp finalStart = Timestamp.valueOf(utcStart);
                Timestamp finalEnd = Timestamp.valueOf(utcEnd);
                try {
                    // Update the appointment record in the database
                    String SQL = "UPDATE appointment "
                            + "SET description = '" + typeField.getText() + "', "
                            + "title = '" + titleField.getText() + "', "
                            + "location = '" + locationField.getText() + "', "
                            + "start = '" + finalStart.toString().substring(0, 19) + "', "
                            + "end = '" + finalEnd.toString().substring(0, 19) + "', "
                            + "lastUpdate = '" + DateAndTime.getTimestamp() + "', "
                            + "lastUpdateBy = '" + LoginScreenController.currentUser + "' "
                            + "WHERE appointmentid = " + appointmentIDField.getText() + ";";
                    ps = con.prepareStatement(SQL);
                    ps.executeUpdate();
                    // Display pop up confirmation for successful edit
                    Alerts.editApptConfirmation.run();
                    // Return the user to the Appointments View
                    Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
                    Scene appointmentsViewScene = new Scene(appointmentsViewParent);
                    Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appointmentsViewStage.setScene(appointmentsViewScene);
                    appointmentsViewStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (BusinessHoursException ex) {
                Logger.getLogger(AppointmentEditViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OverlapException ex) {
                Logger.getLogger(AppointmentEditViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
