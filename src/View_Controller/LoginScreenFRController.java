package View_Controller;

import Model.Logging;
import Model.LoginException;
import static View_Controller.LoginScreenController.currentUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenFRController {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button exitButton;

    @FXML
    void handleExit(ActionEvent event) {
        // Clicking Exit ends the application
        exit();
    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException, LoginException {
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://52.206.157.109/U046Sh";
        //  Database credentials
        final String DBUSER = "U046Sh";
        final String DBPASS = "53688167321";
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DBUSER, DBPASS);
            ResultSet rs = null;
            Statement stmt = null;
            try {
                // Check to see if the user exists in the database
                String sql = "SELECT * FROM user WHERE userName = '" + usernameField.getText() + "'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next() == false) {
                    throw new LoginException();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                // Retrieve the password for the user attempting to log in
                String sql = "SELECT password FROM user WHERE userName = '" + usernameField.getText() + "'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String password = rs.getString("password");
                    // Check to see if the password matches
                    if (password.equals(passwordField.getText())) {
                        // Assign the user logging in to a variable
                        currentUser = usernameField.getText();
                        try {
                            // Record the login in the log
                            Logging mylog = new Logging("log.txt");
                            mylog.logger.setLevel(Level.INFO);
                            mylog.logger.info(currentUser + " logged in.");
                        } catch (SecurityException | IOException ex) {
                            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        // Display the Appointments View
                        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
                        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
                        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appointmentsViewStage.setScene(appointmentsViewScene);
                        appointmentsViewStage.show();
                    } else {
                        throw new LoginException();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
