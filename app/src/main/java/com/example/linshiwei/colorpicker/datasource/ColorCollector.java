package com.example.linshiwei.colorpicker.datasource;

import android.graphics.Color;
import android.provider.BaseColumns;

import com.example.linshiwei.colorpicker.addcolor.colormaker.ColorMakerView;

/**
 * Created by linshiwei on 2017/6/14.
 */

public class ColorCollector {

    private ColorCollector(){}

    public static class ColorElement implements BaseColumns{
        public static final String TABLE_NAME = "colors";
        public static final String DESCRIPTION = "description";
        public static final String COLOR_INT = "colorInt";
    }

}
