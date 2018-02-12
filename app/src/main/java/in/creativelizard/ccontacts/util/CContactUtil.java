package in.creativelizard.ccontacts.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by siddhartha on 12/2/18.
 */

public class CContactUtil {
    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy, HH:mm:ss");
        return df.format(Calendar.getInstance().getTime());
    }
}
