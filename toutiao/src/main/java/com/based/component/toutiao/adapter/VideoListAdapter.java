package com.based.component.toutiao.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.based.component.toutiao.R;
import com.based.component.toutiao.entity.News;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.library.utils.GlideUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

/**
 * Created by Administrator on 2019/5/8.
 */

public class VideoListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    private Context mContext;
    private String videoUrl = "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4";

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

        helper.setText(R.id.tv_author, item.user_info.name)//昵称
                .setText(R.id.tv_comment_count, String.valueOf(item.comment_count));//评论数
        if (item.video_detail_info == null || !item.has_video || TextUtils.isEmpty(item.url)) {
            return;
        }
//        MyJzvdStd jz_video =helper.getView(R.id.jz_video);
//        jz_video.setUp(item.url,"", Jzvd.SCREEN_NORMAL);
//        Glide.with(jz_video.getContext()).load(item.video_detail_info.detail_video_large_image.url).into(jz_video.thumbImageView);
//        jz_video.bottomProgressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.seekbar_progress_drawable,null));
        StandardGSYVideoPlayer video_player = helper.getView(R.id.video_player);
        video_player.setUpLazy(videoUrl, true, null, null, item.title);
//增加title
        video_player.getTitleTextView().setVisibility(View.VISIBLE);
//设置返回键
        video_player.getBackButton().setVisibility(View.VISIBLE);
//设置全屏按键功能
        video_player.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video_player.startWindowFullscreen(mContext, false, true);
            }
        });
//防止错位设置
        video_player.setPlayTag(TAG);
        video_player.setPlayPosition(helper.getAdapterPosition());
//是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        video_player.setAutoFullWithSize(true);
//音频焦点冲突时是否释放
        video_player.setReleaseWhenLossAudio(false);
//全屏动画
        video_player.setShowFullAnimation(true);
//小屏时不触摸滑动
        video_player.setIsTouchWiget(false);
    }
}
