package com.based.component.toutiao.ui.News.Vew;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.based.component.toutiao.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.library.base.mvp.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsActivity extends BaseActivity implements INewsActivity{


    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.tab_channel)
    SlidingTabLayout tabChannel;
    @InjectView(R.id.iv_operation)
    ImageView ivOperation;
    @InjectView(R.id.vp_content)
    ViewPager vpContent;

    @Override
    public int getContentView() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
    }


    @Override
    public void initChannelData() {

    }
}
