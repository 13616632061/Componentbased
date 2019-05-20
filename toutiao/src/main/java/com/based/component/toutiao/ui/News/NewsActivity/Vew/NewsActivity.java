package com.based.component.toutiao.ui.News.NewsActivity.Vew;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.based.component.toutiao.R;
import com.based.component.toutiao.adapter.NewsChannelFragmentAdapter;
import com.based.component.toutiao.entity.Channel;
import com.based.component.toutiao.ui.News.NewsActivity.presenter.NewsPresenter;
import com.based.component.toutiao.ui.News.NewsFragment.view.NewsFragment;
import com.library.base.mvp.BaseActivity;
import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.List;

import butterknife.InjectView;
import cn.jzvd.Jzvd;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class NewsActivity extends BaseActivity implements INewsActivity {


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
        newsPresenter = new NewsPresenter(this);
        newsPresenter.initPermission();
        newsPresenter.initChannelData();
    }

    /**
     * 初始化权限
     */
    @Override
    public void initPermission() {
        PermissionGen.with(this)
                .addRequestCode(100)//请求码
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,//读写权限
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }

    @Override
    public void initChannelData(List<Channel> mSelectedChannels, List<NewsFragment> newsFragments) {
        adapter = new NewsChannelFragmentAdapter(getSupportFragmentManager(), newsFragments, mSelectedChannels);
        vpContent.setAdapter(adapter);
        tabChannel.setUpWithViewPager(vpContent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void initListener() {
        super.initListener();
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //当页签切换的时候，如果有播放视频，则释放资源
                Jzvd.resetAllVideos();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 权限成功
     */
    @PermissionSuccess(requestCode = 100)
    public void requestPermissionsSuccess() {
        Log.i("权限", "Success");
    }

    /**
     * 权限失败
     */
    @PermissionFail(requestCode = 100)
    public void requestPermissionsFail() {
        Log.i("权限", "Fail");
    }


    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.resetAllVideos();
    }
}
