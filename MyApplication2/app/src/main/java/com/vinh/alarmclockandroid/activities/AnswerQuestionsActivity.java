package com.vinh.alarmclockandroid.activities;

import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vinh.alarmclockandroid.R;
import com.vinh.alarmclockandroid.database.DatabaseHandle;
import com.vinh.alarmclockandroid.database.QuizModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AnswerQuestionsActivity extends AppCompatActivity implements View.OnClickListener {




    private static final String TAG = AnswerQuestionsActivity.class.toString();
    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i = 0;

    TextView noidungcauhoi_tv;
    RadioButton dapanA_rb, dapanB_rb, dapanC_rb, dapanD_rb;
    Button traloi_btn, boqua_btn;

    private QuizModel quizModel;

    List<QuizModel> quizList = new ArrayList<>();
    //ArrayList<Integer> arrayListID;
    public int soCauTraLoiDung = 0;

    public boolean kiemTra = true;

    CountDownTimer countDownBoQua;

    CountDownTimer countDownTraLoi;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions_fake);

        quizList = DatabaseHandle.getInstance(this).getListQuiz();

//


        startAniamtion();
        setupUI();
        loadData();
        startCountDownTimer();


        traloi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyCauTraLoi();
            }
        });

        boqua_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenCauHoi();
            }
        });


//        kiemTraSoCauTraLoiDung();
    }


    private void kiemTraSoCauTraLoiDung() {
        while (kiemTra) {
            if (soCauTraLoiDung == 3) {
                Toast.makeText(this, "Bạn đã trả lời được đủ 3 câu", Toast.LENGTH_SHORT).show();
                kiemTra = false;
            }
        }
    }

    private void startCountDownTimer() {

        boqua_btn.setEnabled(false);


        countDownBoQua = new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                boqua_btn.setText("Bỏ qua(10 giây)");
                int soGiayBoQua = (int) millisUntilFinished / 1000;
                boqua_btn.setText(String.format("Bỏ qua(%s giây)", soGiayBoQua));
            }

            @Override
            public void onFinish() {
                boqua_btn.setText(String.format("Bỏ qua"));
                boqua_btn.setEnabled(true);
            }
        };

        mProgressBar.setMax(30000);
        final int time = 30;
        countDownTraLoi = new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
//                int timeCount = (int) (300 - millisUntilFinished / (time * 10));
                Log.d(TAG, "onTick: " + millisUntilFinished);

                mProgressBar.setProgress((int) millisUntilFinished);
            }

            @Override
            public void onFinish() {
                chuyenCauHoi();
            }
        };

        countDownBoQua.start();
        countDownTraLoi.start();
    }

    private void loadData() {

        int topic = com.vinh.alarmclockandroid.database_giobaothuc.DatabaseHandle.getInstance(this).getListGioBaoThuc().get(0).getTopic();

        Random rand = new Random();
         int randomNumber = rand.nextInt(quizList.size() - 1) + 0;

        do {
            randomNumber = rand.nextInt(quizList.size() - 1) + 0;
        } while (quizList.get(randomNumber).getJob() != topic);

        quizModel = quizList.get(randomNumber - 1);

        Log.d(TAG, "loadData: " + quizModel.getQuestion());

        Log.d("topic", "topic: " + topic);

        noidungcauhoi_tv.setText(quizModel.getQuestion());

        dapanA_rb.setText(quizModel.getAnswera());
        dapanB_rb.setText(quizModel.getAnswerb());
        dapanC_rb.setText(quizModel.getAnswerc());
        dapanD_rb.setText(quizModel.getAnswerd());

    }

    private void setupUI() {
        noidungcauhoi_tv = (TextView) findViewById(R.id.noidungcauhoi_tv);

        dapanA_rb = (RadioButton) findViewById(R.id.dapanA_rb);
        dapanB_rb = (RadioButton) findViewById(R.id.dapanB_rb);
        dapanC_rb = (RadioButton) findViewById(R.id.dapanC_rb);
        dapanD_rb = (RadioButton) findViewById(R.id.dapanD_rb);

        traloi_btn = (Button) findViewById(R.id.traloi_btn);
        boqua_btn = (Button) findViewById(R.id.boqua_btn);


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    private void startAniamtion() {

//        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 100, 0);
//        progressAnimator.setDuration(30000);
//        progressAnimator.setInterpolator(new DecelerateInterpolator());
//        progressAnimator.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.traloi_btn:
                Log.d(TAG, "onClick: " + quizModel.getRealalswer());
                xulyCauTraLoi();
                break;
            case R.id.boqua_btn:
                chuyenCauHoi();
                break;
        }
    }

    private void xulyCauTraLoi() {
        int cauTraLoi = 0;

        if (dapanA_rb.isChecked()) {
            cauTraLoi = 1;
        } else if (dapanB_rb.isChecked()) {
            cauTraLoi = 2;
        } else if (dapanC_rb.isChecked()) {
            cauTraLoi = 3;
        } else if (dapanD_rb.isChecked()) {
            cauTraLoi = 4;
        }

        Log.d(TAG, "xulyCauTraLoi: " + quizModel.getRealalswer());

        if (cauTraLoi == quizModel.getRealalswer()) {
            Toast.makeText(this, "Bạn trả lời đúng rồi", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Bạn trả lời đúng rồi");
            chuyenCauHoi();
            soCauTraLoiDung++;

        } else {
            Toast.makeText(this, "Bạn trả lời sai rồi", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Bạn trả lời sai rồi");
            chuyenCauHoi();
            soCauTraLoiDung = 0;
        }
    }

    private void chuyenCauHoi() {
        AudioManager audioManager;
        audioManager = (AudioManager) getSystemService(MainActivity.AUDIO_SERVICE);
        audioManager.setStreamVolume(audioManager.STREAM_MUSIC, 15, AudioManager.FLAG_ALLOW_RINGER_MODES);

        Random rand = new Random();
        int n;
        int job = 1;

        do {
            n = rand.nextInt(15) + 0;
        } while (quizList.get(n).getJob() != job);

        countDownBoQua.cancel();
        countDownTraLoi.cancel();
        startCountDownTimer();
        startAniamtion();

//        noidungcauhoi_tv.setText(quizModel.getQuestion());
//
//        dapanA_rb.setText(quizModel.getAnswera());
//        dapanB_rb.setText(quizModel.getAnswerb());
//        dapanC_rb.setText(quizModel.getAnswerc());
//        dapanD_rb.setText(quizModel.getAnswerd());

        loadData();

        if (soCauTraLoiDung == 2) {
            Toast.makeText(this, "Bạn đã trả lời được đúng 3 câu", Toast.LENGTH_SHORT).show();
            stopService(new Intent(AnswerQuestionsActivity.this, BackgroundMusic.class));

        }
    }
}
