package com.based.component.component_based.ui.guide.model.SplashPagerModel;

import android.content.Context;
import android.content.res.TypedArray;

import com.based.component.component_based.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/26.
 */

public class SplashPagerModel implements ISplashPagerModel {

    private List<Integer> imagesList;
    @Override
    public List<Integer> getBannerDats(Context context) {
        imagesList = new ArrayList<>();
        TypedArray imagesArray = context.getResources().obtainTypedArray(R.array.splash_image);
        for (int i = 0; i < 4; i++) {
            int imageId = imagesArray.getResourceId(i, 0);
            imagesList.add(imageId);
        }
        imagesArray.recycle();
        return imagesList;
    }
}
