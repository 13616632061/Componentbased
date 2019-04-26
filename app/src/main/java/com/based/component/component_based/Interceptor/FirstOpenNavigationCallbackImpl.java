package com.based.component.component_based.Interceptor;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.apkfuns.logutils.LogUtils;
import com.based.component.component_based.Constant.RouterMapping;

/**
 * Created by Administrator on 2019/4/26.
 * 是否第一次启动拦截器的回调
 */

public class FirstOpenNavigationCallbackImpl implements NavigationCallback {


    @Override
    public void onFound(Postcard postcard) {

    }

    @Override
    public void onLost(Postcard postcard) {

    }

    @Override
    public void onArrival(Postcard postcard) {

    }

    @Override
    public void onInterrupt(Postcard postcard) {
        LogUtils.i(postcard.getPath());
        ARouter.getInstance().build(RouterMapping.ROUTER_ACTIVITY_GUIDE).navigation();
    }
}
