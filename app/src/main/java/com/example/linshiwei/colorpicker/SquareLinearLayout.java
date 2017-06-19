package com.example.linshiwei.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by linshiwei on 2017/6/19.
 */

public class SquareLinearLayout extends LinearLayout {

    public SquareLinearLayout(Context context) {
        super(context);
    }
    public SquareLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SquareLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        //重写此方法后默认调用父类的onMeasure方法,分别将宽度测量空间与高度测量空间传入
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        int size = width > height ? height : width;
//        setMeasuredDimension(size, size);

    }

}
