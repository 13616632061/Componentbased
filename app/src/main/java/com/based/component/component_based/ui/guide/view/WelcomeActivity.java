package com.based.component.component_based.ui.guide.view;

import android.Manifest;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.apkfuns.logutils.LogUtils;
import com.based.component.component_based.Constant.RouterMapping;
import com.based.component.component_based.Interceptor.FirstOpenNavigationCallbackImpl;
import com.based.component.component_based.R;

import com.based.component.component_based.ui.guide.view.SplashPagerActivity.SplashPagerActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.library.base.mvp.BaseActivity;
import com.library.constant.Constant;

@Route(path =RouterMapping.ROUTER_ACTIVITY_WELCOME)
public class WelcomeActivity extends BaseActivity {

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
//            ActivityUtils.startActivity(SplashPagerActivity.class);
            LogUtils.i(RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER);
            LogUtils.i(RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER.startsWith("/"));
            LogUtils.i(RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER.indexOf("/"));
            LogUtils.i(RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER.lastIndexOf("/"));
            routerNavigation(RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER);
        }else {
            routerNavigation(RouterMapping.ROUTER_ACTIVITY_GUIDE);
        }
//        ARouter.getInstance().build(RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER).navigation(this,new FirstOpenNavigationCallbackImpl());
//        finish();
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
