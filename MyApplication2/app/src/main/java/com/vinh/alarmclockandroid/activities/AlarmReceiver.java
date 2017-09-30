package com.vinh.alarmclockandroid.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by SNOW on 9/18/2017.
 */

public class AlarmReceiver extends BroadcastReceiver{


    //trao doi du lieu tu nhieu ung dung voi nhau
    // khi den gio hen tren he thong se bao ve ung dung
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentAlarmReceiver = new Intent(context, BackgroundMusic.class);

            context.startService(intentAlarmReceiver);

        int ngaycach = 1;

        AlarmHandle.setAlarm(Calendar.getInstance().getTime().getHours(), Calendar.getInstance().getTime().getMinutes(), context, ngaycach);

    }
}
