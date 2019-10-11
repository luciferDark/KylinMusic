package com.kylin.libs.app.applications;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.kylin.libs.crashlog.JavaUnCatchExptionHandler;


/**
 * Appcation类 修改默认版本crashlog日志输出
 * @author  Kylin
 * @date 2018/3/30.
 */

public class KylinApplication extends Application {
    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this.getApplicationContext();
        initCrashLog();
    }

    private void initCrashLog() {
        Log.d("kylin", "initCrashLog");
        Thread.setDefaultUncaughtExceptionHandler(new JavaUnCatchExptionHandler());
    }
}
