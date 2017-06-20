package com.example.linshiwei.colorpicker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.linshiwei.colorpicker.globalshared.ColorComponentsStyle;
import com.example.linshiwei.colorpicker.globalshared.GlobalValue;
import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.mainview.ColorInformationView;
import com.example.linshiwei.colorpicker.mainview.RecyclerAdapter;
import com.example.linshiwei.colorpicker.datasource.CollectedColor;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionDbHelper;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionSourceManager;
import com.example.linshiwei.colorpicker.datasource.DataCallBack;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<CollectedColor> mColors;

    private RecyclerAdapter mRecycleViewAdapter;
    private ColorCollectionDbHelper mDbHelper;
    private ColorCollectionSourceManager manager = ColorCollectionSourceManager.INSTANCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mColors = new ArrayList<>();

        mRecycleViewAdapter = new RecyclerAdapter(mColors);
        mRecyclerView.setAdapter(mRecycleViewAdapter);

        mDbHelper = new ColorCollectionDbHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        manager.getAllCollectedColor(mDbHelper, new DataCallBack() {
            @Override
            public void onGetData(Boolean success, ArrayList<CollectedColor> colors) {
                if (success&&colors != null){
                    mColors.clear();
                    mColors.addAll(colors);
                    mRecycleViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rgb_hsv,menu);
        inflater.inflate(R.menu.add,menu);
        inflater.inflate(R.menu.pick,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_button:
                Log.i(TAG,"addbuttonTap");

                Intent intent = new Intent(this,AddColorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.enter,R.animator.exit);



                break;
            case R.id.pick_button:
                Log.i(TAG,"pickbuttonTap");
                manager.getAllCollectedColor(mDbHelper,new DataCallBack() {
                    @Override
                    public void onGetData(Boolean success, ArrayList<CollectedColor> colors) {
                        if (success){
                            List a = colors;
                            int c= 3;
                        }
                    }
                });
                break;
            case R.id.rgb_hsv_button:
                tapToChangeColorStyle(item);
                break;
        }
        return true;
    }

    private void tapToChangeColorStyle(MenuItem item){
        if (item.getTitle() == "RGB"){
            item.setTitle("HSV");
            GlobalValue.colorStyle = ColorComponentsStyle.hsv;
        }else{
            item.setTitle("RGB");
            GlobalValue.colorStyle = ColorComponentsStyle.rgb;
        }
        //待完善
        updateColorValue();

    }

    private void updateColorValue(){
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int iFirst = linearLayoutManager.findFirstVisibleItemPosition();
        int iLast = linearLayoutManager.findLastVisibleItemPosition();
        for (int i = iFirst;i<=iLast;i++){
            View v = mRecyclerView.getChildAt(i);
            if (v instanceof ColorInformationView){
                ((ColorInformationView) v).refreshColor();
            }
        }
    }

}

