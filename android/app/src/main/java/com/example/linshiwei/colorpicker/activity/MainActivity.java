package com.example.linshiwei.colorpicker.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.linshiwei.colorpicker.datasource.FinishCallBack;
import com.example.linshiwei.colorpicker.globalshared.ColorComponentsStyle;
import com.example.linshiwei.colorpicker.globalshared.DensityUtility;
import com.example.linshiwei.colorpicker.globalshared.GlobalValue;
import com.example.linshiwei.colorpicker.R;
import com.example.linshiwei.colorpicker.globalshared.ListViewDecoration;
import com.example.linshiwei.colorpicker.globalshared.OnItemClickListener;
import com.example.linshiwei.colorpicker.mainview.ColorInformationView;
import com.example.linshiwei.colorpicker.mainview.RecyclerAdapter;
import com.example.linshiwei.colorpicker.datasource.CollectedColor;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionDbHelper;
import com.example.linshiwei.colorpicker.datasource.ColorCollectionSourceManager;
import com.example.linshiwei.colorpicker.datasource.DataCallBack;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Activity mContext;
    private SwipeMenuRecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<CollectedColor> mColors;

    private RecyclerAdapter mRecycleViewAdapter;
    private ColorCollectionDbHelper mDbHelper;
    private ColorCollectionSourceManager manager = ColorCollectionSourceManager.INSTANCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new ListViewDecoration());

        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);

        mColors = new ArrayList<>();

        mRecycleViewAdapter = new RecyclerAdapter(mColors);
        mRecycleViewAdapter.setOnItemClickListener(mOnItemClickListener);
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

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = DensityUtility.dip2px(mContext,66);

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
//            {
//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                        .setImage(R.mipmap.ic_action_add) // 图标。
//                        .setWidth(width) // 宽度。
//                        .setHeight(height); // 高度。
//                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
//
//                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//
//                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
//            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
//                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

//                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_delete)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。
//
//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setText("添加")
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(mContext,Integer.toString(position),Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         *                        #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。
//
//            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            }

            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                mColors.remove(adapterPosition);
                manager.deleteOneColor(mDbHelper, mColors.get(adapterPosition), new FinishCallBack() {
                    @Override
                    public void onFinish(Boolean success) {
                        if (success){
                            Log.i(TAG,"Delete success");
                        }else{
                            Log.i(TAG,"Delete fail");
                        }

                    }
                });
                mRecycleViewAdapter.notifyItemRemoved(adapterPosition);
            }
        }
    };

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
            View v =mRecyclerView.getChildAt(i).findViewById(R.id.swipe_content).findViewById(R.id.color_information_view);
            if (v instanceof ColorInformationView){
                ((ColorInformationView) v).refreshColor();
            }
        }
    }

}

