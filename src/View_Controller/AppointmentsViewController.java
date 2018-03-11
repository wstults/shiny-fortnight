package View_Controller;

import Model.Appointment;
import Model.Database;
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
import static javafx.application.Platform.exit;
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
            String SQL = "SELECT appointment.appointmentid, customer.customerName, appointment.description, appointment.start, appointment.end FROM appointment INNER JOIN customer on customer.customerid = appointment.customerId ORDER BY start";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                Appointment ap;
                ap = new Appointment();
                ap.setAppointmentID(rs.getString("appointmentid"));
                
                ap.setCustomerID(rs.getString("customerName"));
                ap.setDescription(rs.getString("description"));
                ap.setStart(rs.getString("start"));
                ap.setEnd(rs.getString("end"));
                data.add(ap);
                
                
            }
            appointmentsTable.setItems(data);
        } catch(Exception e) {
            e.printStackTrace();
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
    void handleMonth(ActionEvent event) {

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
    void handleWeek(ActionEvent event) {

    }
    
    //public void setMainApp(MainApp mainApp) {
    //    this.mainApp = mainApp;
    //    appointmentsTable.setItems(data);
        
    //}

}
