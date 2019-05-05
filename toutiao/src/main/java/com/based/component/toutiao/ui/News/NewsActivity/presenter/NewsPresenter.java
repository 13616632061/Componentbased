package com.based.component.toutiao.ui.News.NewsActivity.presenter;

import com.based.component.toutiao.entity.Channel;
import com.based.component.toutiao.ui.News.NewsActivity.Vew.NewsActivity;
import com.based.component.toutiao.ui.News.NewsActivity.model.NewsModel;
import com.based.component.toutiao.ui.News.NewsFragment.NewsFragment;

import java.util.List;

/**
 * Created by Administrator on 2019/5/5.
 */

public class NewsPresenter implements INewsPresenter {

    private NewsActivity newsActivity;
    private NewsModel newsModel;

    public NewsPresenter(NewsActivity newsActivity) {
        this.newsActivity = newsActivity;
        newsModel = new NewsModel();
    }

    @Override
    public void initChannelData() {
        List<Channel> mSelectedChannels = newsModel.initChannelData(newsActivity);
        List<NewsFragment> newsFragments=newsModel.initNewsFragmentData(newsActivity,mSelectedChannels);
        newsActivity.initChannelData(mSelectedChannels,newsFragments);
    }
}
