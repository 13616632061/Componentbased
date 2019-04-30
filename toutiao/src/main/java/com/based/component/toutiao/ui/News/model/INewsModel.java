package com.based.component.toutiao.ui.News.model;

import android.content.Context;

import com.based.component.toutiao.entity.Channel;

import java.util.List;

/**
 * Created by Administrator on 2019/4/30.
 */

public interface INewsModel {
    /**
     * 初始化分类数据
     */
    void initChannelData(Context context);

    /**
     * 获取选择的数据
     * @return
     */
    List<Channel> getSelectedChannels();

    /**
     * 获取未选择的数据
     * @return
     */
    List<Channel> getUnSelectedChannels();
}
