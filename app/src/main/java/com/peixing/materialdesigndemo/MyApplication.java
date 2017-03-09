package com.peixing.materialdesigndemo;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;

/**
 * Created by peixing on 2017/1/4.
 */

public class MyApplication extends Application {
    private Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        OkGo.init(this);
        application = this;
        OkGo.getInstance()
                .setConnectTimeout(5000)
                .setReadTimeOut(5000)
                .setWriteTimeOut(5000)
                .setCacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(5);
    }

    public Application getApplication() {
        return application;
    }
}
