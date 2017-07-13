package com.example.linshiwei.colorpicker.mainview;

import android.content.Context;
import android.graphics.Color;
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

    private int mCurrentColor = Color.WHITE;

    private View mColorIndicationView;
    private ColorComponentsContainerView mColorComponentsContainerView;

    public ColorInformationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.color_information_view,this,true);

        mColorComponentsContainerView = (ColorComponentsContainerView) v.findViewById(R.id.color_comp_container);
        mColorIndicationView = v.findViewById(R.id.color_preview_view);

        //待完善


    }

    public void setCurrentColor(int currentColor) {
        mCurrentColor = currentColor;
        mColorIndicationView.setBackgroundColor(mCurrentColor);
        mColorComponentsContainerView.color = mCurrentColor;
        mColorComponentsContainerView.updateColorValue();
    }

    public void refreshColor(){
        setCurrentColor(mCurrentColor);
    }

    public int getCurrentColor() {
        return mCurrentColor;
    }
}
