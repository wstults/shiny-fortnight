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
public class Alerts {
    
    // Lambda style pop up alert when a new appointment is scheduled successfully
    public static Runnable newApptConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Appointment Scheduled!");
        alert.setContentText("The appointment has been scheduled.");
        alert.showAndWait();
    };
    
    // Lambda style pop up alert when an appointment is edited successfully
    public static Runnable editApptConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Appointment Edited!");
        alert.setContentText("The appointment has been edited successfully.");
        alert.showAndWait();
    };
    
    // Lambda style pop up alert when a new customer is added successfully
    public static Runnable newCustomerConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Customer Added!");
        alert.setContentText("The customer has been added to the database.");
        alert.showAndWait();
    };
    
    // Lambda style pop up alert when a customer is edited successfully
    public static Runnable editCustomerConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Customer Edited!");
        alert.setContentText("The customer has been edited in the database.");
        alert.showAndWait();
    };
    
    // Lambda style pop up alert when a user is added successfully
    public static Runnable newUserConfirmation = () -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("User added!");
        alert.setContentText("The user has been added to the database.");
        alert.showAndWait();
    };
}
