package com.based.component.toutiao.api;


import com.based.component.toutiao.entity.VideoModel;
import com.based.component.toutiao.entity.response.NewsResponse;
import com.based.component.toutiao.entity.response.ResultResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2019/5/9.
 * 网络请求的service
 */

public interface ApiService {

    /**
     * 获取新闻列表
     *
     * @param category
     * @param lastTime
     * @param currentTime
     * @return
     */
    @GET(ApiConstant.GET_ARTICLE_LIST)
    Observable<NewsResponse> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);

    /**
     * 获取视频页的html代码
     */
    @GET
    Observable<String> getVideoHtml(@Url String url);

    /**
     * 获取视频数据json
     *
     * @param url
     * @return
     */
    @GET
    Observable<ResultResponse<VideoModel>> getVideoData(@Url String url);
}
