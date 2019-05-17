package com.based.component.toutiao.ui.News.NewsFragment.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.based.component.toutiao.R;
import com.based.component.toutiao.adapter.VideoListAdapter;
import com.based.component.toutiao.constant.Constant;
import com.based.component.toutiao.entity.News;
import com.based.component.toutiao.ui.News.NewsFragment.presenter.NewsFragmentPresenter;
import com.blankj.utilcode.util.NetworkUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.library.base.mvp.BaseFragment;
import com.library.weight.TipView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

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
    RecyclerView list;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;
    @InjectView(R.id.refresh_layout)
    BGARefreshLayout refreshLayout;

    private static final String TAG = BaseQuickAdapter.class.getSimpleName();
    private String mChannelCode;//频道code
    private boolean isVideoList;//是否是视频模块
    LinearLayoutManager linearLayoutManager;

    private boolean isRecommendChannel;//是否是推荐频道
    private BaseQuickAdapter mNewsAdapter;
    private List<News> mNewsList = new ArrayList<>();
    private NewsFragmentPresenter mNewsFragmentPresenter;


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
        mNewsFragmentPresenter = new NewsFragmentPresenter(this);
        mChannelCode = getArguments().getString(Constant.CHANNEL_CODE);
        isVideoList = getArguments().getBoolean(Constant.IS_VIDEO_LIST, false);

        String[] channelCodes = getResources().getStringArray(R.array.channel_code);
        isRecommendChannel = mChannelCode.equals(channelCodes[0]);

        if (isVideoList) {
            mNewsAdapter = new VideoListAdapter(mActivity, R.layout.item_video_list, mNewsList);
            list.setLayoutManager(new LinearLayoutManager(mActivity));
            list.setAdapter(mNewsAdapter);
        } else {

        }

    }

    @Override
    protected void loadData() {
        mNewsFragmentPresenter.getNewsList(mChannelCode);
    }

    /**
     * 加载最新数据
     *
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (!NetworkUtils.isConnected()) {
            //网络不可用弹出提示
            tipView.show();
            if (refreshLayout.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                refreshLayout.endRefreshing();
            }
            return;
        }
        mNewsFragmentPresenter.getNewsList(mChannelCode);
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
        refreshLayout.endRefreshing();// 加载完毕后在 UI 线程结束下拉刷新
        mNewsList.addAll(newsList);
        mNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void listener() {
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {

                        //如果滑出去了上面和下面就是否，和今日头条一样
                        //是否全屏
                        if (!GSYVideoManager.isFullState(mActivity)) {
                            GSYVideoManager.releaseAllVideos();
                            mNewsAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
