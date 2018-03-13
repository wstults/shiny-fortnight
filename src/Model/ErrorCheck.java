/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View_Controller.AppointmentsViewController;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.control.Alert;

/**
 *
 * @author William
 */

// Class created to house methods for the purpose of exception controls
public class ErrorCheck {
    
    public static String apptID;
    public static String startTime;
    public static String endTime;
    
    // Checks to make sure an appointment being saved falls between 8am and 5pm
    public static void officeHoursCheck(String time) throws BusinessHoursException {
        
        LocalTime open = LocalTime.of(8, 0);
        LocalTime close = LocalTime.of(17, 0);
        LocalDateTime convertedTime = DateAndTime.convertToDateTime(time);
        LocalTime hour = convertedTime.toLocalTime();
        if (hour.isBefore(open) || hour.isAfter(close)) {
            throw new BusinessHoursException();
        }
    }
    
    // Checks to make sure an appointment being saved does not overlap another previously scheduled appointment
    public static void overlapCheck(String start, String end) throws OverlapException {
        LocalDateTime convertedStart = DateAndTime.convertToDateTime(start);
        LocalDateTime convertedEnd = DateAndTime.convertToDateTime(end);
        for (Appointment a:AppointmentsViewController.data) {
            LocalDateTime extractedStart = DateAndTime.convertToDateTime(a.getStart().substring(0, 19));
            LocalDateTime extractedEnd = DateAndTime.convertToDateTime(a.getEnd().substring(0, 19));
            if ((convertedStart.isAfter(extractedStart) && convertedStart.isBefore(extractedEnd)) || (convertedEnd.isAfter(extractedStart) && convertedEnd.isBefore(extractedEnd))) {
                apptID = a.getAppointmentID();
                startTime = a.getStart();
                endTime = a.getEnd();
                throw new OverlapException();
            }
        }
    }
    
    // Checks to make sure form fields are not left blank
    public static boolean nullCheck(String input) {
        boolean result = true;
        if (input.isEmpty()) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error");
            alert.setContentText("Please input a value for all available fields.");
            alert.showAndWait();
        }
        return result;
    }
    
    // Checks to make sure a phone number contains no alpha characters
    public static boolean phoneCheck(String input) {
        boolean result = true;
        try {
            Long.valueOf(input);
        } catch (NumberFormatException e) {
            result = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error");
            alert.setContentText("Phone number can be numeric only.");
            alert.showAndWait();
        }
        return result;
    }
}
