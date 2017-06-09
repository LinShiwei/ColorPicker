package com.example.linshiwei.colorpicker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import static android.R.attr.bottomLeftRadius;
import static android.R.attr.cacheColorHint;
import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<String> mTitleList;

    private RecyclerAdapter mRecycleViewAdapter;

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
        inflater.inflate(R.menu.add,menu);
        inflater.inflate(R.menu.pick,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_button:
                Log.i(TAG,"addbuttonTap");
//                mTitleList.add("new");
//                Log.i(TAG,"" + mTitleList.size());
//                mRecycleViewAdapter.notifyDataSetChanged();
                Intent intent = new Intent(this,AddColorActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.enter,R.animator.exit);
                break;
            case R.id.pick_button:
                Log.i(TAG,"pickbuttonTap");

                break;
        }
        return true;
    }
}

