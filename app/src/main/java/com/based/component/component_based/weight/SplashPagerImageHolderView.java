package com.based.component.component_based.weight;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.based.component.component_based.R;
import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2019/4/25.
 */

public class SplashPagerImageHolderView extends Holder<Integer> {

    private ImageView iv_splash;

    public SplashPagerImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        iv_splash=itemView.findViewById(R.id.iv_splash);
    }

    @Override
    public void updateUI(Integer data) {
        iv_splash.setImageResource(data);
    }
}
