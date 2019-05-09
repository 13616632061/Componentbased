package com.based.component.toutiao.ui.News.NewsActivity.model;

import android.content.Context;

import com.based.component.toutiao.entity.Channel;
import com.based.component.toutiao.ui.News.NewsFragment.view.NewsFragment;

import java.util.List;

/**
 * Created by Administrator on 2019/4/30.
 */

public interface INewsModel {
    /**
     * 初始化分类数据
     */
    List<Channel> initChannelData(Context context);

    /**
     * 初始化News分类NewsFragment实例
     * @param context
     * @return
     */
    List<NewsFragment> initNewsFragmentData(Context context,List<Channel> mSelectedChannels);
}


