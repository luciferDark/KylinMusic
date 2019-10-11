package com.kylin.ui.Test.HotFix;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;

import com.kylin.R;
import com.kylin.hotfix.HotFixEngine;
import com.kylin.libs.utils.LogUtils;

import java.io.File;

/**
 * Created by kylin on 2018/6/18.
 */

public class TestHotFixActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kylin_ui_test_hotfix_activity_layout);
        
        initFix();
    }

    private void initFix() {
        String optimizeDir = this.getApplication().getFilesDir().getAbsolutePath()
                + File.separator + HotFixEngine.DIR_OPTIMIZE_DEX ;
        File file = new File(optimizeDir);
        if (file.exists() && file.listFiles().length > 0){
            HotFixEngine.loadFixedDexs(TestHotFixActivity.this.getApplication(),file);
        }
    }

    public void fix(View view) {
        LogUtils.debug("click fix");
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        LogUtils.debug("click fix:" + filePath);

//        filePath = "/sdcard/fixDex";
        HotFixEngine.loadFixedDexs(TestHotFixActivity.this.getApplication(), Environment.getExternalStorageDirectory());
    }

    public void show(View view) {
        LogUtils.debug("click show");
        HotFixBug.bug(this);
    }
}
