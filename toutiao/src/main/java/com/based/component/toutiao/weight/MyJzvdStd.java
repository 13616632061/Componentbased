package com.based.component.toutiao.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.based.component.toutiao.R;

import cn.jzvd.JzvdStd;

public class MyJzvdStd extends JzvdStd {

    private Context context;

    public MyJzvdStd(Context context) {
        super(context);
        this.context=context;
    }

    public MyJzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

    }



    @Override
    public int getLayoutId() {
        return R.layout.layout_std_mute;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_speed:
                Toast.makeText(context,"加速",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
