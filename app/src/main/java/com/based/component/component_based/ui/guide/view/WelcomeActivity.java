package com.based.component.component_based.ui.guide.view;

import android.Manifest;
import android.os.Bundle;

import com.based.component.component_based.R;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.library.base.mvp.BaseActivity;
import com.library.constant.Constant;
import com.ns.yc.yccountdownviewlib.CountDownView;

public class WelcomeActivity extends BaseActivity {

    @butterknife.InjectView(R.id.cdv_time)
    CountDownView cdvTime;

    private static final int PERMISSIONS_CODE=124;
    private static final String[] PERMISSONS={
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //将window的背景图设置为空
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_welcome;
    }


    @Override
    protected void initView() {
//        initPermissions();
        if(SPUtils.getInstance(Constant.sharedPreferencesName).getBoolean(Constant.KEY_FIRST_SPLASH,true)){
            ActivityUtils.startActivity(SplashPagerActivity.class);
        }else {
            ActivityUtils.startActivity(GuideActivity.class);
        }
        finish();
    }

    @Override
    protected void initData() {
    }

    /**
     * 初始化权限
     */
    private void initPermissions() {
        startPermisssionsTask();
    }

    private void startPermisssionsTask() {
    }


}
