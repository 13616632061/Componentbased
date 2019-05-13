package com.based.component.toutiao.ui.News.NewsFragment.model;


import com.based.component.toutiao.api.ApiRetrofit;
import com.based.component.toutiao.api.ApiService;

import rx.Observable;

/**
 * Created by Administrator on 2019/5/8.
 */

public class NewsFragmentModel implements INewsFragmentModel {


    @Override
    public Observable getNewsList(String category, long lastTime, long currentTime) {
        ApiService apiService= ApiRetrofit.getInstance().getApiService();
        return apiService.getNewsList(category,lastTime,currentTime);
    }
}
