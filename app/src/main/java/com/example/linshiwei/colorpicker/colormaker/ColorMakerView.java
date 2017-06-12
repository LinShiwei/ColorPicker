package com.example.linshiwei.colorpicker.colormaker;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ColorMakerView extends LinearLayout {

    private MakerInputMode inputMode;

    public ColorMakerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MakerInputMode getInputMode(){
        return inputMode;
    }

    public void setInputMode(MakerInputMode value){
        inputMode = value;

    }
}
