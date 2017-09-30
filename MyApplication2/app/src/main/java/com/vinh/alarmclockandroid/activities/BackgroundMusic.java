package com.vinh.alarmclockandroid.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.vinh.alarmclockandroid.R;

/**
 * Created by Admin on 9/23/2017.
 */

public class BackgroundMusic extends Service{

    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotifications();

        mediaPlayer = MediaPlayer.create(this, R.raw.chucbengungon);
        mediaPlayer.setLooping(true);
//        mediaPlayer.setVolume(100, 200);

        AudioManager audioManager;
        audioManager = (AudioManager) getSystemService(MainActivity.AUDIO_SERVICE);
        audioManager.setStreamVolume(audioManager.STREAM_MUSIC, 15, AudioManager.FLAG_ALLOW_RINGER_MODES);

        Intent intentAnswerQuestionS = new Intent(getApplicationContext(), KeuBaoThuc.class);
        intentAnswerQuestionS.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentAnswerQuestionS);

    }

    private void showNotifications() {
        Intent intent = new Intent(getApplicationContext(), AnswerQuestionsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(this);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setTicker("Trả lời câu hỏi để emeeii")
                .setContentTitle("Bấm để tắt báo thức")
                .setContentText("Trả lời đúng 3 câu mới tắt được")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return 1;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
