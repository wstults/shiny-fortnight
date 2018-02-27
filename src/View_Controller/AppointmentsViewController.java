package View_Controller;

import java.io.IOException;
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

public class AppointmentsViewController {

    @FXML
    private TableView<?> appointmentsTable;

    @FXML
    private TableColumn<?, ?> apptIDColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> customerColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> startTimeColumn;

    @FXML
    private TableColumn<?, ?> endTimeColumn;

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

    @FXML
    void handleCustomers(ActionEvent event) {

    }

    @FXML
    void handleEdit(ActionEvent event) {

    }

    @FXML
    void handleExit(ActionEvent event) {

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
    void handleReports(ActionEvent event) {

    }

    @FXML
    void handleView(ActionEvent event) {

    }

    @FXML
    void handleWeek(ActionEvent event) {

    }

}
