/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Locale;
import javafx.scene.control.Alert;

/**
 *
 * @author William
 */

// Custom exception class for use when checking a username / password combination is correct.  Supports French language.
public class LoginException extends Exception {
    public LoginException() {
        if (Locale.getDefault().equals(Locale.FRANCE)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText("Erreur");
            alert.setContentText("Le nom d'utilisateur ou le mot de passe sont incorrects ou ne correspondent pas.");
            alert.showAndWait();
        } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Error");
        alert.setContentText("The username or password are incorrect or do not match.");
        alert.showAndWait();
        }
    }
}
