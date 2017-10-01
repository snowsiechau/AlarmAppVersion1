package com.vinh.alarmclockandroid.database_giobaothuc;

import java.io.Serializable;

/**
 * Created by Admin on 9/5/2017.
 */

public class GioBaoThucModel implements Serializable {

    private int hour;
    private int minute;
    private int status;



    private int topic;


    @Override
    public String toString() {
        return "GioBaoThucModel{" +
                ", hour=" + hour +
                ", minute=" + minute +
                ", status=" + status +
                '}';
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public GioBaoThucModel(int hour, int minute, int status, int topic) {
        this.hour = hour;
        this.minute = minute;
        this.status = status;
        this.topic = topic;

    }
}
