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
            int id = cursor.getInt(0);
            int hour = cursor.getInt(1);
            int minute = cursor.getInt(2);
            int so1 = cursor.getInt(3);
            int so2 = cursor.getInt(4);
            int so3 = cursor.getInt(5);
            int so4 = cursor.getInt(6);
            int so5 = cursor.getInt(7);
            int so6 = cursor.getInt(8);
            int so7 = cursor.getInt(9);
            int status = cursor.getInt(10);

            GioBaoThucModel quizModel = new GioBaoThucModel(id, hour, minute, so1, so2, so3, so4, so5, so6, so7, status);
            gioBaoThucModelList.add(quizModel);

            //next cursor
            cursor.moveToNext();
        }
        Log.d(TAG, "getListGioBaoThuc: " + gioBaoThucModelList.toString());

        return gioBaoThucModelList;
    }

    public void insertTable(){
        sqLiteDatabase = myDatabase.getWritableDatabase();

        sqLiteDatabase.execSQL("insert into giobaothuc values (1230,12,30,1,0,1,0,1,0,1,1)");

//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put("id", 2030);
//        contentValues.put("hour", 20);
//        contentValues.put("minute", 30);
//        contentValues.put("mot", 1);
//        contentValues.put("hai", 1);
//        contentValues.put("ba", 1);
//        contentValues.put("bon", 1);
//        contentValues.put("nam", 1);
//        contentValues.put("sau", 1);
//        contentValues.put("bay", 1);
//        contentValues.put("status", 1);
//
//        sqLiteDatabase.insert("giobaothuc", null, contentValues);
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
        sqLiteDatabase.update("giobaothuc", contentValues, "id = ?", new String[]{String.valueOf(gioBaoThucModel.getId())});
    }
}
