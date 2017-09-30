package com.vinh.alarmclockandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.vinh.alarmclockandroid.R;

public class KhoiDong extends AppCompatActivity {

    //Button batdau_btn;
    ImageView batdau_iv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoidong_blue);

        intent = new Intent(getApplicationContext(), MainActivity.class);

        batdau_iv = (ImageView) findViewById(R.id.iv_setting_blue_icon);

        //batdau_btn = (Button) findViewById(R.id.batdau_btn);
        batdau_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
