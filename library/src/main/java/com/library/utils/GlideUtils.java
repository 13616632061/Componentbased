package com.library.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.library.R;

/**
 * Created by Administrator on 2019/5/14.
 * 对glide进行封装的工具类
 */

public class GlideUtils {
    /**
     * 加载含有默认占位图
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void load(Context context, String url, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.ic_default);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(iv);
    }

    /**
     * 加载缩略图
     *
     * @param context
     * @param url
     * @param iv
     * @param placeHolderResId
     */
    public static void load(Context context, String url, ImageView iv, int placeHolderResId) {
        if (placeHolderResId == -1) {
            Glide.with(context)
                    .load(url)
                    .into(iv);
            return;
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(placeHolderResId);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(iv);
    }

    /**
     * 加载圆型图
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void loadRound(Context context, String url, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.ic_circle_default)
                .centerCrop()
                .circleCrop();

        Glide.with(context)//
                .load(url)//
                .apply(options)//
                .into(iv);
    }
}
