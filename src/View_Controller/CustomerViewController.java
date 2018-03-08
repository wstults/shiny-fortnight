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
    Customer.buildData();
    customersTable.setItems(Customer.data);
    }
    

    @FXML
    void handleAddCustomer(ActionEvent event) throws IOException {
        Parent customerAddViewParent = FXMLLoader.load(getClass().getResource("CustomerAddView.fxml"));
        Scene customerAddViewScene = new Scene(customerAddViewParent);
        Stage customerAddViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerAddViewStage.setScene(customerAddViewScene);
        customerAddViewStage.show();

    }

    @FXML
    void handleClose(ActionEvent event) throws IOException {
        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsViewStage.setScene(appointmentsViewScene);
        appointmentsViewStage.show();

    }

    @FXML
    void handleEditCustomer(ActionEvent event) throws IOException {
        Parent customerEditViewParent = FXMLLoader.load(getClass().getResource("CustomerEditView.fxml"));
        Scene customerEditViewScene = new Scene(customerEditViewParent);
        Stage customerEditViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerEditViewStage.setScene(customerEditViewScene);
        customerEditViewStage.show();

    }

    @FXML
    void handleViewCustomer(ActionEvent event) throws IOException {
        Parent customerDetailViewParent = FXMLLoader.load(getClass().getResource("CustomerDetailView.fxml"));
        Scene customerDetailViewScene = new Scene(customerDetailViewParent);
        Stage customerDetailViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerDetailViewStage.setScene(customerDetailViewScene);
        customerDetailViewStage.show();

    }
    
    @FXML
    void handleSchedule(ActionEvent event) {

    }

}
