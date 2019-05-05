package com.based.component.toutiao.ui.News.NewsActivity.model;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.utils.TextUtils;
import com.based.component.toutiao.R;
import com.based.component.toutiao.constant.Constant;
import com.based.component.toutiao.entity.Channel;
import com.based.component.toutiao.ui.News.NewsFragment.NewsFragment;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/30.
 */

public class NewsModel implements INewsModel{

    @Override
    public List<Channel> initChannelData(Context context) {
        List<Channel> mSelectedChannels=new ArrayList<>();
        String selectedChannelJson = SPUtils.getInstance().getString(Constant.SELECTED_CHANNEL_JSON,"");
        String unSelectedChannelJson = SPUtils.getInstance().getString(Constant.UNSELECTED_CHANNEL_JSON,"");
        if(TextUtils.isEmpty(selectedChannelJson) ||TextUtils.isEmpty(unSelectedChannelJson)){
            //没有添加过数据
            String[] channels= context.getResources().getStringArray(R.array.channel);
            String[] channelCodes=context.getResources().getStringArray(R.array.channel_code);
            for (int i=0;i<channelCodes.length;i++){
                String title=channels[i];
                String channelCode=channelCodes[i];
                mSelectedChannels.add(new Channel(title,channelCode));
            }
            selectedChannelJson=new Gson().toJson(mSelectedChannels);
            SPUtils.getInstance().put(Constant.SELECTED_CHANNEL_JSON,selectedChannelJson);
        }else {
            List<Channel> selectedChannel=new Gson().fromJson(selectedChannelJson,new TypeToken<List<Channel>>(){}.getType());
            List<Channel> unSelectedChannel=new Gson().fromJson(unSelectedChannelJson,new TypeToken<List<Channel>>(){}.getType());
            mSelectedChannels.addAll(selectedChannel);
        }

        return mSelectedChannels;
    }

    @Override
    public List<NewsFragment> initNewsFragmentData(Context context,List<Channel> mSelectedChannels) {
        List<NewsFragment> newsFragments=new ArrayList<>();
        String[] channelCodes=context.getResources().getStringArray(R.array.channel_code);
        for (Channel channel:mSelectedChannels){
            NewsFragment newsFragment=new NewsFragment();
            Bundle bundle=new Bundle();
            bundle.putString(Constant.CHANNEL_CODE,channel.getChannelCode());
            bundle.putBoolean(Constant.IS_VIDEO_LIST,channel.getChannelCode().endsWith(channelCodes[1]));
            newsFragment.setArguments(bundle);
            newsFragments.add(newsFragment);
        }
        return newsFragments;
    }


}
