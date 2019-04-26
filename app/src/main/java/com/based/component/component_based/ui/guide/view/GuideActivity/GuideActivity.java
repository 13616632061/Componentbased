package com.based.component.component_based.ui.guide.view.GuideActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.based.component.component_based.R;
import com.library.base.mvp.BaseActivity;
import com.ns.yc.yccountdownviewlib.CountDownView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuideActivity extends BaseActivity {

    @InjectView(R.id.iv_start_ad)
    ImageView ivStartAd;
    @InjectView(R.id.cdv_time)
    CountDownView cdvTime;


    @Override
    public int getContentView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {

    }
}
