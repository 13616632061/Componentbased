package com.based.component.toutiao.ui.News.NewsFragment.model;

import com.based.component.toutiao.dao.NewsRecordHelper;
import com.based.component.toutiao.entity.News;
import com.based.component.toutiao.entity.NewsRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/8.
 */

public class NewsFragmentModel implements INewsFragmentModel {


    @Override
    public List<News> getNewsList(String channelCode) {
        List<News> newsList = new ArrayList<>();
        NewsRecord newsRecord= NewsRecordHelper.getLastNewsRecord(channelCode);
        if(newsRecord==null){
            //找不到记录，拉取网络数据
            newsRecord=new NewsRecord();

        }
        newsList=new Gson().fromJson(newsRecord.getJson(),new TypeToken<List<News>>() {}.getType());

        return newsList;
    }
}
