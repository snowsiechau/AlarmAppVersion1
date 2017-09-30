package com.vinh.alarmclockandroid.database_giobaothuc;

import java.io.Serializable;

/**
 * Created by Admin on 9/5/2017.
 */

public class GioBaoThucModel implements Serializable {
    private int id;
    private int hour;
    private int minute;
    private int status;
    private int so1;
    private int so2;
    private int so3;
    private int so4;
    private int so5;
    private int so6;
    private int so7;

    @Override
    public String toString() {
        return "GioBaoThucModel{" +
                "id=" + id +
                ", hour=" + hour +
                ", minute=" + minute +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSo1() {
        return so1;
    }

    public int getSo2() {
        return so2;
    }

    public int getSo3() {
        return so3;
    }

    public int getSo4() {
        return so4;
    }

    public int getSo5() {
        return so5;
    }

    public int getSo6() {
        return so6;
    }

    public int getSo7() {
        return so7;
    }

    public void setSo1(int so1) {
        this.so1 = so1;
    }

    public void setSo2(int so2) {
        this.so2 = so2;
    }

    public void setSo3(int so3) {
        this.so3 = so3;
    }

    public void setSo4(int so4) {
        this.so4 = so4;
    }

    public void setSo5(int so5) {
        this.so5 = so5;
    }

    public void setSo6(int so6) {
        this.so6 = so6;
    }

    public void setSo7(int so7) {
        this.so7 = so7;
    }

    public GioBaoThucModel(int id, int hour, int minute, int so1, int so2, int so3, int so4, int so5, int so6, int so7, int status) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.status = status;
        this.so1 = so1;
        this.so2 = so2;
        this.so3 = so3;
        this.so4 = so4;
        this.so5 = so5;
        this.so6 = so6;
        this.so7 = so7;

    }
}
