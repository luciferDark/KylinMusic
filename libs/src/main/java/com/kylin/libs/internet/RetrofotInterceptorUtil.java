package com.kylin.libs.internet;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by Kylin on 2018/4/8.
 */

public class RetrofotInterceptorUtil {
    public static String TAG = "----";
    //日志拦截器
//    public static HttpLoggingInterceptor gInterceptor
//
//    LogInterceptor() {
//        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.w(TAG, "log: " + message);
//            }
//        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
//    }

    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request mRequest = chain.request();
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等,PS我会在下一篇文章总写拦截token方法
                Http:
                return chain.proceed(mRequest);
            }
        };

    }
}
