package com.example.linshiwei.colorpicker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.addcolor.InputButtonContainerView;
import com.example.linshiwei.colorpicker.addcolor.colormaker.ColorMakerView;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionDbHelper;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionSourceManager;
import com.example.linshiwei.colorpicker.datasource.FinishCallBack;
import com.example.linshiwei.colorpicker.mainview.ColorInformationView;

/**
 * Created by linshiwei on 2017/7/14.
 */

public class PickColorActivity extends AppCompatActivity {

    private static final String TAG = "PickColorActivity";

//    private
    private ColorInformationView mColorInformationView;
    private ColorCollectionDbHelper mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color);

        LinearLayout layout = (LinearLayout) findViewById(R.id.root_layout);
//        mColorMakerView =
        mColorInformationView = (ColorInformationView) findViewById(R.id.color_information_view);

        mDbHelper = new ColorCollectionDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_button:
                Log.i(TAG,"savebuttonTap");
                saveCurrentColor(item);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.left_to_right,R.animator.right_to_left);
    }


    public void saveCurrentColor(MenuItem item){
        //待完善
//        ColorCollectionSourceManager.INSTANCE.saveOneColor(mDbHelper, mColorMakerView.getCurrentColor(), new FinishCallBack() {
//            @Override
//            public void onFinish(Boolean success) {
//                if (success){
//                    Log.i(TAG,"Save success");
//                }else{
//                    Log.i(TAG,"Save fail");
//                }
//            }
//        });

    }

}
