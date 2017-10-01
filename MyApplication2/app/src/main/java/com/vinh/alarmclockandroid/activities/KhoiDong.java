package com.vinh.alarmclockandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;
import com.vinh.alarmclockandroid.R;

public class KhoiDong extends AppCompatActivity {

    ImageView ivSwitchOn;
    RelativeLayout layoutAlarmInfo;

    //Button batdau_btn;
    ImageView batdau_iv;
    Intent intent;

    boolean isOnline = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoidong_blue);

        intent = new Intent(getApplicationContext(), MainActivity.class);

        batdau_iv = (ImageView) findViewById(R.id.iv_setting_blue_icon);
        //switch on
        ivSwitchOn = (ImageView) findViewById(R.id.iv_switch_on);
        layoutAlarmInfo = (RelativeLayout) findViewById(R.id.layout_alarm_information);

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

                if(isOnline){
                    ivSwitchOn.setImageResource(R.drawable.icon_switch_on);
                    layoutAlarmInfo.setVisibility(View.VISIBLE);
                    isOnline = false;
                }else{
                    ivSwitchOn.setImageResource(R.drawable.icon_switch_off);
                    layoutAlarmInfo.setVisibility(View.INVISIBLE);
                    isOnline = true;
                }

            }
        });

    }


}
