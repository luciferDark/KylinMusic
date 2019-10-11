package com.kylin.libs.internet;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.kylin.libs.utils.LogUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit管理类
 *
 * @author by Kylin
 * @date 2018/4/8.
 */
public class RetrofitHelper {
    private Context mContext;
    private OkHttpClient okHttpClient;
    private GsonConverterFactory gsonConverterFactory =
            GsonConverterFactory.create(new GsonBuilder().create());
    private static Retrofit mRetrofit = null;
    private static RetrofitHelper instance = null;
    private String mURL = "https://api.douban.com/";

    private static final String HEAD_URL_NAME = "url_name";
    private static final int CONNECT_TIME_OUT = 5;//链接超时时间
    private RetrofitInterceptor mRetrofitInterceptor;

    public static RetrofitHelper instance(Context mContext,  RetrofitInterceptor mRetrofitInterceptor) {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper(mContext,mRetrofitInterceptor);
                }
            }
        }
        return instance;
    }

    private RetrofitHelper(Context mContext,  RetrofitInterceptor mRetrofitInterceptor) {
        this.mContext = mContext;
        this.mRetrofitInterceptor = mRetrofitInterceptor;
        init();
    }

    private void init() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //获取request
                        Request request = chain.request();
                        if (mRetrofitInterceptor == null){
                            return chain.proceed(request);
                        }
                        //获取Request的构建者Builder
                        Request.Builder builder = request.newBuilder();
                        //获取builder中header中的url_name
                        List<String> headerValues = request.headers(HEAD_URL_NAME);
                        if (headerValues != null && headerValues.size() > 0) {
                            //从header 中移除
                            builder.removeHeader("DomainName");
                            String headerValue = headerValues.get(0);
                            HttpUrl newBaseUrl = null;
                            String urlBack = mRetrofitInterceptor.onChangeBaseUrl(headerValue);
                            newBaseUrl = HttpUrl.parse(urlBack);
                            //从request中获取原有的HttpUrl实例oldHttpUrl
                            HttpUrl oldHttpUrl = request.url();
                            HttpUrl newFullUrl = oldHttpUrl
                                    .newBuilder()
                                    .scheme(newBaseUrl.scheme())
                                    .host(newBaseUrl.host())
                                    .port(newBaseUrl.port())
                                    .build();
                            LogUtils.debug("newFullUrl: " + newFullUrl.toString());
                            return  chain.proceed(builder.url(newFullUrl).build());
                        } else {
                            //不拦截，直接返回请求体
                            return  chain.proceed(request);
                        }
                    }
                }).build();
        LogUtils.debug("mURL: " + mURL);
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(mURL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public interface RetrofitInterceptor{
        String onChangeBaseUrl(String key);
    }

    public static <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
