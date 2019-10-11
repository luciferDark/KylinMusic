package com.kylin.libs.interfaces;

import com.kylin.libs.mvpbase.BaseModel;
import com.kylin.libs.mvpbase.BasePresenter;
import com.kylin.libs.mvpbase.BaseView;

/**
 * @author  Kylin
 * @date 2018/3/30.
 */
public interface TestContrack {
    /**
     * 测试用回调
     * @param <E>
     */
    interface TestCallback<E>{
        /**
         * 成功回调
         * @param test
         */
        void onSuccess(E test);

        /**
         * 失败回调
         * @param code
         * @param msg
         */
        void onError(String code, String msg);
    }

    interface TestView extends BaseView {
        /**
         * 测试接口
         */
        void test();
    }

    interface TestModel extends BaseModel {
        /**
         * 测试请求
         * @param num
         * @param callback
         */
        void requestTest(String num, TestCallback callback);
    }

    abstract  class AbstractTestPresenter extends BasePresenter<TestModel, TestView>{
        /**
         * 测试请求抽象方法
         * @param num
         */
        public  abstract void requestTest(String num);
    }
}
