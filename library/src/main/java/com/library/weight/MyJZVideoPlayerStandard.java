package com.library.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.apkfuns.logutils.LogUtils;
import com.library.R;

import cn.jzvd.JzvdStd;


public class MyJZVideoPlayerStandard extends JzvdStd {

    public MyJZVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyJZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == cn.jzvd.R.id.thumb || i == cn.jzvd.R.id.start) {
            LogUtils.e("state: " + state);
            if (state == STATE_IDLE || state == SCREEN_NORMAL) {
                //如果当前是闲置状态，点击后回调点击播放的事件
                if (mListener != null) {
                    mListener.onStartClick();
                    return;
                }
            }
        } else if (i == cn.jzvd.R.id.fullscreen) {
            if (state == SCREEN_FULLSCREEN) {
                //click quit fullscreen
            } else {
                //click goto fullscreen
            }
        }
        super.onClick(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_std;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mListener != null){
            mListener.onTouch();
        }
        return super.onTouch(v, event);
    }

    @Override
    public void startVideo() {
        super.startVideo();
        LogUtils.i(TAG,"startVideo...");
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        if (mListener != null) {
            mListener.onStateNormal();
        }
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        LogUtils.i(TAG,"onStatePreparing...");
        if (mListener != null){
            mListener.onPreparing();
        }
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        LogUtils.i(TAG,"onStatePlaying...");
        if (mListener != null){
            mListener.onPlaying();
        }
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
        LogUtils.i(TAG,"onStatePause...");
        if (mListener != null){
            mListener.onPause();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        super.onProgressChanged(seekBar, progress, fromUser);
        if (mListener != null){
            mListener.onProgressChanged(progress);
        }
    }

    @Override
    public void onStateError() {
        super.onStateError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        if (mListener != null){
            mListener.onComplete();
        }
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
        LogUtils.i(TAG,"onInfo...");
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }

//    @Override
//    public void startWindowFullscreen() {
//        super.startWindowFullscreen();
//    }
//
//    @Override
//    public void startWindowTiny() {
//        super.startWindowTiny();
//    }

    @Override
    public void startDismissControlViewTimer() {
        super.startDismissControlViewTimer();
        LogUtils.i(TAG,"startDismissControlViewTimer...");
        if (mListener != null){
            mListener.onStartDismissControlViewTimer();
        }
    }


    private VideoStateListener mListener;

    public VideoStateListener getListener() {
        return mListener;
    }

    public void setVideoStateListener(VideoStateListener listener) {
        mListener = listener;
    }
}
