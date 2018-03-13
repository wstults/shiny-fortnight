package View_Controller;

import Model.Customer;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerViewController {
    
    public static String selectedCustomerID;

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<?, ?> customerIDColumn;

    @FXML
    private TableColumn<?, ?> customerNameColumn;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button viewCustomerButton;

    @FXML
    private Button editCustomerButton;
    
    @FXML
    private Button scheduleButton;
    
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        // Create an observable list to display in the customers tableview
        Customer.buildData();
        customersTable.setItems(Customer.data);
    }
    
    @FXML
    void handleAddCustomer(ActionEvent event) throws IOException {
        // Clicking Add Customer brings the user to the Customer Add View
        Parent customerAddViewParent = FXMLLoader.load(getClass().getResource("CustomerAddView.fxml"));
        Scene customerAddViewScene = new Scene(customerAddViewParent);
        Stage customerAddViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerAddViewStage.setScene(customerAddViewScene);
        customerAddViewStage.show();
    }

    @FXML
    void handleClose(ActionEvent event) throws IOException {
        // Clicking close returns the user to the Appointments View
        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsViewStage.setScene(appointmentsViewScene);
        appointmentsViewStage.show();
    }

    @FXML
    void handleEditCustomer(ActionEvent event) throws IOException {
        // Clicking Edit Customer brings the user to the Customer Edit View, capturing the selected customer
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        selectedCustomerID = selectedCustomer.getCustomerID();
        Parent customerEditViewParent = FXMLLoader.load(getClass().getResource("CustomerEditView.fxml"));
        Scene customerEditViewScene = new Scene(customerEditViewParent);
        Stage customerEditViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerEditViewStage.setScene(customerEditViewScene);
        customerEditViewStage.show();
    }

    @FXML
    void handleViewCustomer(ActionEvent event) throws IOException {
        // Clicking View Customer Details brings the user to the Customer Detail View, capturing the selected customer
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        selectedCustomerID = selectedCustomer.getCustomerID();
        Parent customerDetailViewParent = FXMLLoader.load(getClass().getResource("CustomerDetailView.fxml"));
        Scene customerDetailViewScene = new Scene(customerDetailViewParent);
        Stage customerDetailViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerDetailViewStage.setScene(customerDetailViewScene);
        customerDetailViewStage.show();
    }
    
    @FXML
    void handleSchedule(ActionEvent event) throws IOException {
        // Clicking Schedule Appointment brings the user to the Appointment Add View, capturing the selected customer
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        selectedCustomerID = selectedCustomer.getCustomerID();
        Parent appointmentAddViewParent = FXMLLoader.load(getClass().getResource("AppointmentAddView.fxml"));
        Scene appointmentAddViewScene = new Scene(appointmentAddViewParent);
        Stage appointmentAddViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentAddViewStage.setScene(appointmentAddViewScene);
        appointmentAddViewStage.show();
    }
}
