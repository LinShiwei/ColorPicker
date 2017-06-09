package com.example.linshiwei.colorpicker;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by linshiwei on 2017/6/7.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder> {
    private static final String TAG = "RecyclerAdapter";

    private ArrayList<String> mTitles;

    public class ItemHolder extends RecyclerView.ViewHolder {

        private TextView mTitleView;

        public ItemHolder(View itemView) {
            super(itemView);
            mTitleView = (TextView) itemView.findViewById(R.id.item_description);
        }

        public void bindTitle(String text){
            mTitleView.setText(text);
        }
    }


    public RecyclerAdapter(ArrayList<String> titles) {
        mTitles = titles;
    }

    @Override
    public RecyclerAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_row,parent,false);

        return new ItemHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ItemHolder holder, int position) {
        String titleText = mTitles.get(position);
        holder.bindTitle(titleText);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mTitles.size());
        return mTitles.size();
    }
}
