package com.example.linshiwei.colorpicker.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linshiwei.colorpicker.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by linshiwei on 2017/6/14.
 */

public enum ColorCollectionSourceManager {

    INSTANCE;

    ColorCollectionSourceManager(){

    }

    public void getAllCollectedColor(ColorCollectionDbHelper dbHelper ,DataCallBack callBack){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                ColorCollector.ColorElement._ID,
                ColorCollector.ColorElement.DESCRIPTION,
                ColorCollector.ColorElement.COLOR_INT
        };

//        String selection = ColorCollector.ColorElement.DESCRIPTION + " = ?";
//        String[] selectionArgs = { "desc" };

        String sortOrder =
                ColorCollector.ColorElement.DESCRIPTION + " DESC";

        Cursor cursor = db.query(
                ColorCollector.ColorElement.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<Integer> colors = new ArrayList<>();
        while(cursor.moveToNext()) {

            int index = cursor.getColumnIndexOrThrow(ColorCollector.ColorElement.COLOR_INT);

            int color = cursor.getInt(index);
            colors.add(color);


        }
        cursor.close();
        callBack.onGetData(true,colors);
    }

    public void saveOneColor(ColorCollectionDbHelper dbHelper, int color, FinishCallBack callBack){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColorCollector.ColorElement.DESCRIPTION,Calendar.getInstance().getTime().toString());
        values.put(ColorCollector.ColorElement.COLOR_INT,color);
        long newID = db.insert(ColorCollector.ColorElement.TABLE_NAME,null,values);
        if(newID == -1){
            callBack.onFinish(false);
        }else{
            callBack.onFinish(true);
        }
    }
}
