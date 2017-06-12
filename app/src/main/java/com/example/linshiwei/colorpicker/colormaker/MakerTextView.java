package com.example.linshiwei.colorpicker.colormaker;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.util.AttributeSet;


/**
 * Created by linshiwei on 2017/6/9.
 */

public class MakerTextView extends AppCompatTextView {

    private int colorComponentValue = 255;
    private boolean isFocusedOn = false;

    public int focusedBackgroundColor = Color.YELLOW;

    public MakerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHint(String.valueOf(colorComponentValue));
        setEnabled(false);
        setInputType(InputType.TYPE_NULL);
    }

    public void setColorComponentValue(int value){
        int old = colorComponentValue;
        colorComponentValue = value;
        didChangeColorComponentValue(colorComponentValue,old);
    }

    public int getColorComponentValue(){
        return colorComponentValue;
    }

    public void setIsFocusedOn(boolean value){
        boolean old = isFocusedOn;
        isFocusedOn = value;
        didChangeIsFocusedOn(isFocusedOn,value);
    }

    private void didChangeColorComponentValue(int newValue,int oldValue){
        setHint(String.valueOf(newValue));
    }

    private void didChangeIsFocusedOn(boolean newValue, boolean oldValue){
        if(isFocusedOn){
            setBackgroundColor(focusedBackgroundColor);
            setText("");
        }else{
            setBackgroundColor(Color.WHITE);
        }

    }


}
