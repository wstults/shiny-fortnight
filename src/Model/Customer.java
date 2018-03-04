/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author William
 */
public class Customer {
    
    private SimpleStringProperty customerid = new SimpleStringProperty();
    private SimpleStringProperty customerName = new SimpleStringProperty();
    private SimpleStringProperty addressId = new SimpleStringProperty();
    private SimpleStringProperty active = new SimpleStringProperty();
    private SimpleStringProperty createDate = new SimpleStringProperty();
    private SimpleStringProperty createdBy = new SimpleStringProperty();
    private SimpleStringProperty lastUpdate = new SimpleStringProperty();
    private SimpleStringProperty lastUpdateBy = new SimpleStringProperty();
    private static Database objDbClass;
    private static Connection con;
    public static ObservableList<Customer> data;
    
    public Customer() {
        
    }
    
    public String getCustomerID() {
        return customerid.get();
    }
    
    public String getCustomerName() {
        return customerName.get();
    }
    
    public String getAddressID() {
        return addressId.get();
    }
    
    public String getActive() {
        return active.get();
    }
    
    public String getCreateDate() {
        return createDate.get();
    }
    
    public String getCreatedBy() {
        return createdBy.get();
    }
    
    public String getLastUpdate() {
        return lastUpdate.get();
    }
    
    public String getLastUpdateBy() {
        return lastUpdateBy.get();
    }
    
    public static void buildData() throws SQLException, ClassNotFoundException {
        data = FXCollections.observableArrayList();
        objDbClass = new Database();
        con = objDbClass.getConnection();
        try {
            String SQL = "Select customerid, customerName from customer";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()) {
                Customer cu = new Customer();
                
                cu.customerid.set(rs.getString("customerid"));
                
                cu.customerName.set(rs.getString("customerName"));
                
                data.add(cu);
                
                
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}
