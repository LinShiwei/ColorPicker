package com.example.linshiwei.colorpicker.addcolor.colormaker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.addcolor.MakerInputMode;

public class ColorMakerView extends LinearLayout {

    private RGBMakerView mRGBMakerView;
    private HEXMakerView mHEXMakerView;

    private MakerInputMode mInputMode;

    public ColorMakerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.color_maker_view,this,true);
    }

    public MakerInputMode getInputMode(){
        return mInputMode;
    }

    public void setInputMode(MakerInputMode value){
        mInputMode = value;

    }
}
