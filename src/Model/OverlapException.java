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
public class OverlapException extends Exception {
    public OverlapException() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Error");
        alert.setContentText("The specified start and end times overlap with an existing appointment:\nAppointment ID " + ErrorCheck.apptID + ": " + ErrorCheck.startTime + " - " + ErrorCheck.endTime);
        alert.showAndWait();
    }
    
}
