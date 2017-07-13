package com.example.linshiwei.colorpicker.globalshared;

import android.app.Application;

/**
 * Created by linshiwei on 2017/7/13.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }
    }

    public static App getInstance() {
        return instance;
    }
}
