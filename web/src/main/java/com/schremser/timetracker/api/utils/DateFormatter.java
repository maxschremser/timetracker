package com.schremser.timetracker.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bluemax on 21.06.15.
 */
public class DateFormatter {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static String parseToString(String date) {
        if (date.equals("today"))
            date = format.format(new Date());
        try {
            return format.format(format.parse(date));
        } catch (ParseException pe) {
            return "unknownDateFormat, use: 'yyyy-MM-dd' or 'today'";
        }
    }
}
