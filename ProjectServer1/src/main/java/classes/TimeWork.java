package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class TimeWork {
	public static int currentSem()
	{
		int semester;
		Calendar now = Calendar.getInstance();
		String d1 = "09-02";
        String d2 = "30-06";
 
        String pattern = "dd-MM";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
 
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        try {
            cal1.setTime(sdf.parse(d1));
            cal2.setTime(sdf.parse(d2));
            cal3.setTime(sdf.parse(sdf.format(now.getTime())));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        if (cal3.after(cal1)&& cal3.before(cal2)) 
            semester=2;
        else
            semester=1;
        return semester;
	}
	public static Date TimeNow()
	{
		Calendar now = Calendar.getInstance();
		String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try 
        {
        	now.setTime(sdf.parse(sdf.format(now.getTime())));
        }
        catch (ParseException e) {e.printStackTrace();}
        return now.getTime();
	}
	
}
