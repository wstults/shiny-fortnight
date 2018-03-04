/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author William
 */
public class Appointment {
    
    private final SimpleStringProperty Appointmentid = new SimpleStringProperty("");
    private final SimpleStringProperty CustomerId = new SimpleStringProperty("");
    private final SimpleStringProperty Title = new SimpleStringProperty("");
    private final SimpleStringProperty Description = new SimpleStringProperty("");
    private final SimpleStringProperty Location = new SimpleStringProperty("");
    private final SimpleStringProperty Contact = new SimpleStringProperty("");
    private final SimpleStringProperty Start = new SimpleStringProperty("");
    private final SimpleStringProperty End = new SimpleStringProperty("");
    private final SimpleStringProperty CreateDate = new SimpleStringProperty("");
    private final SimpleStringProperty CreatedBy = new SimpleStringProperty("");
    private final SimpleStringProperty LastUpdate = new SimpleStringProperty("");
    private final SimpleStringProperty LastUpdateBy = new SimpleStringProperty("");
    public static ObservableList<Appointment> data;
    private static Database objDbClass;
    private static Connection con;
    
    public Appointment() {
        
    }
    
    public String getAppointmentID() {
        return Appointmentid.get();
    }
    
    public String getCustomerID() {
        return CustomerId.get();
    }
    
    public String getTitle() {
        return Title.get();
    }
    
    public String getDescription() {
        return Description.get();
    }
    
    public String getLocation() {
        return Location.get();
    }
    
    public String getContact() {
        return Contact.get();
    }
    
    public String getStart() {
        return Start.get();
    }
    
    public String getEnd() {
        return End.get();
    }
    
    public String getCreateDate() {
        return CreateDate.get();
    }
    
    public String getCreatedBy() {
        return CreatedBy.get();
    }
    
    public String getLastUpdate() {
        return LastUpdate.get();
    }
    
    public String getLastUpdateBy() {
        return LastUpdateBy.get();
    }
    
    public void setAppointmentID(String appointmentid){
        Appointmentid.set(appointmentid);
    }
    
    public void setCustomerID(String customerId){
        CustomerId.set(customerId);
    }
    
    public void setDescription(String description) {
        Description.set(description);
    }
    
    public void setStart(String start) {
        Start.set(start);
    }
    
    public void setEnd(String end) {
        End.set(end);
    }
    
    
    
    
    
    
}
