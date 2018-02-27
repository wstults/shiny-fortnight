package View_Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenController {
    
    public String currentUser;
    static Stage stage;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;
    
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    void handleExit(ActionEvent event) {

    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
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
                String sql = "SELECT password FROM user WHERE userName = '" + usernameField.getText() + "'";
                
                stmt = conn.createStatement();
                
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    
                
                
                String password = rs.getString("password");
                
                if (password.equals(passwordField.getText())) {
                    
                    Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
                    Scene appointmentsViewScene = new Scene(appointmentsViewParent);
                    Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appointmentsViewStage.setScene(appointmentsViewScene);
                    appointmentsViewStage.show();
                } else {
                    
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
