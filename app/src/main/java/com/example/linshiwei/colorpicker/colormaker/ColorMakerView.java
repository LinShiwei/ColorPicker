package com.example.linshiwei.colorpicker.colormaker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;

public class ColorMakerView extends LinearLayout {

    private RGBMakerView mRGBMakerView;
    private HEXMakerView mHEXMakerView;

    private MakerInputMode inputMode;

    public ColorMakerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.color_maker_view,this,true);
    }

    public MakerInputMode getInputMode(){
        return inputMode;
    }

    public void setInputMode(MakerInputMode value){
        inputMode = value;

    }
}
