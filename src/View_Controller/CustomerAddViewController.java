package View_Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CustomerAddViewController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressOneField;

    @FXML
    private TextField addressTwoField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField zipCodeField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button saveButton;

    @FXML
    private Button closeButton;

    @FXML
    void handleClose(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {
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
                String sql = "INSERT INTO customer (customerName, addressId, createDate, createdBy, lastUpdate, lastUpdateBy) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                
                ps = conn.prepareStatement(sql);
                ps.setString(1, nameField.getText());
                ps.setInt(2, 1);
                ps.setString(3, "today");
                ps.setString(4, "bill");
                ps.setString(5, "today");
                ps.setString(6, "bill");
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
