package com.library.base.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.library.app.LibAplication;
import com.squareup.leakcanary.RefWatcher;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/4/24.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private T mPresenter;
    //对所有activity进行管理
    private static Activity mCurrentActivity;
    ;
    private static List<Activity> mActivitys = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        ButterKnife.inject(this);
        //路由自动属性注入
        ARouter.getInstance().inject(this);

        //初始化的时候将其添加到集合中
        synchronized (mActivitys) {
            mActivitys.add(this);
        }
        initView();
//        initData();

    }


    /**
     * 返回一个用于页面显示界面的布局id
     *
     * @return
     */
    public abstract int getContentView();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
//    protected abstract void initData();
    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //测试内存泄漏，正式一定要隐藏
        RefWatcher refWatcher = LibAplication.getRefWatcher(this);//1
        refWatcher.watch(this);

        //退出的时候清除
        synchronized (mActivitys) {
            mActivitys.remove(this);
        }
    }

    public void routerNavigation(String path) {
        ARouter.getInstance().build(path).navigation();
    }
}
