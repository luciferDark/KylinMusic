package com.kylin.ui.Test.Service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.kylin.R;
import com.kylin.libs.utils.LogUtils;

/**
 * Created by kylin on 2018/6/10.
 */

public class TestServiceView extends Activity {
    Button button, button1, button2, button3;
    TestService testService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.debug("onServiceConnected:");
            TestService.MyBinder binder = (TestService.MyBinder) service;
            testService = binder.getService();
            LogUtils.debug("service thread name:" + testService.getServiceThreadName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.debug("onServiceConnected:" + name);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kylin_ui_test_service_activity_layout);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TestServiceView.this, TestService.class);
                Bundle bundle = new Bundle();
                bundle.putString("First", "start service 1");
                intent.putExtras(bundle);
                LogUtils.debug("startService");
                startService(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TestServiceView.this, TestService.class);
                Bundle bundle = new Bundle();
                bundle.putString("First", "stop service 1");
                intent.putExtras(bundle);
                LogUtils.debug("stopService");
                stopService(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TestServiceView.this, TestService.class);
                bindService(intent, serviceConnection , BIND_AUTO_CREATE);
                LogUtils.debug("bindService");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
                LogUtils.debug("unbindService");
            }
        });
    }
}
