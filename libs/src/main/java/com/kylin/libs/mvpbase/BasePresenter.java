package com.kylin.libs.mvpbase;

import android.content.Context;

import com.kylin.libs.internet.RetrofitHelper;

import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * MVP-Presenter abs基类
 *
 * @author Kylin
 * @date 2018/3/30.
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {
    public M mModel;
    private WeakReference<V> mViewRef;
    protected RetrofitHelper mRetrofitHelper;
    protected CompositeSubscription mCompositeSubscription;

    public void attachModelView(M model, V view) {
        this.mModel = model;
        this.mViewRef = new WeakReference<V>(view);
        this.mCompositeSubscription = new CompositeSubscription();

    }

    public boolean isAttached() {
        return null != mViewRef && null != mModel;
    }

    public V getView() {
        if (!isAttached()) {
            return null;
        }
        return mViewRef.get();
    }

    public void dettach() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public void onStop() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected abstract String onChangeBaseUrlCall(String key);

    /**
     * 封装Retrofit 的API获取接口
     * @param mContext
     * @param clazz
     * @param <T>
     * @return
     */
    protected  <T> T getApi(Context mContext,  Class<T> clazz) {
        mRetrofitHelper = RetrofitHelper.instance(mContext, new RetrofitHelper.RetrofitInterceptor() {
            @Override
            public String onChangeBaseUrl(String key) {
                String urlChanged = onChangeBaseUrlCall(key);
                return urlChanged;
            }
        });
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        return mRetrofitHelper.createApi(clazz);
    }

}
