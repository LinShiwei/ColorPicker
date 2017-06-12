package com.example.linshiwei.colorpicker.colormaker;

import android.content.Context;
import android.widget.LinearLayout;

public class ColorMakerView extends LinearLayout {

    private MakerInputMode inputMode;

    public ColorMakerView(Context context) {
        super(context);
    }

    public MakerInputMode getInputMode(){
        return inputMode;
    }

    public void setInputMode(MakerInputMode value){
        inputMode = value;

    }
}
