package com.example.linshiwei.colorpicker.mainview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;

/**
 * Created by linshiwei on 2017/6/19.
 */

public class ColorComponentsContainerView extends LinearLayout {

    private ColorComponentView mComponentRorHView;
    private ColorComponentView mComponentGorSView;
    private ColorComponentView mComponentBorVView;

    public ColorComponentsContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.color_components_container_view,this,true);

        mComponentRorHView = (ColorComponentView) v.findViewById(R.id.comp_1);
        mComponentGorSView = (ColorComponentView) v.findViewById(R.id.comp_2);
        mComponentBorVView = (ColorComponentView) v.findViewById(R.id.comp_3);
    }




}
