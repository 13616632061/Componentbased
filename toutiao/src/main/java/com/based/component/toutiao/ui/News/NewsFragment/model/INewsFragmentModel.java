package com.based.component.toutiao.ui.News.NewsFragment.model;

import com.based.component.toutiao.entity.News;

import java.util.List;

/**
 * Created by Administrator on 2019/5/8.
 */

public interface INewsFragmentModel {
    /**
     * 初始化News数据
     *
     * @return
     */
    List<News> getNewsList(String channelCode);
}
