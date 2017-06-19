package com.example.linshiwei.colorpicker.mainview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.datasource.CollectedColor;

import java.util.ArrayList;

/**
 * Created by linshiwei on 2017/6/7.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder> {
    private static final String TAG = "RecyclerAdapter";

    private ArrayList<CollectedColor> mColors;

    public class ItemHolder extends RecyclerView.ViewHolder {

        private ColorInformationView mColorInformationView;

        public ItemHolder(View itemView) {
            super(itemView);
            mColorInformationView = (ColorInformationView) itemView.findViewById(R.id.color_information_view);
        }

        public void bindCurrentColor(int color){
            mColorInformationView.setCurrentColor(color);
        }
    }


    public RecyclerAdapter(ArrayList<CollectedColor> colors) {
        mColors = colors;
    }

    @Override
    public RecyclerAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_row,parent,false);

        return new ItemHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ItemHolder holder, int position) {
        int color = mColors.get(position).color;
        holder.bindCurrentColor(color);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mColors.size());
        return mColors.size();
    }
}
