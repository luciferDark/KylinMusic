package com.kylin.ui.Test.HotFix;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kylin on 2018/6/18.
 */

public class HotFixBug {

    public static void bug(Context context) {
        int a = 2000;
        int b = 0;
        Toast.makeText(context, "this is a bug" + a / b, Toast.LENGTH_SHORT).show();
    }
}
