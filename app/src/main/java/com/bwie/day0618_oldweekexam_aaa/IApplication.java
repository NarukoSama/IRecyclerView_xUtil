package com.bwie.day0618_oldweekexam_aaa;

import android.app.Application;

import org.xutils.x;

/**
 * Created by
 * Chenxin
 * on 2017/6/18.
 */

public class IApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }
}
