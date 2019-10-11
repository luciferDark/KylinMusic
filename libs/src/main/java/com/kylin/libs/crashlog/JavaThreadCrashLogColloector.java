package com.kylin.libs.crashlog;

import android.support.annotation.Nullable;

/**
 *  crash Java线程信息收集器
 * @author  Kylin
 * @date 2018/3/30.
 */

public class JavaThreadCrashLogColloector {
    @Nullable
    public static String collect(@Nullable Thread thread) {
        StringBuilder result = new StringBuilder();
        if (thread == null) {
            return "thread is null, collect no info from thread.";
        }

        result.append("id=").append(thread.getId()).append("\n");
        result.append("name=").append(thread.getName()).append("\n");
        result.append("priority=").append(thread.getPriority()).append("\n");
        if (thread.getThreadGroup() != null) {
            result.append("groupName=").append(thread.getThreadGroup().getName()).append("\n");
        }

        return result.toString();
    }
}
