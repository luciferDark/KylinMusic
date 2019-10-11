package com.kylin.libs.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * log打印日志封装
 *
 * @author Kylin
 * @date 2018/3/30.
 */
public class LogUtils {
    public static final String TAG = "kylin";

    private static boolean isDebug = true;

    /**
     * for error log
     *
     * @param msg
     */
    public static void error(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    /**
     * for warming log
     *
     * @param msg
     */
    public static void warn(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    /**
     * for info log
     *
     * @param msg
     */
    public static void info(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    /**
     * for debug log
     *
     * @param msg
     */
    public static void debug(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    /**
     * for verbose log
     *
     * @param msg
     */
    public static void verbose(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void setDebug(boolean isDebug) {
        LogUtils.isDebug = isDebug;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    /**
     * 点击Log跳转到指定源码位置
     *
     * @param tag
     * @param msg
     */
    public static void showLog(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(msg)) {
            if (TextUtils.isEmpty(tag)) {
                tag = TAG;
            }
            StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
            int currentIndex = -1;
            for (int i = 0; i < stackTraceElement.length; i++) {
                if (stackTraceElement[i].getMethodName().compareTo("showLog") == 0) {
                    currentIndex = i + 1;
                    break;
                }
            }
            if (currentIndex >= 0) {
                String fullClassName = stackTraceElement[currentIndex].getClassName();
                String className = fullClassName.substring(fullClassName
                        .lastIndexOf(".") + 1);
                String methodName = stackTraceElement[currentIndex].getMethodName();
                String lineNumber = String
                        .valueOf(stackTraceElement[currentIndex].getLineNumber());

                Log.i(tag, msg + "\n  ---->at " + className + "." + methodName + "("
                        + className + ".java:" + lineNumber + ")");
            } else {
                Log.i(tag, msg);
            }
        }
    }
}
