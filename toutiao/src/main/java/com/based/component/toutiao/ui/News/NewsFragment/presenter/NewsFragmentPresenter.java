package com.based.component.toutiao.ui.News.NewsFragment.presenter;

import com.apkfuns.logutils.LogUtils;
import com.based.component.toutiao.dao.NewsRecordHelper;
import com.based.component.toutiao.entity.News;
import com.based.component.toutiao.entity.NewsData;
import com.based.component.toutiao.entity.NewsRecord;
import com.based.component.toutiao.entity.response.NewsResponse;
import com.based.component.toutiao.ui.News.NewsFragment.model.NewsFragmentModel;
import com.based.component.toutiao.ui.News.NewsFragment.view.INewsFragmentView;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.library.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2019/5/13.
 */

public class NewsFragmentPresenter extends BasePresenter<INewsFragmentView> implements INewsFragmentPresenter {

    private long lastTime;
    private List<News> newsList;
    private NewsRecord newsRecord;

    private NewsFragmentModel newsFragmentModel;

    public NewsFragmentPresenter(INewsFragmentView mView) {
        super(mView);
        newsFragmentModel = new NewsFragmentModel();
    }

    @Override
    public void getNewsList(String channelCode) {
        newsList = new ArrayList<>();
        newsRecord = NewsRecordHelper.getLastNewsRecord(channelCode);
        if (newsRecord == null) {
            //找不到记录，拉取网络数据
            newsRecord = new NewsRecord();
            //读取对应频道下最后一次刷新的时间戳
            lastTime = SPUtils.getInstance().getLong(channelCode, 0);
            if (lastTime == 0) {
                //如果为空，则是从来没有刷新过，使用当前时间戳
                lastTime = System.currentTimeMillis() / 1000;
            }
            getSubscriptionNewsList(channelCode);
            return;
        }
        newsList = new Gson().fromJson(newsRecord.getJson(), new TypeToken<List<News>>() {
        }.getType());
        mView.getNewsList(newsList);
    }

    /**
     * 网络获取NewsList
     * @param channelCode
     */
    public void getSubscriptionNewsList(String channelCode) {
        LogUtils.e(newsFragmentModel.getNewsList(channelCode, lastTime, System.currentTimeMillis() / 1000));
        addSubscription(newsFragmentModel.getNewsList(channelCode, lastTime, System.currentTimeMillis() / 1000), new Subscriber<NewsResponse>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(e.getLocalizedMessage());
            }

            @Override
            public void onNext(NewsResponse response) {
                lastTime = System.currentTimeMillis() / 1000;
                SPUtils.getInstance().put(channelCode, lastTime);

                List<NewsData> newsData = response.data;
                if (newsData != null) {
                    for (NewsData data : newsData) {
                        News news = new Gson().fromJson(data.content, News.class);
                        newsList.add(news);
                    }
                }
                LogUtils.e(newsList);
                mView.getNewsList(newsList);
            }
        });
    }
}
