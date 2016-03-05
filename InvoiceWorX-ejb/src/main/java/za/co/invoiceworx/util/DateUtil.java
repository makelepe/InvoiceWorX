package za.co.invoiceworx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author F4657314
 */
public class DateUtil {

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    
    public static final Date newDate() {
        return new Date();
    }
    
    public static Date toDate (String sdate, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(sdate);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public static Integer calculateDifferenceInDays (Date from, Date to) {
        
        if (from == null || to == null) {
            return 0;
        }
        
        if (from.after(to)) {
            return 0;
        }
        return Long.valueOf((to.getTime() - from.getTime()) / (24 * 60 * 60 * 1000)).intValue();
    }
    
    public static int getNumberOfMonths (Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(from);
        
        Calendar toCal = Calendar.getInstance();
        fromCal.setTime(to);
        
        return 3;
    }
    
}
