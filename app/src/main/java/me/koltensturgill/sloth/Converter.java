package me.koltensturgill.sloth;

import android.arch.persistence.room.TypeConverter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Converter
{

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);

    @TypeConverter
    public static Date stringToDate(String date)
    {
        if (date == null)
        {
            return null;
        }
        else
        {

            try
            {
                return simpleDateFormat.parse(date);
            }

            catch (ParseException e)
            {
                return new Date();
            }
        }
    }

    @TypeConverter
    public static String dateToString(Date date)
    {
        if (date == null)
        {
            return null;
        }
        else
        {
            return date.toString();
        }
    }
}