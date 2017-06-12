package com.example.linshiwei.colorpicker.colormaker;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;

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
        mTitleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tapTitleButton(v);
            }
        });
        View child1 = findViewById(R.id.textViewH);
        View child2 = findViewById(R.id.buttonHEX);

    }

    public MakerInputMode getInputMode(){
        return inputMode;
    }

    public void setInputMode(MakerInputMode value){
        inputMode = value;
        switch (inputMode){
            case hex:
                mFirstTextField.focusedBackgroundColor = Color.WHITE;
                mSecondTextField.focusedBackgroundColor = Color.WHITE;
                mThirdTextField.focusedBackgroundColor = Color.WHITE;

                break;
            case rgb:

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
}
