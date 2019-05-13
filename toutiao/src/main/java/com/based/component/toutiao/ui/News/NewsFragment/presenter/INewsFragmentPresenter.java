package com.based.component.toutiao.ui.News.NewsFragment.presenter;

/**
 * Created by Administrator on 2019/5/13.
 */

public interface INewsFragmentPresenter {
    /**
     * 获取新闻列表数据
     * @param channelCode
     */
    void getNewsList(String channelCode);
}
