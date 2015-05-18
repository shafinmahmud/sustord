/*
 */
package me.shafin.sustord.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author SHAFIN
 */
public class DateTime {

    private static final String LOCAL_TIME_ZONE = "GMT+6:00";

    public static String getSytemTimeStamp() {
        Date currentTime = new Date();
        DateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a"); 
        return timeStampFormat.format(currentTime);
    }

    public static String getLocalTimeStamp() {
        Date currentTime = new Date();
        DateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        timeStampFormat.setTimeZone(TimeZone.getTimeZone(LOCAL_TIME_ZONE));    
        return timeStampFormat.format(currentTime);
    }
}
