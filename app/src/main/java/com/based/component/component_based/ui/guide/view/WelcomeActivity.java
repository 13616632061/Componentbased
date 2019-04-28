package com.based.component.component_based.ui.guide.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.based.component.component_based.Constant.RouterMapping;
import com.based.component.component_based.Interceptor.FirstOpenNavigationCallbackImpl;
import com.based.component.component_based.R;


import com.library.base.mvp.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.TimerTask;

@Route(path = RouterMapping.ROUTER_ACTIVITY_WELCOME)
public class WelcomeActivity extends BaseActivity {

    private static final int PERMISSIONS_CODE = 124;
    private static final String[] PERMISSONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private WeakReference<WelcomeActivity> activityWeakReference;
    private Handler handler;

    private static class handler extends Handler {
        private WelcomeActivity welcomeActivity;

        public handler(WeakReference<WelcomeActivity> ref) {
            this.welcomeActivity = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (welcomeActivity != null) {
                        welcomeActivity.finish();
                    }
                    break;
            }
        }
    }

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
        //按home切换到后台，点击launcher的图标切换到前台，页面退出回到启动页
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        ARouter.getInstance().build(RouterMapping.ROUTER_ACTIVITY_GUIDE).navigation(this, new FirstOpenNavigationCallbackImpl());
        //弱引用handler 防止内存泄漏
        activityWeakReference = new WeakReference<WelcomeActivity>(this);
        handler = new handler(activityWeakReference);
        handler.sendEmptyMessageDelayed(0, 500);
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
