package com.based.component.component_based.Interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.apkfuns.logutils.LogUtils;
import com.based.component.component_based.Constant.RouterMapping;
import com.blankj.utilcode.util.SPUtils;
import com.library.constant.Constant;

/**
 * Created by Administrator on 2019/4/26.
 * 是否第一次启动拦截器
 * priority 数值越小越先执行
 */
@Interceptor(priority = 7, name = "isFirstOpenApp")
public class FirstOpenAppInterceptor implements IInterceptor {


    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        LogUtils.i(path);
        boolean isFirstOpenApp = SPUtils.getInstance(Constant.sharedPreferencesName).getBoolean(Constant.KEY_FIRST_SPLASH, true);
        if (!isFirstOpenApp) {
            callback.onContinue(postcard);
        } else {
            switch (path) {
                case RouterMapping.ROUTER_ACTIVITY_SPLASHPAGER:
                    callback.onContinue(postcard);
                    break;
                default:
                    callback.onInterrupt(null);
                    break;
            }

        }
    }

    @Override
    public void init(Context context) {
        LogUtils.i("initFirstOpenAppInterceptor");
    }
}
