package com.vinh.alarmclockandroid.database_giobaothuc;

import android.content.Context;
import android.content.res.Resources;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Admin on 9/5/2017.
 */

public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "giobaothuc.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @requires a != null /\ a!= empty /\ a is sorted
     */
    public int search(int[] a, int x) {
        int start = 0;
        int stop = a.length;

        return binarySearch(a, x, start, stop);
    }

    public int binarySearch(int[] a, int x, int start,int stop) {

        if (start == stop) {
            throw new Resources.NotFoundException();
        }

        int index = Math.round((stop - start)/2);
        if (a[index] == x) {
            return index;
        } else {
            if (x < a[index]) {
                stop = index-1;
            } else {
                start = index+1;
            }

            return binarySearch(a, x, start, stop);
        }
    }
}
