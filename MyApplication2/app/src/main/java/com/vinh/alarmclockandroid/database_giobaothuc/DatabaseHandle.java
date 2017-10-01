package com.vinh.alarmclockandroid.database_giobaothuc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 9/5/2017.
 */

public class DatabaseHandle {
    private MyDatabase myDatabase;

    public DatabaseHandle(Context context)
    {
        myDatabase = new MyDatabase(context);
    }

    private static DatabaseHandle databaseHandle;

    public static DatabaseHandle getInstance(Context context)
    {
        if (databaseHandle == null)
        {
            databaseHandle = new DatabaseHandle(context);
        }

        return databaseHandle;
    }

    private SQLiteDatabase sqLiteDatabase;

    public List<GioBaoThucModel> getListGioBaoThuc()
    {
        List<GioBaoThucModel> gioBaoThucModelList = new ArrayList<>();

        sqLiteDatabase = myDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM giobaothuc", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            // get data
            int hour = cursor.getInt(0);
            int minute = cursor.getInt(1);
            int status = cursor.getInt(2);
            int topic = cursor.getInt(3);

            GioBaoThucModel quizModel = new GioBaoThucModel(hour, minute, status, topic);
            gioBaoThucModelList.add(quizModel);

            //next cursor
            cursor.moveToNext();
        }
        Log.d(TAG, "getListGioBaoThuc: " + gioBaoThucModelList.toString());

        return gioBaoThucModelList;
    }

    public void insertTable(int gio, int phut, int status, int topic){
        sqLiteDatabase = myDatabase.getWritableDatabase();

        sqLiteDatabase.execSQL("insert into giobaothuc values (" + gio + "," + phut + "," + status + "," + topic + ")");

    }

    public void deleteTable(){
        sqLiteDatabase = myDatabase.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from giobaothuc");
    }

    public void setBookmark(GioBaoThucModel gioBaoThucModel, int status)
    {
        sqLiteDatabase = myDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (status == 0)
        {
            contentValues.put("status", 1);
        }
        else
        {
            contentValues.put("status", 0);
        }
//        sqLiteDatabase.update("giobaothuc", contentValues, "id = ?", new String[]{String.valueOf(gioBaoThucModel.getId())});
    }
}
