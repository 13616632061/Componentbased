package com.based.component.toutiao.adapter;

import android.support.annotation.Nullable;

import com.based.component.toutiao.entity.News;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2019/5/8.
 */

public class VideoListAdapter extends BaseQuickAdapter<News,BaseViewHolder> {
    public VideoListAdapter(int layoutResId, @Nullable List<News> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {

    }
}
