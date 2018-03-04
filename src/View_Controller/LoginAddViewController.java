package View_Controller;

import Model.DateAndTime;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginAddViewController {

    @FXML
    private Button registerButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    void handleClose(ActionEvent event) throws IOException {
        Parent appointmentsViewParent = FXMLLoader.load(getClass().getResource("AppointmentsView.fxml"));
        Scene appointmentsViewScene = new Scene(appointmentsViewParent);
        Stage appointmentsViewStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appointmentsViewStage.setScene(appointmentsViewScene);
        appointmentsViewStage.show();

    }

    @FXML
    void handleRegister(ActionEvent event) throws SQLException, ClassNotFoundException {
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
            PreparedStatement ps = null;
            try {

                Timestamp stamp = DateAndTime.getTimestamp();
                String sql = "INSERT INTO user (userName, password, active, createBy, createDate, lastUpdate, lastUpdatedBy) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, usernameField.getText());
                ps.setString(2, passwordField.getText());
                ps.setInt(3, 1);
                ps.setString(4, LoginScreenController.currentUser);
                ps.setTimestamp(5, stamp);
                ps.setTimestamp(6, stamp);
                ps.setString(7, LoginScreenController.currentUser);
                ps.executeUpdate();
                
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
