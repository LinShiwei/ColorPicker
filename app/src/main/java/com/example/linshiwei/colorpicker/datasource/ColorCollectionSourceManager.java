package com.example.linshiwei.colorpicker.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        ArrayList<CollectedColor> colors = new ArrayList<>();
        while(cursor.moveToNext()) {

            int index = cursor.getColumnIndexOrThrow(ColorCollector.ColorElement.COLOR_INT);
            int color = cursor.getInt(index);

            int dateIndex = cursor.getColumnIndexOrThrow(ColorCollector.ColorElement.DESCRIPTION);
            Date date = getDateFromString(cursor.getString(dateIndex));
            colors.add(new CollectedColor(color,date));


        }
        cursor.close();
        callBack.onGetData(true,colors);
    }

    public void saveOneColor(ColorCollectionDbHelper dbHelper, CollectedColor color, FinishCallBack callBack){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColorCollector.ColorElement.DESCRIPTION,getStringFromDate(color.addingDate));
        values.put(ColorCollector.ColorElement.COLOR_INT,color.color);
        long newID = db.insert(ColorCollector.ColorElement.TABLE_NAME,null,values);
        if(newID == -1){
            callBack.onFinish(false);
        }else{
            callBack.onFinish(true);
        }
    }

    public void saveOneColor(ColorCollectionDbHelper dbHelper,Integer colorInt,FinishCallBack callBack){

        CollectedColor color = new CollectedColor(colorInt,Calendar.getInstance().getTime());
        saveOneColor(dbHelper,color,callBack);
    }

    private String getStringFromDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    private Date getDateFromString(String str){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date;
        try {
            date = dateFormat.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance().getTime();
        }
    }

}
