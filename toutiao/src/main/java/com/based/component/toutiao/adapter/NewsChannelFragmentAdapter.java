package com.based.component.toutiao.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.based.component.toutiao.entity.Channel;
import com.based.component.toutiao.ui.News.NewsFragment.NewsFragment;

import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */

public class NewsChannelFragmentAdapter extends FragmentPagerAdapter {

    private List<NewsFragment> newsFragments;
    private List<Channel> mSelectedChannels;
    public NewsChannelFragmentAdapter(FragmentManager fm,List<NewsFragment> newsFragments,List<Channel> mSelectedChannels) {
        super(fm);
        this.newsFragments=newsFragments;
        this.mSelectedChannels=mSelectedChannels;
    }

    @Override
    public Fragment getItem(int i) {
        return newsFragments.get(i);
    }

    @Override
    public int getCount() {
        return newsFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mSelectedChannels.get(position).getTitle();
    }
}
