package com.based.component.toutiao.ui.News.NewsActivity.presenter;

import com.based.component.toutiao.entity.Channel;

import java.util.List;

/**
 * Created by Administrator on 2019/5/5.
 */

public interface INewsPresenter {
    //初始化权限
    void initPermission();
    /**
     * 初始化News分类数据
     * @return
     */
    void initChannelData();
}
