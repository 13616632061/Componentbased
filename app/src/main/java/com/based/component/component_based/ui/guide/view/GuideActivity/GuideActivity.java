package com.based.component.component_based.ui.guide.view.GuideActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.apkfuns.logutils.LogUtils;
import com.based.component.component_based.Constant.RouterMapping;
import com.based.component.component_based.R;
import com.based.component.component_based.ui.guide.presenter.GuidePresenter.GuidePresenter;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.library.base.mvp.BaseActivity;
import com.ns.yc.yccountdownviewlib.CountDownView;

import butterknife.ButterKnife;
import butterknife.InjectView;

@Route(path = RouterMapping.ROUTER_ACTIVITY_GUIDE)
public class GuideActivity extends BaseActivity implements IGuideView{

    @InjectView(R.id.iv_start_ad)
    ImageView ivStartAd;
    @InjectView(R.id.cdv_time)
    CountDownView cdvTime;

    private GuidePresenter guidePresenter;


    @Override
    public int getContentView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this,0);
        guidePresenter=new GuidePresenter(this);
        guidePresenter.showAdPicture();
        guidePresenter.initCountDownView();
    }

    @Override
    public void showAdPicture(String adPath) {
        Glide.with(this).load(adPath).into(ivStartAd);
    }

    @Override
    public void initCountDownView() {
        cdvTime.setTime(3);
        cdvTime.start();
        cdvTime.setOnLoadingFinishListener(new CountDownView.OnLoadingFinishListener() {
            @Override
            public void finish() {
                routerNavigation(RouterMapping.ROUTER_ACTIVITY_MAIN);
                GuideActivity.this.finish();
            }
        });
    }
}
