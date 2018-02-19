package View_Controller;

import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    void handleExit(ActionEvent event) {
        exit();

    }

    @FXML
    void handleLogin(ActionEvent event) {

    }

}
