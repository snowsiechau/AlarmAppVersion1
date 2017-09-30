package com.vinh.alarmclockandroid.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 9/26/2017.
 */

public class AlarmHandle {

    public static void setAlarm(int hour, int minute, Context context, int ngayCach)
    {
        Calendar calendar = Calendar.getInstance();

        AlarmManager objAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Intent alarmShowIntent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, alarmShowIntent , PendingIntent.FLAG_UPDATE_CURRENT
        );

        if (soSanhThoiGian(hour, minute))
        {
            objAlarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
        else
        {
            objAlarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + ngayCach * 24*60*60*1000, pendingIntent);
        }
    }

    private static boolean soSanhThoiGian(int gio, int phut) {
        Date currentTime = Calendar.getInstance().getTime();
        if (currentTime.getHours() < gio)
        {
            return true;
        }
        else if (currentTime.getHours() == gio)
        {
            if (currentTime.getMinutes() < phut)
            {
                return true;
            }
        }

        return false;


    }
}
