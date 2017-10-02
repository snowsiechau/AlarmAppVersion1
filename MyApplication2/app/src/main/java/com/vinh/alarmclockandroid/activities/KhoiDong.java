package com.vinh.alarmclockandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;
import com.vinh.alarmclockandroid.R;
import com.vinh.alarmclockandroid.database_giobaothuc.DatabaseHandle;
import com.vinh.alarmclockandroid.database_giobaothuc.GioBaoThucModel;

import java.util.ArrayList;
import java.util.List;

public class KhoiDong extends AppCompatActivity {

    ImageView ivSwitchOn;
    RelativeLayout layoutAlarmInfo;

    private GioBaoThucModel quizModel;

    List<GioBaoThucModel> quizList = new ArrayList<>();

    //Button batdau_btn;
    ImageView batdau_iv, btn_xoa;
    Intent intent;
    Intent intentGuide;
    TextView tvAlarmTime;
    TextView tvTopic;

    ImageView buttonGuide;

    boolean isOnline = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoidong_blue);

        quizList = DatabaseHandle.getInstance(this).getListGioBaoThuc();

        intent = new Intent(getApplicationContext(), MainActivity.class);
        intentGuide = new Intent(getApplicationContext(), GuideActivity.class);

        batdau_iv = (ImageView) findViewById(R.id.iv_setting_blue_icon);
        //switch on
        ivSwitchOn = (ImageView) findViewById(R.id.iv_switch_on);
        layoutAlarmInfo = (RelativeLayout) findViewById(R.id.layout_alarm_information);
        btn_xoa = (ImageView) findViewById(R.id.btn_xoa);

        //custom analog
        CustomAnalogClock customAnalogClock = (CustomAnalogClock) findViewById(R.id.analog_clock);
        customAnalogClock.setAutoUpdate(true);

        //batdau_btn = (Button) findViewById(R.id.batdau_btn);
        batdau_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        ivSwitchOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // layoutAlarmInfo.setVisibility(View.INVISIBLE);

                if (isOnline) {
                    ivSwitchOn.setImageResource(R.drawable.icon_switch_on);
                    layoutAlarmInfo.setVisibility(View.VISIBLE);
                    quizList = DatabaseHandle.getInstance(getApplicationContext()).getListGioBaoThuc();
                    isOnline = false;
                    if (quizList.size() != 0) {
                        AlarmHandle.setAlarm(quizList.get(0).getHour(), quizList.get(0).getMinute(), getApplicationContext(), 1);
                    }
                } else {
                    ivSwitchOn.setImageResource(R.drawable.icon_switch_off);
                    layoutAlarmInfo.setVisibility(View.INVISIBLE);
                    isOnline = true;

                    if (AlarmHandle.pendingIntent != null) {
                        AlarmHandle.pendingIntent.cancel();
                    }
                }

            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AlarmHandle.pendingIntent != null) {
                    AlarmHandle.pendingIntent.cancel();
                }
                DatabaseHandle.getInstance(getApplicationContext()).deleteTable();
                tvAlarmTime.setText("No Alarm !");
                tvAlarmTime.setTextSize(36);
                tvTopic.setText("");
            }
        });

        tvAlarmTime = (TextView) findViewById(R.id.tv_alarmtime);
        tvTopic = (TextView) findViewById(R.id.tv_topic);
        buttonGuide = (ImageView) findViewById(R.id.bt_guide);
        buttonGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentGuide);
            }
        });

//
//        String gio = String.valueOf(quizList.get(0).getHour());
//        String phut = String.valueOf(quizList.get(0).getMinute());
//
//        if (quizList.get(0).getHour() < 10){
//            gio = "0" + gio;
//        }
//
//        if (quizList.get(0).getMinute() < 10){
//            phut = "0" + phut;
//        }
//
//        if (quizList.size() != 0) {
//            tvAlarmTime.setText(gio + " : " + phut);
//        }


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        quizList = DatabaseHandle.getInstance(this).getListGioBaoThuc();
        String gio = "";
        String phut = "";
        if (quizList.size() != 0) {
            gio = String.valueOf(quizList.get(0).getHour());
            phut = String.valueOf(quizList.get(0).getMinute());

            if (quizList.get(0).getHour() < 10) {
                gio = "0" + gio;
            }

            if (quizList.get(0).getMinute() < 10) {
                phut = "0" + phut;
            }
        }

        if (quizList.size() != 0) {
            tvAlarmTime.setText(gio + " : " + phut);
            String topic;

            if (quizList.get(0).getTopic() == 1) {
                topic = "Java";
            } else if (quizList.get(0).getTopic() == 2) {
                topic = "Đố vui";
            } else {
                topic = "IQ";
            }
            tvTopic.setText(topic);
        }
    }
}