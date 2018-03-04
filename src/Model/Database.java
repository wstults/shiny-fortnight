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
    
    
        
    
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://52.206.157.109/U046Sh";

        //  Database credentials
        final String DBUSER = "U046Sh";
        final String DBPASS = "53688167321";
        
        public Connection getConnection() throws ClassNotFoundException, SQLException{       
           Class.forName(JDBC_DRIVER);
           return DriverManager.getConnection(DB_URL,DBUSER,DBPASS); 
     }

        
    
    
    
    
}
