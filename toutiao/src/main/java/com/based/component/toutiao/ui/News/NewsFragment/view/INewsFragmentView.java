package com.based.component.toutiao.ui.News.NewsFragment.view;

import com.based.component.toutiao.entity.News;

import java.util.List;

/**
 * Created by Administrator on 2019/5/6.
 */

public interface INewsFragmentView {

    void getNewsList(List<News> newsList);

    void listener();
}
