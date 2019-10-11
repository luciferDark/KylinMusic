package com.kylin.ui.Test.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.kylin.libs.utils.LogUtils;

/**
 * Created by kylin on 2018/6/10.
 */

public class TestService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.debug("TestService onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.debug("TestService onBind");
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        LogUtils.debug("TestService onStartCommand + " + bundle.get("First"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.debug("TestService onDestroy");
    }

    public String getServiceThreadName(){
        return Thread.currentThread().getName();
    }

    public class MyBinder extends Binder {
        public TestService getService(){
            return TestService.this;
        }
    }
    //通过binder实现了 调用者（client）与 service之间的通信
    private MyBinder binder = new MyBinder();
}
