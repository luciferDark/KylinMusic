package com.kylin.ui.Test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kylin.R;
import com.kylin.libs.utils.LogUtils;
import com.kylin.ui.Test.HotFix.TestHotFixActivity;
import com.kylin.ui.Test.LockView.TestActivity;
import com.kylin.ui.Test.Service.TestServiceView;
import com.kylin.ui.guid.GuidActivity;

/**
 * Created by kylin on 2018/6/18.
 */

public class TestJumpActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kylin_ui_test_jump_activity_layout);
    }

    public void splash(View view) {
        LogUtils.debug("click splash");
        startActivity(GuidActivity.class);
    }

    public void lockView(View view) {
        LogUtils.debug("click lockView");
        startActivity(TestActivity.class);
    }
    public void serverTest(View view) {
        LogUtils.debug("click serverTest");
        startActivity(TestServiceView.class);
    }
    public void hotfix(View view) {
        LogUtils.debug("click serverTest");
        startActivity(TestHotFixActivity.class);
    }


    public void startActivity(Class clazz){
        Intent intent = new Intent();
        intent.setClass(TestJumpActivity.this, clazz);
        startActivity(intent);
    }
}
