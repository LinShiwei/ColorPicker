package com.example.linshiwei.colorpicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by linshiwei on 2017/6/9.
 */

public class AddColorActivity extends AppCompatActivity {
    private static final String TAG = "AddColorActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_color);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.left_to_right,R.animator.right_to_left);
    }

    public void onClickChangeModeButton(View v){

        switch (v.getId()){
            case R.id.buttonRGB:

                break;
            case R.id.buttonHEX:
                Log.i(TAG,"HEX");

                break;
        }
    }

    public void onClickNumberButton(View v){
        Button btn = (Button)v;
        Log.d(TAG,btn.getText().toString());
    }
}
