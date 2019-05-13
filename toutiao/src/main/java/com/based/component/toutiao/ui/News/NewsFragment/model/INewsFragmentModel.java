package com.based.component.toutiao.ui.News.NewsFragment.model;

import rx.Observable;

/**
 * Created by Administrator on 2019/5/8.
 */

public interface INewsFragmentModel {
    /**
     * 初始化News数据
     *
     * @return
     */
    Observable getNewsList(String category,long lastTime,long currentTime);
}
