package com.based.component.toutiao.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.based.component.toutiao.R;
import com.based.component.toutiao.entity.News;
import com.based.component.toutiao.weight.MyJzvdStd;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.library.utils.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import cn.jzvd.Jzvd;

/**
 * Created by Administrator on 2019/5/8.
 */

public class VideoListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    private Context mContext;

    public VideoListAdapter(Context mContext, int layoutResId, @Nullable List<News> data) {
        super(layoutResId, data);
        this.mContext = mContext;

    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        if (TextUtils.isEmpty(item.title)) {
            //如果没有标题，则直接跳过
            return;
        }
        helper.setVisible(R.id.ll_title, false);//显示标题栏
        helper.setText(R.id.tv_title, item.title);//设置标题


        if (item.video_detail_info != null) {
            String format = mContext.getResources().getString(R.string.video_play_count);
            int watchCount = item.video_detail_info.video_watch_count;
            String countUnit = "";
            if (watchCount > 10000) {
                watchCount = watchCount / 10000;
                countUnit = "万";
            }
            helper.setText(R.id.tv_watch_count, String.format(format, watchCount + countUnit));//播放次数
//            GlideUtils.load(mContext,item.video_detail_info.detail_video_large_image.url,videoPlayer.getThumbImageView(),R.color.color_d8d8d8);
        }
        GlideUtils.loadRound(mContext, item.user_info.avatar_url, helper.getView(R.id.iv_avatar));//作者头像
        helper.setVisible(R.id.ll_duration, true)//显示时长
                //设置时长
                .setText(R.id.tv_duration, TimeUtils.millis2String(item.video_duration, new SimpleDateFormat("HH:mm", Locale.getDefault())));
        helper.setText(R.id.tv_author, item.user_info.name)//昵称
                .setText(R.id.tv_comment_count, String.valueOf(item.comment_count));//评论数
        if (item.video_detail_info == null || !item.has_video || TextUtils.isEmpty(item.url)) {
            return;
        }
        MyJzvdStd jz_video =helper.getView(R.id.jz_video);
        jz_video.setUp(item.url,"", Jzvd.SCREEN_NORMAL);
        Glide.with(jz_video.getContext()).load(item.video_detail_info.detail_video_large_image.url).into(jz_video.thumbImageView);
        jz_video.bottomProgressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.seekbar_progress_drawable,null));
    }
}
