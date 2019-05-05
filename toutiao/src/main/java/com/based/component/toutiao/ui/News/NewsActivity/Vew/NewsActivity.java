package com.based.component.toutiao.ui.News.NewsActivity.Vew;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.based.component.toutiao.R;
import com.based.component.toutiao.adapter.NewsChannelFragmentAdapter;
import com.based.component.toutiao.entity.Channel;
import com.based.component.toutiao.ui.News.NewsActivity.presenter.NewsPresenter;
import com.based.component.toutiao.ui.News.NewsFragment.NewsFragment;
import com.library.base.mvp.BaseActivity;
import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.List;

import butterknife.InjectView;

public class NewsActivity extends BaseActivity implements INewsActivity{


    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.tab_channel)
    RecyclerTabLayout tabChannel;
    @InjectView(R.id.iv_operation)
    ImageView ivOperation;
    @InjectView(R.id.vp_content)
    ViewPager vpContent;

    private NewsChannelFragmentAdapter adapter;
    private NewsPresenter newsPresenter;

    @Override
    public int getContentView() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        newsPresenter=new NewsPresenter(this);
        newsPresenter.initChannelData();
    }


    @Override
    public void initChannelData(List<Channel> mSelectedChannels,List<NewsFragment> newsFragments) {
        adapter=new NewsChannelFragmentAdapter(getSupportFragmentManager(),newsFragments,mSelectedChannels);
        vpContent.setAdapter(adapter);
        tabChannel.setUpWithViewPager(vpContent);
    }
}
