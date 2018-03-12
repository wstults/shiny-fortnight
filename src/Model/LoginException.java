/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Alert;

/**
 *
 * @author William
 */
public class LoginException extends Exception {
    public LoginException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Error");
        alert.setContentText("The username or password are incorrect or do not match.");
        alert.showAndWait();
    }
    
}
