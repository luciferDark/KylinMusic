package com.kylin.libs.crashlog;

import android.util.Log;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *  crash 堆栈信息收集器
 * @author  Kylin
 * @date 2018/3/30.
 */

public class JavaUnCatchExptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final Writer writer = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(writer);
        Throwable cause = e;
        if (null == cause) {
            return;
        }
        cause.printStackTrace(printWriter);
        cause = cause.getCause();
        final String stackTraceStr = writer.toString();
        final String threadInfoStr = JavaThreadCrashLogColloector.collect(t);
        Log.d("kylin", "stack info:\n" + stackTraceStr + "\n"
                + "thread info:\n" + threadInfoStr);
        printWriter.close();
    }
}
