package com.example.linshiwei.colorpicker.addcolor;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linshiwei on 2017/6/12.
 */

public class InputButtonContainerView extends LinearLayout {

    public ArrayList<Button> hexButtons = new ArrayList<>();

    private MakerInputMode mInputMode = MakerInputMode.dec;

    public InputButtonContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.input_button_container_view,this,true);

        hexButtons = findButtonWithTagRecursively(this,R.string.hex_tag);
        //待完善


    }

    public MakerInputMode getInputMode(){
        return mInputMode;
    }

    public void setInputMode(){
        switch (mInputMode){
            case dec:
                for (Button btn:hexButtons) {
                    btn.setEnabled(false);
                    //待完善
                }
                break;
            case hex:
                for (Button btn :
                        hexButtons) {
                    btn.setEnabled(true);
                    //待完善
                }
                break;
        }
    }


    private static ArrayList<Button> findButtonWithTagRecursively(ViewGroup root, Object tag){
        ArrayList<Button> allViews = new ArrayList<>();

        final int childCount = root.getChildCount();
        for(int i=0; i<childCount; i++){
            final View childView = root.getChildAt(i);

            if(childView instanceof ViewGroup){
                allViews.addAll(findButtonWithTagRecursively((ViewGroup)childView, tag));
            }
            else{
                final Object tagView = childView.getTag();
                if(tagView != null && tagView.equals(tag))
                    if (childView instanceof Button){
                        allViews.add((Button)childView);
                    }
            }
        }

        return allViews;
    }

}
