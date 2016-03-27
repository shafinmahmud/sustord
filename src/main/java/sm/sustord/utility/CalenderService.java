/*
 */
package sm.sustord.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author SHAFIN
 */
public class CalenderService {

    private final DateFormat dateFormat;

    public CalenderService() {

        //Calendar's getInstance method returns 
        //a Calendar object whose calendar fields 
        //have been initialized with the current date and time
        this.dateFormat = new SimpleDateFormat("dd MMM YYYY");
    }

    public String getDateLabel(int iter) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, iter);
        
        String date = dateFormat.format(calendar.getTime());

        return date.toUpperCase();
    }

    public String getDayLabel(int iter) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, iter);
        //get(int field) Returns the int value of 
        //the given calendar field eg. DAY_OF_MONTH, DAY_OF_WEEK
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        
        String dayLabel;
        String[] dayName = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY",
            "THURSDAY", "FRIDAY", "SATURDAY"};

        dayLabel = dayName[day-1];

        return dayLabel;
    }
}
