package com.frontkom.androidchallenge.Helpers;

import android.os.SystemClock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cesar Ferreira on 10/02/2018.
 * Class that converts time ina certain format to an uniform time format or time elapsed
 */

public class TimeConverter {


    public static String convertTimeDate(String time)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = format.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date != null ? date.toLocaleString() : time;
    }

    public static long convertTimeMilliseconds(String time)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        long timeInMilliseconds = 0;
        try {
            Date mDate = sdf.parse(time);
            timeInMilliseconds = mDate.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeInMilliseconds;
    }
    public static String getElapsedTimeMinutesSecondsString(String time) {
        long ocurrence_time = TimeConverter.convertTimeMilliseconds(time);
        long current_time = SystemClock.currentThreadTimeMillis();
        long calculated_time =   Calendar.getInstance().getTime().getTime() - ocurrence_time ;



        long minutes = TimeUnit.MILLISECONDS.toMinutes(calculated_time);

        if (minutes < 60) {
            return minutes +" minute(s)" + " ago"  ;
        }
        long hours = TimeUnit.MILLISECONDS.toHours(calculated_time);

        if (hours < 24) {
            return hours +" hour(s)" + " ago"  ;
        }

        long days = TimeUnit.MILLISECONDS.toDays(calculated_time);

        return days +" day(s)" + " ago"  ;

    }

}
