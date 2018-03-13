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

// Custom exception class for use when checking if an appointment time falls outside of business hours
public class BusinessHoursException extends Exception {
    
    public BusinessHoursException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Error");
        alert.setContentText("Appointments may not be scheduled outside of local business hours.");
        alert.showAndWait();
    }
}
