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

// Class designed for use when generating timestamps for database entries, and various date and time conversions
public class DateAndTime {
    
    // Generates a timestamp based on current local time, and converts to UTC
    public static Timestamp getTimestamp()  {
    
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
	Timestamp startsqlts = Timestamp.valueOf(ldtStart);
        return startsqlts;
}
    
    // Converts a specifically formatted string into a LocalDateTime object
    public static LocalDateTime convertToDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        LocalDateTime result = LocalDateTime.parse(time, df);
        return result;
    }
    
    // Converts a Timestamp object into a LocalDateTime object
    public static LocalDateTime timestampToDateTime(Timestamp time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        String converted = time.toString().substring(0, 19);
        LocalDateTime result = LocalDateTime.parse(converted, df);
        return result;
    }
    
    // Converts a UTC LocalDateTime into a LocalDateTime adjusted for the current time zone
    public static LocalDateTime localTime(LocalDateTime time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcTime = time.atZone(ZoneId.of("UTC"));
        ZonedDateTime adjustedTime = utcTime.withZoneSameInstant(zid);
        LocalDateTime finalTime = adjustedTime.toLocalDateTime();
        return finalTime;
    }
    
    // Converts a specifically formatted current time zone String into a LocalDateTime object in UTC
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
