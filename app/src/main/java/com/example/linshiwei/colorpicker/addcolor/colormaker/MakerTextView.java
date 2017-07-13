package com.example.linshiwei.colorpicker.addcolor.colormaker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.util.AttributeSet;


/**
 * Created by linshiwei on 2017/6/9.
 */

public class MakerTextView extends AppCompatTextView {

    private int mColorComponentValue = 255;
    private boolean isFocusedOn = false;

    public int focusedBackgroundColor = Color.YELLOW;

    public MakerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHint(String.valueOf(mColorComponentValue));
        setEnabled(false);
        setInputType(InputType.TYPE_NULL);
    }

    public void setColorComponentValue(int value){
        mColorComponentValue = value;
        setHint(getText());
    }

    public int getColorComponentValue(){
        return mColorComponentValue;
    }

    public void setIsFocusedOn(boolean value){
        isFocusedOn = value;
        if(isFocusedOn){
            setBackgroundColor(focusedBackgroundColor);
            setText("");
        }else{
            setBackgroundColor(Color.WHITE);
        }
    }
}
