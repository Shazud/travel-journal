package com.ddp.tj.database;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class CalendarConverter {
    @TypeConverter
    public static Calendar toCalendar(Long timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(timestamp));
            return calendar;
        }
    }

    @TypeConverter
    public static Long toTimestamp(Calendar calendar){
        return calendar == null ? null : calendar.getTime().getTime();
    }

}

