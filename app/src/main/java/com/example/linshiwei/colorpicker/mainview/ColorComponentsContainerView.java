package com.example.linshiwei.colorpicker.mainview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.globalshared.ColorComponentsStyle;
import com.example.linshiwei.colorpicker.globalshared.GlobalValue;

/**
 * Created by linshiwei on 2017/6/19.
 */

public class ColorComponentsContainerView extends LinearLayout {

    public int color = Color.WHITE;

    private ColorComponentView mComponentRorHView;
    private ColorComponentView mComponentGorSView;
    private ColorComponentView mComponentBorVView;

    public ColorComponentsContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.color_components_container_view,this,true);

        mComponentRorHView = (ColorComponentView) v.findViewById(R.id.comp_1);
        mComponentGorSView = (ColorComponentView) v.findViewById(R.id.comp_2);
        mComponentBorVView = (ColorComponentView) v.findViewById(R.id.comp_3);

        updateColorValue();
    }

    public void updateColorValue(){
        switch (GlobalValue.colorStyle){
            case rgb:
                updateRGBValue();
                break;
            case hsv:
                updateHSVValue();
                break;
        }
    }

    private void updateRGBValue(){
        mComponentRorHView.setName("R");
        mComponentGorSView.setName("G");
        mComponentBorVView.setName("B");

        mComponentRorHView.setValue(Color.red(color));
        mComponentGorSView.setValue(Color.green(color));
        mComponentBorVView.setValue(Color.blue(color));
    }

    private void updateHSVValue(){
        mComponentRorHView.setName("H");
        mComponentGorSView.setName("S");
        mComponentBorVView.setName("V");

        float hsv[] = {0,0,0};
        Color.colorToHSV(color,hsv);

        mComponentRorHView.setValue(hsv[0]);
        mComponentGorSView.setValue(hsv[1]);
        mComponentBorVView.setValue(hsv[2]);
    }

}
