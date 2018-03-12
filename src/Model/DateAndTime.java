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
import java.util.function.Supplier;

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
		Timestamp startsqlts = Timestamp.valueOf(ldtStart); //this value can be inserted into database
                
                return startsqlts;
}
    
    public static LocalDateTime convertToDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        LocalDateTime result = LocalDateTime.parse(time, df);
        return result;
    }
    
    public static LocalDateTime timestampToDateTime(Timestamp time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        String converted = time.toString().substring(0, 19);
        LocalDateTime result = LocalDateTime.parse(converted, df);
        return result;
    }
    
    public static LocalDateTime localTime(LocalDateTime time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcTime = time.atZone(ZoneId.of("UTC"));
        System.out.println("Original time is " + utcTime);
        ZonedDateTime adjustedTime = utcTime.withZoneSameInstant(zid);
        System.out.println("Adjusted time is " + adjustedTime);
        LocalDateTime finalTime = adjustedTime.toLocalDateTime();
        System.out.println("Final Time is " + finalTime);
        return finalTime;
        
    }
    
    public static LocalDateTime utcTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        LocalDateTime ldtstart = convertToDateTime(time);
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime localTime = ldtstart.atZone(zid);
        ZonedDateTime utcTime = localTime.withZoneSameInstant(ZoneId.of("UTC"));
        
        LocalDateTime finalTime = utcTime.toLocalDateTime();
        return finalTime;
    }
    
}
