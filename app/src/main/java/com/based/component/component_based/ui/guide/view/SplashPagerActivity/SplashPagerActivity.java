package com.based.component.component_based.ui.guide.view.SplashPagerActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.based.component.component_based.Constant.RouterMapping;
import com.based.component.component_based.R;
import com.based.component.component_based.ui.guide.presenter.SplashPagerPresenter.SplashPagerPersenter;
import com.based.component.component_based.weight.SplashPagerImageHolderView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.blankj.utilcode.util.BarUtils;
import com.library.base.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

@Route(path = RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER)
public class SplashPagerActivity extends BaseActivity implements ISplashPagerView {


    @InjectView(R.id.splash_banner)
    ConvenientBanner splashBanner;
    @InjectView(R.id.btn_go)
    Button btnGo;

    private SplashPagerPersenter splashPagerPersenter;

    @Override
    public int getContentView() {
        return R.layout.activity_splash_pager;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        splashPagerPersenter = new SplashPagerPersenter(this);
        splashPagerPersenter.setBanner();
    }

    @Override
    public void setBanner(final List<Integer> imagesList) {
        splashBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new SplashPagerImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_splash_pager_image;
            }
        }, imagesList).setPageIndicator(new int[]{R.mipmap.point_focused, R.mipmap.point_unfocused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .setOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    }

                    @Override
                    public void onPageSelected(int index) {
                        if (imagesList.size() == index + 1) {
                            btnGo.setVisibility(View.VISIBLE);
                        } else {
                            btnGo.setVisibility(View.GONE);
                        }
                    }
                });
    }


    @OnClick({R.id.btn_go})
    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go:

                break;
        }
    }


}
