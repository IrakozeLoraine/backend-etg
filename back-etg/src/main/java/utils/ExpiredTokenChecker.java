package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ExpiredTokenChecker {
    public boolean hasExpired(Date date) {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        try {
            Date toDate = dateFormat.parse(today.toString());
            Date fromDate = dateFormat.parse(date.toString());
            long diff = toDate.getTime() - fromDate.getTime();

            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <= 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
