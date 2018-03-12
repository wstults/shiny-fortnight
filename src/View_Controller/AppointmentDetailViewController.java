package View_Controller;

import Model.Database;
import Model.DateAndTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppointmentDetailViewController {
    
    private Database objDbClass;
    private Connection con;

    @FXML
    private TextField appointmentIDField;

    @FXML
    private TextField customerField;
    
    @FXML
    private TextField titleField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField consultantField;
    
    @FXML
    private TextField locationField;

    @FXML
    private TextField startField;

    @FXML
    private TextField endField;

    @FXML
    private Button closeButton;

    @FXML
    void handleClose(ActionEvent event) throws IOException {
        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsViewStage.setScene(appointmentsViewScene);
        appointmentsViewStage.show();

    }
    
    public void initialize() throws ClassNotFoundException, SQLException {
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            String SQL = "SELECT appointment.appointmentid, customer.customerName, appointment.description, appointment.createdBy, appointment.start, appointment.end, appointment.title, appointment.location FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId WHERE appointmentid = '" + AppointmentsViewController.selectedAppointmentID + "'";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                Timestamp startStamp = rs.getTimestamp("start");
                LocalDateTime utcTime = DateAndTime.timestampToDateTime(startStamp);
                LocalDateTime zonedTime = DateAndTime.localTime(utcTime);
                Timestamp finalTime = Timestamp.valueOf(zonedTime);
                startField.setText(finalTime.toString().substring(0, 19));
                Timestamp endStamp = rs.getTimestamp("end");
                LocalDateTime utcTime2 = DateAndTime.timestampToDateTime(endStamp);
                LocalDateTime zonedTime2 = DateAndTime.localTime(utcTime2);
                Timestamp finalTime2 = Timestamp.valueOf(zonedTime2);
                endField.setText(finalTime2.toString().substring(0, 19));
                appointmentIDField.setText(rs.getString("appointmentid"));
                customerField.setText(rs.getString("customerName"));
                titleField.setText(rs.getString("title"));
                typeField.setText(rs.getString("description"));
                consultantField.setText(rs.getString("createdBy"));
                locationField.setText(rs.getString("location"));
                //startField.setText(rs.getString("start").substring(0, 19));
                //endField.setText(rs.getString("end").substring(0, 19));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
