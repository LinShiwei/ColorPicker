package com.example.linshiwei.colorpicker.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by linshiwei on 2017/6/14.
 */

public class ColorCollectionDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ColorCollection.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ColorCollector.ColorElement.TABLE_NAME + " (" +
                    ColorCollector.ColorElement._ID + " INTEGER PRIMARY KEY," +
                    ColorCollector.ColorElement.DESCRIPTION + " TEXT," +
                    ColorCollector.ColorElement.COLOR_INT + " TEXT)";

    private  static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ColorCollector.ColorElement.TABLE_NAME;


    public ColorCollectionDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
