package com.vinh.alarmclockandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vinh.alarmclockandroid.R;

public class KhoiDong extends AppCompatActivity {

    Button batdau_btn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoi_dong);

        intent = new Intent(getApplicationContext(), MainActivity.class);
        batdau_btn = (Button) findViewById(R.id.batdau_btn);
        batdau_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
