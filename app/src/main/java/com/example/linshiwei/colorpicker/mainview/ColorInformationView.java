package com.example.linshiwei.colorpicker.mainview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;

import static com.example.linshiwei.colorpicker.addcolor.MakerInputMode.dec;

/**
 * Created by linshiwei on 2017/6/19.
 */

public class ColorInformationView extends LinearLayout {



    public ColorInformationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.color_information_view,this,true);


    }

}
