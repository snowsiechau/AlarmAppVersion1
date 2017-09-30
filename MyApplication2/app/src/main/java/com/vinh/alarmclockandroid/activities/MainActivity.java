package com.vinh.alarmclockandroid.activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;


import com.vinh.alarmclockandroid.R;
import com.vinh.alarmclockandroid.activities.AlarmReceiver;
import com.vinh.alarmclockandroid.database_giobaothuc.DatabaseHandle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStart;

    TextView tvTimePicker;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;

    Intent intent;


    AudioManager audioManager;


    int vollume;

    Intent intentDatBaoThucThanhCong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DatabaseHandle.getInstance(this).insertTable();

        //Log.d("list", "onCreate: " + DatabaseHandle.getInstance(this).getListGioBaoThuc().size());

        loadUI();
        timePicker.setIs24HourView(true);

        calendar = Calendar.getInstance();

        //cho phep truy cap va he thong bao dong
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        intent = new Intent(MainActivity.this, AlarmReceiver.class);

        btnStart.setOnClickListener(this);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        intentDatBaoThucThanhCong = new Intent(getApplicationContext(), DatBaoThucThanhCong.class);




    }

    private void setAlarmDate() {
        int ngaycach = 1;

        int currentDay = Calendar.getInstance().getTime().getDay();



        AlarmHandle.setAlarm(timePicker.getCurrentHour(), timePicker.getCurrentMinute(), MainActivity.this, ngaycach);
        Log.d("abcd", "setAlarmDate: " + timePicker.getCurrentHour() + " : " + timePicker.getCurrentMinute());
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    }



    private void loadUI() {
        btnStart = (Button) findViewById(R.id.bt_start);
        tvTimePicker = (TextView) findViewById(R.id.tv_timePicker);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
    }

    MediaPlayer mediaPlayer;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_start:
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();

                String gio_sting = String.valueOf(gio);
                String phut_string = String.valueOf(phut);

                if (phut < 10){
                    phut_string = "0" + phut_string;
                }

                if (gio < 10){
                    gio_sting = "0" + gio_sting;
                }

                intent.putExtra("extra", "on");

                intent.putExtra("volume", vollume);

                //khac voi intent thuong la no se luon ton tai ke ca khi ung dung ket thuc


                tvTimePicker.setText(gio_sting + " : " + phut_string);

                setAlarmDate();

                startActivity(intentDatBaoThucThanhCong);

                break;

            default:
                break;
        }
    }
}
