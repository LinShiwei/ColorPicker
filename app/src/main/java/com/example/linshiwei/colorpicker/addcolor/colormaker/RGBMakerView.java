package com.example.linshiwei.colorpicker.addcolor.colormaker;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.globalshared.ColorComponentsStyle;
import com.example.linshiwei.colorpicker.globalshared.GlobalValue;
import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.addcolor.MakerInputMode;

import static com.example.linshiwei.colorpicker.addcolor.MakerInputMode.dec;
import static com.example.linshiwei.colorpicker.addcolor.MakerInputMode.hex;

/**
 * Created by linshiwei on 2017/6/9.
 */

public class RGBMakerView extends LinearLayout {

    private Button mTitleButton;
    private MakerTextView mFirstTextField;
    private MakerTextView mSecondTextField;
    private MakerTextView mThirdTextField;

    private MakerInputMode mInputMode = hex;
    private int mCurrentValue = 255;

    public MakerTextView currentTextField;

    public RGBMakerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.hex_maker_view,this,true);

        mTitleButton = (Button)findViewById(R.id.buttonHEX);
        mFirstTextField = (MakerTextView)findViewById(R.id.textViewH);
        mSecondTextField = (MakerTextView)findViewById(R.id.textViewE);
        mThirdTextField = (MakerTextView)findViewById(R.id.textViewX);

        mTitleButton.setText(GlobalValue.colorStyle == ColorComponentsStyle.rgb ? "RGB" : "HSV");
        mTitleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tapTitleButton(v);
            }
        });

        currentTextField = mFirstTextField;
        currentTextField.setIsFocusedOn(true);

        if(GlobalValue.colorStyle == ColorComponentsStyle.hsv){
            mFirstTextField.setColorComponentValue(0);
            mSecondTextField.setColorComponentValue(0);

            mFirstTextField.setHint("0");
            mSecondTextField.setHint("0");
        }
    }

    public MakerInputMode getInputMode(){
        return mInputMode;
    }

    public void setInputMode(MakerInputMode value){
        mInputMode = value;
        switch (mInputMode){
            case dec:
                mFirstTextField.focusedBackgroundColor = ContextCompat.getColor(getContext(),R.color.textFieldFocusedBackgroundColor);
                mSecondTextField.focusedBackgroundColor = mFirstTextField.focusedBackgroundColor;
                mThirdTextField.focusedBackgroundColor = mFirstTextField.focusedBackgroundColor;

                currentTextField.setIsFocusedOn(true);
                //待完成//

                break;
            case hex:
                mFirstTextField.focusedBackgroundColor = Color.WHITE;
                mSecondTextField.focusedBackgroundColor = Color.WHITE;
                mThirdTextField.focusedBackgroundColor = Color.WHITE;

                currentTextField.setIsFocusedOn(false);
                currentTextField.setText("");

                //待完成//
                break;
        }
    }

    public int getCurrentValue(){
        return mCurrentValue;
    }

    public void setCurrentValue(int value){
        mCurrentValue = value;
        currentTextField.setText(Integer.toString(mCurrentValue));
        currentTextField.setColorComponentValue(mCurrentValue);
    }

    public void tapTitleButton(View v){
        ViewParent vv = getParent().getParent();
        if(vv instanceof ColorMakerView){
            ColorMakerView maker = (ColorMakerView)vv;
            maker.setInputMode(dec);
        }
    }

    public void clearAllTextFields(){
        mFirstTextField.setText("");
        mSecondTextField.setText("");
        mThirdTextField.setText("");
    }

    public void switchToNextTextField(){
        currentTextField.setIsFocusedOn(false);
        if(currentTextField == mFirstTextField){
            currentTextField = mSecondTextField;
        }else{
            if(currentTextField == mSecondTextField){
                currentTextField = mThirdTextField;
            }else{
                currentTextField = mFirstTextField;
            }
        }
        currentTextField.setIsFocusedOn(true);
    }

    public int getCurrentColor(){
        if(GlobalValue.colorStyle == ColorComponentsStyle.rgb){
            return Color.rgb(mFirstTextField.getColorComponentValue(),
                    mSecondTextField.getColorComponentValue(),
                    mThirdTextField.getColorComponentValue());
        }else{
            float[] hsv = {mFirstTextField.getColorComponentValue(),
                    mSecondTextField.getColorComponentValue(),
                    mThirdTextField.getColorComponentValue()};
            return Color.HSVToColor(hsv);
        }
    }

}
