package com.based.component.component_based.ui.guide.presenter.GuidePresenter;

import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.based.component.component_based.ui.guide.model.GuideModel.GuideModel;
import com.based.component.component_based.ui.guide.view.GuideActivity.GuideActivity;
import com.bumptech.glide.Glide;
import com.library.base.mvp.BasePresenter;

/**
 * Created by Administrator on 2019/4/28.
 */

public class GuidePresenter extends BasePresenter<GuideActivity> implements IGuidePresenter {

    private GuideActivity guideActivity;
    private GuideModel guideModel;

    public GuidePresenter(GuideActivity mView) {
        super(mView);
        guideActivity = mView;
        guideModel = new GuideModel();
    }

    @Override
    public void showAdPicture() {
        String adPath = guideModel.getAdPicture();
        guideActivity.showAdPicture(adPath);
    }

    @Override
    public void initCountDownView() {
        guideActivity.initCountDownView();
    }
}
