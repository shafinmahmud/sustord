/*
 */
package me.shafin.sustord.utility;

import java.sql.Timestamp;

/**
 *
 * @author SHAFIN
 */
public class DateTime {
    
    public static String getSytemTimeStamp(){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time.toString();
    }
    
    public static String getLocalTimeStamp(double timeZone){
        return "";
    }
}
