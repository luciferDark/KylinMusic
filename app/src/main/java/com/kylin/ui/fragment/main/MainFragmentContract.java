package com.kylin.ui.fragment.main;

import com.kylin.libs.mvpbase.BaseModel;
import com.kylin.libs.mvpbase.BaseView;
import com.kylin.models.MusicModel;

import java.util.List;

/**
 * 主界面碎片三合一
 *
 * @author Kylin
 * @date 2018/4/4.
 */
public class MainFragmentContract {
    /**
     * 主界面碎片页 view接口
     */
    public interface MainFragmentView extends BaseView {
        void setAdapter(List<MusicModel> models);
    }

    /**
     * 主界面碎片页 数据model接口
     */
    public interface MainFragmentModel extends BaseModel {
    }
}
