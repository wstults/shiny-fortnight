/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author William
 */
public class Alerts {
    
           
    
    public static Runnable newApptConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Appointment Scheduled!");
        alert.setContentText("The appointment has been scheduled.");
        alert.showAndWait();
      
         
    };
    
    public static Runnable editApptConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Appointment Edited!");
        alert.setContentText("The appointment has been edited successfully.");
        alert.showAndWait();
      
         
    };
    
    public static Runnable newCustomerConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Customer Added!");
        alert.setContentText("The customer has been added to the database.");
        alert.showAndWait();
      
         
    };
    
    public static Runnable editCustomerConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Customer Edited!");
        alert.setContentText("The customer has been edited in the database.");
        alert.showAndWait();
      
         
    };
}
