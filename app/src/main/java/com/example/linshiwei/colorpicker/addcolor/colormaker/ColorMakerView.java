package com.example.linshiwei.colorpicker.addcolor.colormaker;

import android.content.Context;
import android.graphics.Color;
import android.renderscript.ScriptGroup;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.addcolor.InputButtonContainerView;
import com.example.linshiwei.colorpicker.addcolor.MakerInputMode;

public class ColorMakerView extends LinearLayout {

    private RGBMakerView mRGBMakerView;
    private HEXMakerView mHEXMakerView;

    private MakerInputMode mInputMode = MakerInputMode.dec;

    public ColorMakerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.color_maker_view,this,true);


        if (mRGBMakerView == null){
            mRGBMakerView = (RGBMakerView) v.findViewById(R.id.rgb_maker_view);
        }
        if (mHEXMakerView == null){
            mHEXMakerView = (HEXMakerView) v.findViewById(R.id.hex_maker_view);
        }
    }

    public MakerInputMode getInputMode(){
        return mInputMode;
    }

    public void setInputMode(MakerInputMode value){
        mInputMode = value;
        if (getParent() instanceof ViewGroup){
            ViewGroup parent = (ViewGroup)getParent();
            final int count = parent.getChildCount();
            for (int i = 0;i<count;i++){
                if (parent.getChildAt(i) instanceof InputButtonContainerView){
                    InputButtonContainerView v = (InputButtonContainerView)(parent.getChildAt(i));
                    v.setInputMode(mInputMode);
                }
            }
            mRGBMakerView.setInputMode(mInputMode);
            mHEXMakerView.setInputMode(mInputMode);
        }
    }

    public void inputOneNumber(String numberString){
        if ((mRGBMakerView == null)||(mHEXMakerView == null)){
            //return
        }else{
            int dec = -1;
            switch (mInputMode){
                case dec:
                    dec = Integer.parseInt(mRGBMakerView.currentTextField.getText().toString() + numberString);
                    break;
                case hex:
                    dec = Integer.parseInt(mHEXMakerView.currentTextField.getText().toString() + numberString,16);
                    break;
            }
            if((dec>=0)&&(dec<256)){
                mRGBMakerView.setCurrentValue(dec);
                mHEXMakerView.setCurrentValue(dec);
            }
        }
    }

    public void tapToFocusOnNextTextField(){
        mRGBMakerView.switchToNextTextField();
        mHEXMakerView.switchToNextTextField();
    }

    public void clearAllTextFields(){
        mRGBMakerView.clearAllTextFields();
        mHEXMakerView.clearAllTextFields();
    }

    public int getCurrentColor(){
        if ((mRGBMakerView != null)&&(mHEXMakerView != null)){
            return mRGBMakerView.getCurrentColor();
        }else{
            return Color.WHITE;
        }
    }
}
