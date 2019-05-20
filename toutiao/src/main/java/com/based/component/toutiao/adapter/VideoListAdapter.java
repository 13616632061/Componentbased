package com.based.component.toutiao.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.based.component.toutiao.R;
import com.based.component.toutiao.entity.News;
import com.based.component.toutiao.weight.VideoPathDecoder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.library.utils.GlideUtils;
import com.library.utils.UIUtils;
import com.library.weight.MyJZVideoPlayerStandard;
import com.library.weight.VideoStateListenerAdapter;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Administrator on 2019/5/8.
 */

public class VideoListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    private Context mContext;
    private String videoUrl = "http://domhttp.kksmg.com/2019/05/20/h264_1200k_mp4_SHDongFangHD30000002019052032108021091_aac.mp4";

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

        MyJZVideoPlayerStandard videoPlayer = helper.getView(R.id.video_player);
        if (item.video_detail_info != null) {
            String format = mContext.getResources().getString(R.string.video_play_count);
            int watchCount = item.video_detail_info.video_watch_count;
            String countUnit = "";
            if (watchCount > 10000) {
                watchCount = watchCount / 10000;
                countUnit = "万";
            }
            helper.setText(R.id.tv_watch_count, String.format(format, watchCount + countUnit));//播放次数
//            GlideUtils.load(mContext,item.video_detail_info.detail_video_large_image.url,videoPlayer.thumbImageView,-1);
        }
        GlideUtils.loadRound(mContext, item.user_info.avatar_url, helper.getView(R.id.iv_avatar));//作者头像

        helper.setText(R.id.tv_author, item.user_info.name)//昵称
                .setText(R.id.tv_comment_count, String.valueOf(item.comment_count));//评论数
        if (item.video_detail_info == null || !item.has_video || TextUtils.isEmpty(item.url)) {
            return;
        }
//        videoPlayer.setUp(videoUrl, item.title);

        videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE);
        videoPlayer.tinyBackImageView.setVisibility(GONE);

        videoPlayer.titleTextView.setText("");//清除标题,防止复用的时候出现

        videoPlayer.setVideoStateListener(new VideoStateListenerAdapter() {

            boolean isVideoParsing = false; //视频是否在解析的标识

            @Override
            public void onStartClick() {
                LogUtils.e("onStartClick");
                String videoUrl = "";
                if (item.video_detail_info != null) {
                    //取出解析后的网址
                    videoUrl = item.video_detail_info.parse_video_url;
                }

                if (!TextUtils.isEmpty(videoUrl)){
                    //如果已经解析过
                    LogUtils.e("取出对应的视频地址: " + videoUrl);
                    videoPlayer.setUp(videoUrl, item.title);
                    videoPlayer.startVideo();
                    return;
                }

                //解析视频
                parseVideo();
            }

            private void parseVideo() {
                LogUtils.e("title: " + item.title);

                if (isVideoParsing) {
                    LogUtils.e("视频正在解析，不重复调用...");
                    return;
                } else {
                    isVideoParsing = true;
                }

                //隐藏开始按钮 显示加载中
                videoPlayer.setAllControlsVisiblity(GONE, GONE, GONE, VISIBLE, VISIBLE, GONE, GONE);
//                helper.setVisible(R.id.ll_duration, false);//隐藏时长
                helper.setVisible(R.id.ll_title, false);//隐藏标题栏

                VideoPathDecoder decoder = new VideoPathDecoder() {
                    @Override
                    public void onSuccess(String url) {
                        LogUtils.i("Video url:" + url);
                        UIUtils.postTaskSafely(new Runnable() {
                            @Override
                            public void run() {
                                //更改视频是否在解析的标识
                                isVideoParsing = false;

                                //准备播放
                                videoPlayer.setUp(url, item.title);

                                if (item.video_detail_info != null) {
                                    item.video_detail_info.parse_video_url = url; //赋值解析后的视频地址
                                    videoPlayer.seekToInAdvance = item.video_detail_info.progress; //设置播放进度
                                }

                                //开始播放
                                videoPlayer.startVideo();
                            }
                        });
                    }

                    @Override
                    public void onDecodeError(String errorMsg) {
                        isVideoParsing = false;//更改视频是否在解析的标识
                        //隐藏加载中 显示开始按钮
                        videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE);
                        UIUtils.showToast(errorMsg);
                    }
                };
                decoder.decodePath(item.url);
            }

        });
    }
}
