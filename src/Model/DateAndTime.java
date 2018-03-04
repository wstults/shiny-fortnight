/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

/**
 *
 * @author William
 */
public class DateAndTime {
    
    public static Timestamp getTimestamp()  {
    //Getting the LocalDateTime Objects from String values
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"); 
                String date = LocalDate.now().toString();
                String time = LocalTime.now().toString().substring(0, 8);
		String txtStartTime = date + " " + time;

		LocalDateTime ldtStart = LocalDateTime.parse(txtStartTime, df);


		
		ZoneId zid = ZoneId.systemDefault();

		ZonedDateTime zdtStart = ldtStart.atZone(zid);
		
		ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
		
		ldtStart = utcStart.toLocalDateTime();
		
		//Create Timestamp values from Instants to update database
		java.sql.Timestamp startsqlts = java.sql.Timestamp.valueOf(ldtStart); //this value can be inserted into database
                
                return startsqlts;
}
    
}
