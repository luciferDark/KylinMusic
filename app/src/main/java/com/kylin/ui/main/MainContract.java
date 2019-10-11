package com.kylin.ui.main;

import com.kylin.libs.mvpbase.BaseModel;
import com.kylin.libs.mvpbase.BaseView;

/**
 * 主界面三合一
 *
 * @author Kylin
 * @date 2018/4/1.
 */

public class MainContract {
    /**
     * 主界面 view接口
     */
    public interface MainView extends BaseView {

    }

    /**
     * 主界面 数据model接口
     */
    public interface MainModel extends BaseModel {

    }
}
