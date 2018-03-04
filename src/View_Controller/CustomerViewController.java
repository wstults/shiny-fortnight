package View_Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CustomerViewController {

    @FXML
    private TableView<?> customersTable;

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
    void handleAddCustomer(ActionEvent event) throws IOException {
        Parent customerAddViewParent = FXMLLoader.load(getClass().getResource("CustomerAddView.fxml"));
        Scene customerAddViewScene = new Scene(customerAddViewParent);
        Stage customerAddViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customerAddViewStage.setScene(customerAddViewScene);
        customerAddViewStage.show();

    }

    @FXML
    void handleClose(ActionEvent event) {

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

}
