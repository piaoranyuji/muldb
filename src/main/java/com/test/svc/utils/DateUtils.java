package com.test.svc.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    public static String now() {
        return now(DATE_FORMAT_LONG);
    }

    public static String now(String dateFormat) {
        final DateFormat DF = new SimpleDateFormat(dateFormat);
        Calendar cal = Calendar.getInstance();
        return DF.format(cal.getTime());
    }
}
