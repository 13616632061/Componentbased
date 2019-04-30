package com.based.component.toutiao.entity;

/**
 * Created by Administrator on 2019/4/29.
 */

public class Channel {

    public String title;
    public String channelCode;
    public int itemType;

    public Channel(String title, String channelCode) {
        this.title = title;
        this.channelCode = channelCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
