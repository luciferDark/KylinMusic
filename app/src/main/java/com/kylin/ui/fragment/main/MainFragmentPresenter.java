package com.kylin.ui.fragment.main;

import com.kylin.api.Api;
import com.kylin.api.services.MainBottomMusicListService;
import com.kylin.libs.mvpbase.BasePresenter;
import com.kylin.libs.utils.LogUtils;
import com.kylin.models.MusicList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 主界面碎片页Presenter
 *
 * @author Kylin
 * @date 2018/4/4.
 */
public class MainFragmentPresenter extends BasePresenter<MainFragmentModel, MainFragment> {
    private MusicList result;

    @Override
    public String onChangeBaseUrlCall(String key) {
        return Api.URL_BOTTOM_LIST;
    }

    /**
     * 初始化底部播放器列表
     * @param fg
     */
    public void initBottomPlayMusicList(final MainFragment fg) {
        MainBottomMusicListService mBottomMusicListService = getApi(fg.getContext(), MainBottomMusicListService.class);
        mCompositeSubscription.add(
                mBottomMusicListService.query_MainBottom_MusicPlayList("周杰伦")
                // 将被观察者切换到子线程
                .subscribeOn(Schedulers.io())
                // 将观察者切换到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MusicList>() {
                    @Override
                    public void onCompleted() {
                        if (result == null) {
                            return;
                        }
                        LogUtils.debug("initBottomPlayMusicList onCompleted result  size " + result.getMusicList().size());
                        for (int i = 0; i < result.getMusicList().size(); i++) {
                            LogUtils.debug("initBottomPlayMusicList onCompleted result   " + i + "：" + result.getMusicList().get(i).toString());
                        }
                        fg.setAdapter(result.getMusicList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.debug("initBottomPlayMusicList onError:" + e.getMessage());
                    }

                    @Override
                    public void onNext(MusicList musicList) {
                        LogUtils.debug("initBottomPlayMusicList onNext");
                        result = musicList;
                    }
                }));
    }

}
