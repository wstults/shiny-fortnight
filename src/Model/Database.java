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
        
    
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://52.206.157.109/U046Sh",
            "U046Sh",
            "53688167321");
    
    }
}
