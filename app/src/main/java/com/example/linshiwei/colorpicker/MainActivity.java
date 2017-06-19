package com.example.linshiwei.colorpicker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.linshiwei.colorpicker.datasource.CollectedColor;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionDbHelper;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionSourceManager;
import com.example.linshiwei.colorpicker.datasource.ColorCollector;
import com.example.linshiwei.colorpicker.datasource.DataCallBack;
import com.example.linshiwei.colorpicker.datasource.FinishCallBack;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<String> mTitleList;

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

        mTitleList = new ArrayList<>();

        mRecycleViewAdapter = new RecyclerAdapter(mTitleList);
        mRecyclerView.setAdapter(mRecycleViewAdapter);

        mDbHelper = new ColorCollectionDbHelper(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mTitleList.size() == 0){
            for (int i=0;i<20;i++){
                mTitleList.add(Integer.toString(i));
            }
//            mRecycleViewAdapter.notifyDataSetChanged();
        }
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
                if (item.getTitle() == "RGB"){
                    item.setTitle("HSV");
                    GlobalValue.mColorStyle = ColorComponentsStyle.hsv;
                }else{
                    item.setTitle("RGB");
                    GlobalValue.mColorStyle = ColorComponentsStyle.rgb;
                }
                //待完善
                break;
        }
        return true;
    }





}

