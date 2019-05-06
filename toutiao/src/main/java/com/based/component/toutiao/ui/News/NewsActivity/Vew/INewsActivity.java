package com.based.component.toutiao.ui.News.NewsActivity.Vew;

import com.based.component.toutiao.entity.Channel;
import com.based.component.toutiao.ui.News.NewsFragment.NewsFragment;

import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */

public interface INewsActivity {
    //初始化权限
   void initPermission();
    //初始化分类
    void initChannelData(List<Channel> mSelectedChannels,List<NewsFragment> newsFragments);
}
