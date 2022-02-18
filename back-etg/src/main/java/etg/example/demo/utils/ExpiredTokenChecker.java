package etg.example.demo.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ExpiredTokenChecker {
    public boolean hasExpired(Date fromDate) {
        Date toDate = new Date();
        long diff = toDate.getTime() - fromDate.getTime();

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <= 0;
    }
}
