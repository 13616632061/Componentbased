package com.based.component.toutiao.ui.News.model;

import android.content.Context;

import com.alibaba.android.arouter.utils.TextUtils;
import com.based.component.toutiao.R;
import com.based.component.toutiao.constant.Constant;
import com.based.component.toutiao.entity.Channel;
import com.blankj.utilcode.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/30.
 */

public class NewsModel implements INewsModel{

    private List<Channel> mSelectedChannels=new ArrayList<>();
    @Override
    public void initChannelData(Context context) {
        String selectedChannelJson = SPUtils.getInstance().getString(Constant.SELECTED_CHANNEL_JSON,"");
        String unSelectedChannelJson = SPUtils.getInstance().getString(Constant.UNSELECTED_CHANNEL_JSON,"");
        if(TextUtils.isEmpty(selectedChannelJson) ||TextUtils.isEmpty(unSelectedChannelJson)){
            String[] channels= context.getResources().getStringArray(R.array.channel);
            String[] channelCodes=context.getResources().getStringArray(R.array.channel_code);
            for (int i=0;i<channelCodes.length;i++){
                String title=channels[i];
                String channelCode=channelCodes[i];
                mSelectedChannels.add(new Channel(title,channelCode));
            }
        }else {

        }
    }

    @Override
    public List<Channel> getSelectedChannels() {
        return null;
    }

    @Override
    public List<Channel> getUnSelectedChannels() {
        return null;
    }
}
