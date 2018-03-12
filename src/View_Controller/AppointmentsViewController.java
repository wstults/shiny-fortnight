package View_Controller;

import Model.Appointment;
import Model.Database;
import Model.DateAndTime;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static javafx.application.Platform.exit;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppointmentsViewController {
    
    private Database objDbClass;
    private Connection con;
    public static ObservableList<Appointment> data;
    public static String selectedAppointmentID;
    //private MainApp mainApp;

    @FXML
    private TableView<Appointment> appointmentsTable;

    @FXML
    private TableColumn<Appointment, String> apptIDColumn;

    @FXML
    private TableColumn<Appointment, String> customerColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, String> startTimeColumn;

    @FXML
    private TableColumn<Appointment, String> endTimeColumn;

    @FXML
    private RadioButton weekRadio;

    @FXML
    private RadioButton monthRadio;

    @FXML
    private Button customersButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button editAppointmentButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button viewAppointmentButton;

    @FXML
    private Button registerButton;
    
    public void buildData() throws SQLException, ClassNotFoundException {
        data = FXCollections.observableArrayList();
        objDbClass = new Database();
        con = objDbClass.getConnection();
        
        try {
            LocalDate now = LocalDate.now();
            LocalDate nowPlus7 = now.plusDays(7);
            String sql = "SELECT appointment.appointmentid, customer.customerName, appointment.description, appointment.start, appointment.end FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId WHERE start BETWEEN '" 
                    + now.toString() + "' and '" + nowPlus7.toString() + "' ORDER BY start";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()) {
                Appointment ap;
                ap = new Appointment();
                ap.setAppointmentID(rs.getString("appointmentid"));
                
                ap.setCustomerID(rs.getString("customerName"));
                ap.setDescription(rs.getString("description"));
                //ap.setStart(rs.getString("start").substring(0, 19));
                Timestamp startStamp = rs.getTimestamp("start");
                LocalDateTime utcTime = DateAndTime.timestampToDateTime(startStamp);
                LocalDateTime zonedTime = DateAndTime.localTime(utcTime);
                Timestamp finalTime = Timestamp.valueOf(zonedTime);
                ap.setStart(finalTime.toString().substring(0, 19));
                //ap.setEnd(rs.getString("end").substring(0, 19));
                Timestamp endStamp = rs.getTimestamp("end");
                LocalDateTime utcTime2 = DateAndTime.timestampToDateTime(endStamp);
                LocalDateTime zonedTime2 = DateAndTime.localTime(utcTime2);
                Timestamp finalTime2 = Timestamp.valueOf(zonedTime2);
                ap.setEnd(finalTime2.toString().substring(0, 19));
                data.add(ap);
                
                
            }
            appointmentsTable.setItems(data);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void buildDataMonth() throws SQLException, ClassNotFoundException {
        data = FXCollections.observableArrayList();
        objDbClass = new Database();
        con = objDbClass.getConnection();
        
        try {
            LocalDate now = LocalDate.now();
            LocalDate nowPlus30 = now.plusDays(30);
            String sql = "SELECT appointment.appointmentid, customer.customerName, appointment.description, appointment.start, appointment.end FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId WHERE start BETWEEN '" 
                    + now.toString() + "' and '" + nowPlus30.toString() + "' ORDER BY start";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()) {
                Appointment ap;
                ap = new Appointment();
                ap.setAppointmentID(rs.getString("appointmentid"));
                
                ap.setCustomerID(rs.getString("customerName"));
                ap.setDescription(rs.getString("description"));
                //ap.setStart(rs.getString("start").substring(0, 19));
                Timestamp startStamp = rs.getTimestamp("start");
                LocalDateTime utcTime = DateAndTime.timestampToDateTime(startStamp);
                LocalDateTime zonedTime = DateAndTime.localTime(utcTime);
                Timestamp finalTime = Timestamp.valueOf(zonedTime);
                ap.setStart(finalTime.toString().substring(0, 19));
                //ap.setEnd(rs.getString("end").substring(0, 19));
                Timestamp endStamp = rs.getTimestamp("end");
                LocalDateTime utcTime2 = DateAndTime.timestampToDateTime(endStamp);
                LocalDateTime zonedTime2 = DateAndTime.localTime(utcTime2);
                Timestamp finalTime2 = Timestamp.valueOf(zonedTime2);
                ap.setEnd(finalTime2.toString().substring(0, 19));
                data.add(ap);
                
                
            }
            appointmentsTable.setItems(data);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void reminder() {
        LocalDateTime now = LocalDateTime.now();
        
        for (Appointment a:data) {
            LocalDateTime extractedStart = DateAndTime.convertToDateTime(a.getStart().substring(0, 19));
            LocalDateTime extractedStartMinus15 = extractedStart.minusMinutes(15);
            if (now.isAfter(extractedStartMinus15) && now.isBefore(extractedStart)) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reminder");
                alert.setHeaderText("Appointment Soon");
                alert.setContentText("There is an appointment soon:\nAppointment ID - " + a.getAppointmentID() + "\nStart Time - " + a.getStart() + "\nEnd Time - " + a.getEnd());
                alert.showAndWait();
            }
        }
    }
    
    
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        apptIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        buildData();
        reminder();
        
    }
    
    

    @FXML
    void handleCustomers(ActionEvent event) throws IOException {
        Parent customerViewParent = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
        Scene customerViewScene = new Scene(customerViewParent);
        Stage customerViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerViewStage.setScene(customerViewScene);
        customerViewStage.show();

    }

    @FXML
    void handleEdit(ActionEvent event) throws IOException {
        
        Appointment selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        selectedAppointmentID = selectedAppointment.getAppointmentID();
        
        
        Parent appointmentEditViewParent = FXMLLoader.load(getClass().getResource("AppointmentEditView.fxml"));
        Scene appointmentEditViewScene = new Scene(appointmentEditViewParent);
        Stage appointmentEditViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentEditViewStage.setScene(appointmentEditViewScene);
        appointmentEditViewStage.show();

    }

    @FXML
    void handleExit(ActionEvent event) {
        exit();

    }

    @FXML
    void handleMonth(ActionEvent event) throws SQLException, ClassNotFoundException {
        buildDataMonth();
    }

    @FXML
    void handleRegister(ActionEvent event) throws IOException {
        Parent loginAddViewParent = FXMLLoader.load(getClass().getResource("LoginAddView.fxml"));
        Scene loginAddViewScene = new Scene(loginAddViewParent);
        Stage loginAddViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginAddViewStage.setScene(loginAddViewScene);
        loginAddViewStage.show();

    }

    @FXML
    void handleReports(ActionEvent event) throws IOException {
        Parent reportsViewParent = FXMLLoader.load(getClass().getResource("ReportsView.fxml"));
        Scene reportsViewScene = new Scene(reportsViewParent);
        Stage reportsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        reportsViewStage.setScene(reportsViewScene);
        reportsViewStage.show();

    }

    @FXML
    void handleView(ActionEvent event) throws IOException {
    
            Appointment selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
            selectedAppointmentID = selectedAppointment.getAppointmentID();
        
        Parent appointementDetailViewParent = FXMLLoader.load(getClass().getResource("AppointmentDetailView.fxml"));
        Scene appointementDetailViewScene = new Scene(appointementDetailViewParent);
        Stage appointementDetailViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointementDetailViewStage.setScene(appointementDetailViewScene);
        appointementDetailViewStage.show();

    }

    @FXML
    void handleWeek(ActionEvent event) throws SQLException, ClassNotFoundException {
        buildData();
    }
    
    

}
