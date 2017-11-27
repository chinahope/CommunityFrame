package com.example.ggs.reddot;

import android.app.Application;
import android.content.Context;

/**
 * Created by fengjian on 2017/9/13.
 */

public class CommonApplication extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PermissionHelper.setMode(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
