package com.based.component.component_based.ui.guide.view;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.based.component.component_based.R;
import com.based.component.component_based.weight.SplashPagerImageHolderView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.library.base.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SplashPagerActivity extends BaseActivity {


    @InjectView(R.id.splash_banner)
    ConvenientBanner splashBanner;

    private List<Integer> imagesList;

    @Override
    public int getContentView() {
        return R.layout.activity_splash_pager;
    }

    @Override
    protected void initView() {
        setBanner();
    }


    @Override
    protected void initData() {

    }

    private void setBanner() {
        imagesList = new ArrayList<>();
        TypedArray imagesArray = this.getResources().obtainTypedArray(R.array.splash_image);
        for (int i=0;i<4;i++){
            int imageId=imagesArray.getResourceId(i,0);
            imagesList.add(imageId);
        }
        imagesArray.recycle();

        splashBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new SplashPagerImageHolderView(SplashPagerActivity.this,itemView );
            }

            @Override
            public int getLayoutId() {
                return 0;
            }
        }, imagesList).setPageIndicator(new int[]{R.mipmap.point_focused, R.mipmap.point_unfocused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .setOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    }

                    @Override
                    public void onPageSelected(int index) {
                        if(imagesList.size()==index){

                        }
                    }
                });
    }

}
