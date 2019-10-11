package com.kylin.api.services;

import com.kylin.models.MusicList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * api-底部播放列表
 * @author  by Kylin
 * @date 2018/4/8.
 */
public interface MainBottomMusicListService{
    @Headers({"url_name:user"})
    @GET("bottom/playList")
    Observable<MusicList> query_MainBottom_MusicPlayList(@Query("listName") String listName);
}
