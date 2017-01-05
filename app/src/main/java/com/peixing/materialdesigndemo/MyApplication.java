package com.peixing.materialdesigndemo;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;

/**
 * Created by peixing on 2017/1/4.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);
        OkGo.getInstance()
                .setConnectTimeout(5000)
                .setReadTimeOut(5000)
                .setWriteTimeOut(5000)
                .setCacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(5);
    }
}
