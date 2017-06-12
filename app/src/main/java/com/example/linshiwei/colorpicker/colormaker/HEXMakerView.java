package com.example.linshiwei.colorpicker.colormaker;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.ColorComponentsStyle;
import com.example.linshiwei.colorpicker.GlobalValue;
import com.example.linshiwei.colorpicker.R;

import static com.example.linshiwei.colorpicker.ColorComponentsStyle.rgb;
import static com.example.linshiwei.colorpicker.colormaker.MakerInputMode.hex;
import static com.example.linshiwei.colorpicker.colormaker.MakerInputMode.rgb;

/**
 * Created by linshiwei on 2017/6/12.
 */

public class HEXMakerView extends LinearLayout {

    private Button mTitleButton;
    private MakerTextView mFirstTextField;
    private MakerTextView mSecondTextField;
    private MakerTextView mThirdTextField;

    private MakerInputMode inputMode = hex;
    private int mCurrentValue = 255;

    public MakerTextView mCurrentTextField;

    public HEXMakerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.hex_maker_view,this,true);

        mTitleButton = (Button)findViewById(R.id.buttonHEX);
        mFirstTextField = (MakerTextView)findViewById(R.id.textViewH);
        mSecondTextField = (MakerTextView)findViewById(R.id.textViewE);
        mThirdTextField = (MakerTextView)findViewById(R.id.textViewX);


        mTitleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tapTitleButton(v);
            }
        });

        mFirstTextField.focusedBackgroundColor = Color.WHITE;
        mSecondTextField.focusedBackgroundColor = Color.WHITE;
        mThirdTextField.focusedBackgroundColor = Color.WHITE;

        mCurrentTextField = mFirstTextField;

        if(GlobalValue.mColorStyle == ColorComponentsStyle.hsv){
            mFirstTextField.setColorComponentValue(0);
            mSecondTextField.setColorComponentValue(0);

            mFirstTextField.setHint("0");
            mSecondTextField.setHint("0");
        }
    }

    public MakerInputMode getInputMode(){
        return inputMode;
    }

    public void setInputMode(MakerInputMode value){
        inputMode = value;
        switch (inputMode){
            case dec:
                mFirstTextField.focusedBackgroundColor = Color.WHITE;
                mSecondTextField.focusedBackgroundColor = Color.WHITE;
                mThirdTextField.focusedBackgroundColor = Color.WHITE;

                mCurrentTextField.setIsFocusedOn(false);
                mCurrentTextField.setText("");
                //待完成//

                break;
            case hex:
                mFirstTextField.focusedBackgroundColor = ContextCompat.getColor(getContext(),R.color.textFieldFocusedBackgroundColor);
                mSecondTextField.focusedBackgroundColor = mFirstTextField.focusedBackgroundColor;
                mThirdTextField.focusedBackgroundColor = mFirstTextField.focusedBackgroundColor;

                mCurrentTextField.setIsFocusedOn(true);
                //待完成//
                break;
        }
    }

    public int getCurrentValue(){
        return mCurrentValue;
    }

    public void setCurrentValue(int value){
        mCurrentValue = value;
        mCurrentTextField.setText(Integer.toHexString(mCurrentValue));
        mCurrentTextField.setColorComponentValue(mCurrentValue);
    }

    public void tapTitleButton(View v){
        if(getParent() instanceof ColorMakerView){
            ColorMakerView maker = (ColorMakerView)getParent();
            maker.setInputMode(hex);
        }
    }

    public void clearAllTextFields(){
        mFirstTextField.setText("");
        mSecondTextField.setText("");
        mThirdTextField.setText("");
    }

    public void switchToNextTextField(){
        mCurrentTextField.setIsFocusedOn(false);
        if(mCurrentTextField == mFirstTextField){
            mCurrentTextField = mSecondTextField;
        }else{
            if(mCurrentTextField == mSecondTextField){
                mCurrentTextField = mThirdTextField;
            }else{
                mCurrentTextField = mFirstTextField;
            }
        }
        mCurrentTextField.setIsFocusedOn(true);
    }
}
