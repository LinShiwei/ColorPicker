package com.example.linshiwei.colorpicker.mainview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linshiwei.colorpicker.R;

import java.util.Locale;

/**
 * Created by linshiwei on 2017/6/19.
 */

public class ColorComponentView extends LinearLayout {

    private TextView mComponentTitleLabel;
    private TextView mHexValueLabel;
    private TextView mDecValueLabel;
    private TextView mNormalizedValueLabel;

    private String name = "";
    private float value = 1;

    public ColorComponentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.color_component_view,this,true);

        mComponentTitleLabel = (TextView) v.findViewById(R.id.comp_title_textView);
        mHexValueLabel = (TextView) v.findViewById(R.id.comp_hex_textView);
        mDecValueLabel = (TextView) v.findViewById(R.id.comp_dec_textView);
        mNormalizedValueLabel = (TextView) v.findViewById(R.id.comp_normalize_textView);
    }

    public void setName(String name){
        this.name = name;
        mComponentTitleLabel.setText(this.name);
    }

    public String getName(){
        return this.name;
    }

    public void setValue(float value){
        this.value = value;
        mNormalizedValueLabel.setText(String.format(Locale.US, "%.2f", value/255.0));
        mDecValueLabel.setText(String.valueOf(Math.round(value)));
        mHexValueLabel.setText(Integer.toHexString(Math.round(value)).toUpperCase());
    }

    public float getValue(){
        return this.value;
    }

}
