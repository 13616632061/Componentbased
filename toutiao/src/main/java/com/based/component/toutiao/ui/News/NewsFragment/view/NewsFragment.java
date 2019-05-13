package com.based.component.toutiao.ui.News.NewsFragment.view;

import android.view.View;
import android.widget.FrameLayout;

import com.based.component.toutiao.R;
import com.based.component.toutiao.adapter.VideoListAdapter;
import com.based.component.toutiao.constant.Constant;
import com.based.component.toutiao.entity.News;
import com.library.base.mvp.BaseFragment;
import com.library.weight.PowerfulRecyclerView;
import com.library.weight.TipView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2019/5/5.
 */

public class NewsFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, INewsFragmentView {
    @InjectView(R.id.tip_view)
    TipView tipView;
    @InjectView(R.id.list)
    PowerfulRecyclerView list;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;
    @InjectView(R.id.refresh_layout)
    BGARefreshLayout refreshLayout;

    private String mChannelCode;//频道code
    private boolean isVideoList;//是否是视频模块

    private boolean isRecommendChannel;//是否是推荐频道
    private VideoListAdapter mVideoListAdapter;
    private List<News> mNewsList = new ArrayList<>();


    @Override
    protected int setContentViewId() {
        return R.layout.fragment_news;
    }

    /**
     * 初始化view
     *
     * @param rootView
     */
    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        refreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, false);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText(getResources().getString(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(getResources().getString(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText(getResources().getString(R.string.refresh_ing_text));//刷新中的提示文字

        //设置下拉刷新和上拉加载更多的风格
        refreshLayout.setRefreshViewHolder(refreshViewHolder);

    }

    /**
     * 初始化data
     */
    @Override
    public void initData() {
        super.initData();
        mChannelCode = getArguments().getString(Constant.CHANNEL_CODE);
        isVideoList = getArguments().getBoolean(Constant.IS_VIDEO_LIST, false);

        String[] channelCodes = getResources().getStringArray(R.array.channel_code);
        isRecommendChannel = mChannelCode.equals(channelCodes[0]);

        if (isVideoList) {
            mVideoListAdapter=new VideoListAdapter(R.layout.item_video_list,mNewsList);

        } else {

        }
    }

    @Override
    protected void loadData() {

    }

    /**
     * 加载最新数据
     *
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    /**
     * 加载更多数据，或者更具产品需求实现上拉刷新也可以
     *
     * @param refreshLayout
     * @return
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void getNewsList(List<News> newsList) {
        mNewsList.addAll(newsList);
    }
}
