package com.example.linshiwei.colorpicker.mainview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.datasource.CollectedColor;
import com.example.linshiwei.colorpicker.globalshared.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;

/**
 * Created by linshiwei on 2017/6/7.
 */

public class RecyclerAdapter extends SwipeMenuAdapter<RecyclerAdapter.ItemHolder> {
    private static final String TAG = "RecyclerAdapter";

    private ArrayList<CollectedColor> mColors;

    private OnItemClickListener mOnItemClickListener;

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ColorInformationView mColorInformationView;
        public OnItemClickListener mListener;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mColorInformationView = (ColorInformationView) itemView.findViewById(R.id.color_information_view);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null){
                mListener.onItemClick(getAdapterPosition());
            }
        }

        public void bindCurrentColor(int color){
            mColorInformationView.setCurrentColor(color);
        }
    }


    public RecyclerAdapter(ArrayList<CollectedColor> colors) {
        mColors = colors;
    }

//    @Override
//    public RecyclerAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View inflatedView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recycleview_item_row,parent,false);
//        ItemHolder holder = new ItemHolder(inflatedView);
//        holder.mListener = mOnItemClickListener;
//        return holder;
//    }


    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_row,parent,false);
        return inflatedView;
    }

    @Override
    public ItemHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        ItemHolder holder = new ItemHolder(realContentView);
        holder.mListener = mOnItemClickListener;
        return holder;
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
