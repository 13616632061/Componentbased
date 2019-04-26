package com.based.component.component_based.ui.guide.presenter.SplashPagerPresenter;

import com.based.component.component_based.ui.guide.model.SplashPagerModel.SplashPagerModel;
import com.based.component.component_based.ui.guide.view.SplashPagerActivity.SplashPagerActivity;
import com.blankj.utilcode.util.LogUtils;
import com.library.base.mvp.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2019/4/26.
 */

public class SplashPagerPersenter extends BasePresenter<SplashPagerActivity> implements ISplashPagerPersenter {
    private SplashPagerModel splashPagerModel;
    private SplashPagerActivity splashPagerActivity;


    public SplashPagerPersenter(SplashPagerActivity mView) {
        super(mView);
        splashPagerActivity=mView;
        splashPagerModel=new SplashPagerModel();
    }

    /**
     * 引导图
     */
    @Override
    public void setBanner() {
        List<Integer> imagesList=splashPagerModel.getBannerDats(splashPagerActivity);
        splashPagerActivity.setBanner(imagesList);
    }

    /**
     * 去主页
     */
    @Override
    public void goMain() {

    }
}
