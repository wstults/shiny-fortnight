/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*;

/**
 *
 * @author William
 */

/**
 * DB Connection Info:
 * Server name:  52.206.157.109 
 * Database name:  U046Sh
 * Username:  U046Sh
 * Password:  53688167321
 */
public class Database {
    
    public static void main(String[] args) throws SQLException {
        
    
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://52.206.157.109/U046Sh";

        //  Database credentials
        final String DBUSER = "U046Sh";
        final String DBPASS = "53688167321";

        
    
    }
    
    public void insertCustomer(String customerName, int addressId, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) throws SQLException {
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
                ps.setString(1, customerName);
                ps.setInt(2, addressId);
                ps.setString(3, createDate);
                ps.setString(4, createdBy);
                ps.setString(5, lastUpdate);
                ps.setString(6, lastUpdateBy);
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
    
    /*public void updateCustomer();{
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://52.206.157.109/U046Sh";

        //  Database credentials
        final String DBUSER = "U046Sh";
        final String DBPASS = "53688167321";
        Connection conn;
    }
    
}*/
