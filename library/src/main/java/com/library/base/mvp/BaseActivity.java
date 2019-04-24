package com.library.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.library.app.LibAplication;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2019/4/24.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        initView();
        initData();

    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 返回一个用于页面显示界面的布局id
     * @return
     */
    public abstract int getContentView() ;



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //测试内存泄漏，正式一定要隐藏
        RefWatcher refWatcher = LibAplication.getRefWatcher(this);//1
        refWatcher.watch(this);
    }
}
