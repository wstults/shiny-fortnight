package View_Controller;

import Model.Database;
import Model.DateAndTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
    private TextField typeField;

    @FXML
    private TextField consultantField;

    @FXML
    private TextField startField;

    @FXML
    private TextField endField;

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsViewStage.setScene(appointmentsViewScene);
        appointmentsViewStage.show();
        
    }

    /*@FXML
    void handleSave(ActionEvent event) throws ClassNotFoundException, SQLException {
        PreparedStatement ps;
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            String SQL = "UPDATE appointment "
                    + "SET description = '" + typeField.getText() + "', "
                    + "contact = '" + consultantField.getText() + "', "
                    + "start = '" + startField.getText() + "', "
                    + "end = '" + endField.getText() + "', "
                    + "lastUpdate = '" + DateAndTime.getTimestamp() + "', "
                    + "lastUpdateBy = '" + LoginScreenController.currentUser + "' "
                    + "WHERE appointmentid = " + appointmentIDField.getText() + ";";
            ps = con.prepareStatement(SQL);
            ps.executeUpdate();
            Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
            Scene appointmentsViewScene = new Scene(appointmentsViewParent);
            Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appointmentsViewStage.setScene(appointmentsViewScene);
            appointmentsViewStage.show();
        
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            String SQL = "SELECT appointment.appointmentid, customer.customerName, appointment.description, appointment.createdBy, appointment.start, appointment.end FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId WHERE appointmentid = '" + AppointmentsViewController.selectedAppointmentID + "'";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                appointmentIDField.setText(rs.getString("appointmentid"));
                customerField.setText(rs.getString("customerName"));
                typeField.setText(rs.getString("description"));
                consultantField.setText(rs.getString("createdBy"));
                startField.setText(rs.getString("start"));
                endField.setText(rs.getString("end"));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveButton.setOnAction((event) -> {
            PreparedStatement ps;
            try {
                String SQL = "UPDATE appointment "
                    + "SET description = '" + typeField.getText() + "', "
                    + "contact = '" + consultantField.getText() + "', "
                    + "start = '" + startField.getText() + "', "
                    + "end = '" + endField.getText() + "', "
                    + "lastUpdate = '" + DateAndTime.getTimestamp() + "', "
                    + "lastUpdateBy = '" + LoginScreenController.currentUser + "' "
                    + "WHERE appointmentid = " + appointmentIDField.getText() + ";";
                ps = con.prepareStatement(SQL);
                ps.executeUpdate();
                Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
                Scene appointmentsViewScene = new Scene(appointmentsViewParent);
                Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appointmentsViewStage.setScene(appointmentsViewScene);
                appointmentsViewStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }

}
