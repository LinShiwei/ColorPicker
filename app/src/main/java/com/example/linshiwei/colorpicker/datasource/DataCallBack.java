package com.example.linshiwei.colorpicker.datasource;

import java.util.ArrayList;

/**
 * Created by linshiwei on 2017/6/14.
 */

public interface DataCallBack {

    void onGetData(Boolean success, ArrayList<CollectedColor> colors);

}

