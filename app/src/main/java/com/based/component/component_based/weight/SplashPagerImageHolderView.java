package com.based.component.component_based.weight;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2019/4/25.
 */

public class SplashPagerImageHolderView extends Holder<Integer> {

    private Context mContext;
    private ImageView mImageView;

    public SplashPagerImageHolderView(Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
    }

    @Override
    protected void initView(View itemView) {
        mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void updateUI(Integer data) {
        mImageView.setImageResource(data);
    }
}
