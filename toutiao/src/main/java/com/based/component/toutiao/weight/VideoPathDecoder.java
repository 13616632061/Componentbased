package com.based.component.toutiao.weight;

import android.util.Base64;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.based.component.toutiao.api.ApiConstant;
import com.based.component.toutiao.api.ApiRetrofit;
import com.based.component.toutiao.entity.Video;
import com.based.component.toutiao.entity.VideoModel;
import com.based.component.toutiao.entity.response.ResultResponse;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public abstract class VideoPathDecoder {

    public static final String TAG = VideoPathDecoder.class.getSimpleName();

    public void decodePath(String srcUrl) {
        LogUtils.e(TAG+": srcUrl: " + srcUrl);
        ApiRetrofit.getInstance().getApiService().getVideoHtml(srcUrl)
                .flatMap(new Func1<String, Observable<ResultResponse<VideoModel>>>() {
                    @Override
                    public Observable<ResultResponse<VideoModel>> call(String response) {
                        Pattern pattern = Pattern.compile("videoId: '(.+)'");
                        Matcher matcher = pattern.matcher(response);
                        if (matcher.find()) {
                            String videoId = matcher.group(1);
                            LogUtils.e(TAG,"videoId: " + videoId);
                            //1.将/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}，进行crc32加密。
                            String r = getRandom();
                            LogUtils.e(TAG,"r: " + r);
                            CRC32 crc32 = new CRC32();
                            String s = String.format(ApiConstant.URL_VIDEO, videoId, r);
                            //进行crc32加密。
                            crc32.update(s.getBytes());
                            String crcString = crc32.getValue() + "";
                            //2.访问http://i.snssdk.com/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}&s={crc32值}
                            String url = ApiConstant.HOST_VIDEO + s + "&s=" + crcString;
                            LogUtils.e(TAG,"s: " + crcString);
                            Log.i(TAG,url);
                            return ApiRetrofit.getInstance().getApiService().getVideoData(url);
                        }
                        return null;
                    }
                })
                .map(new Func1<ResultResponse<VideoModel>, Video>() {
                    @Override
                    public Video call(ResultResponse<VideoModel> videoModelResultResponse) {
                        VideoModel.VideoListBean data = videoModelResultResponse.data.video_list;

                        if (data.video_3 != null) {
                            return updateVideo(data.video_3);
                        }
                        if (data.video_2 != null) {
                            return updateVideo(data.video_2);
                        }
                        if (data.video_1 != null) {
                            return updateVideo(data.video_1);
                        }
                        return null;
                    }

                    private String getRealPath(String base64) {
                        return new String(Base64.decode(base64.getBytes(), Base64.DEFAULT));
                    }

                    private Video updateVideo(Video video) {
                        //base64解码
                        video.main_url = getRealPath(video.main_url);
                        return video;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Video>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtils.e(e.toString());
                        onDecodeError("解析视频失败，请重试");
                    }

                    @Override
                    public void onNext(Video video) {
                        LogUtils.e(TAG,"视频地址：" + video.main_url);
                        onSuccess(video.main_url);
                    }
                });
    }

    private String getRandom() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public abstract void onSuccess(String url);
    public abstract void onDecodeError(String errorMsg);
}

