package View_Controller;

import Model.Appointment;
import Model.Database;
import Model.DateAndTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReportsViewController {

    private Database objDbClass;
    private Connection con;
    public static ObservableList<Appointment> typeData;
    public static ObservableList<Appointment> consultantData;
    public static ObservableList<Appointment> customerData;
    
    @FXML
    ObservableList<String> reportList = FXCollections.observableArrayList(
    "View Appointments by Type", "View Appointments by Consultant", "View Appointments by Customer");
    
    @FXML
    private ChoiceBox<String> reportBox;

    @FXML
    private Button generateButton;

    @FXML
    private Button closeButton;

    @FXML
    private TableView<Appointment> reportTable;

    @FXML
    private TableColumn<?, ?> customerColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> consultantColumn;

    @FXML
    private TableColumn<?, ?> startColumn;

    @FXML
    void handleClose(ActionEvent event) throws IOException {
        // Clicking Close returns the user to the Appointments View
        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsViewStage.setScene(appointmentsViewScene);
        appointmentsViewStage.show();
    }
    
    @FXML
    void handleGenerate(ActionEvent event) throws ClassNotFoundException, SQLException {
        // Get the type of report chosen from the choicebox
        String report = reportBox.getSelectionModel().getSelectedItem();
        // Decide which report to generate
        if (report.equals("View Appointments by Type")) {
            typeReport();
            reportTable.setItems(typeData);
        } 
        if (report.equals("View Appointments by Consultant")) {
            consultantReport();
            reportTable.setItems(consultantData);
        } 
        if (report.equals("View Appointments by Customer")) {
            customerReport();
            reportTable.setItems(customerData);
        }
    }
    
    @FXML
    private void initialize() {
        reportBox.setValue("View Appointments by Type");
        reportBox.setItems(reportList);
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        consultantColumn.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
    }
    
    private void typeReport() throws ClassNotFoundException, SQLException {
        // For generating an observable list organized by type
        typeData = FXCollections.observableArrayList();
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            // Retrive the data from the database and order appropriately
            String sql = "SELECT customer.customerName, appointment.title, appointment.description, appointment.contact, appointment.start FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId ORDER BY description, start";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()) {
                // Create Appointment objects from query results
                Appointment ap;
                ap = new Appointment();
                ap.setCustomerID(rs.getString("customerName"));
                ap.setTitle(rs.getString("title"));
                ap.setDescription(rs.getString("description"));
                ap.setContact(rs.getString("contact"));
                Timestamp startStamp = rs.getTimestamp("start");
                LocalDateTime utcTime = DateAndTime.timestampToDateTime(startStamp);
                LocalDateTime zonedTime = DateAndTime.localTime(utcTime);
                Timestamp finalTime = Timestamp.valueOf(zonedTime);
                ap.setStart(finalTime.toString().substring(0, 19));
                // Add the Appointment to the observable list
                typeData.add(ap);
        } 
    } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void consultantReport() throws ClassNotFoundException, SQLException {
        // For generating an observable list organized by consultant
        consultantData = FXCollections.observableArrayList();
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            // Retrive the data from the database and order appropriately
            String sql = "SELECT customer.customerName, appointment.title, appointment.description, appointment.contact, appointment.start FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId ORDER BY contact, start";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()) {
                // Create Appointment objects from query results
                Appointment ap;
                ap = new Appointment();
                ap.setCustomerID(rs.getString("customerName"));
                ap.setTitle(rs.getString("title"));
                ap.setDescription(rs.getString("description"));
                ap.setContact(rs.getString("contact"));
                Timestamp startStamp = rs.getTimestamp("start");
                LocalDateTime utcTime = DateAndTime.timestampToDateTime(startStamp);
                LocalDateTime zonedTime = DateAndTime.localTime(utcTime);
                Timestamp finalTime = Timestamp.valueOf(zonedTime);
                ap.setStart(finalTime.toString().substring(0, 19));
                // Add the Appointment to the observable list
                consultantData.add(ap);
        } 
    } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void customerReport() throws ClassNotFoundException, SQLException {
        // For generating an observable list organized by customer
        customerData = FXCollections.observableArrayList();
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            // Retrive the data from the database and order appropriately
            String sql = "SELECT customer.customerName, appointment.title, appointment.description, appointment.contact, appointment.start FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId ORDER BY customerName, start";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()) {
                // Create Appointment objects from query results
                Appointment ap;
                ap = new Appointment();
                ap.setCustomerID(rs.getString("customerName"));
                ap.setTitle(rs.getString("title"));
                ap.setDescription(rs.getString("description"));
                ap.setContact(rs.getString("contact"));
                Timestamp startStamp = rs.getTimestamp("start");
                LocalDateTime utcTime = DateAndTime.timestampToDateTime(startStamp);
                LocalDateTime zonedTime = DateAndTime.localTime(utcTime);
                Timestamp finalTime = Timestamp.valueOf(zonedTime);
                ap.setStart(finalTime.toString().substring(0, 19));
                // Add the Appointment to the observable list
                customerData.add(ap);
        } 
    } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
