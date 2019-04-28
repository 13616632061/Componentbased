package com.based.component.component_based.ui.main.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.based.component.component_based.Constant.RouterMapping;
import com.based.component.component_based.R;

@Route(path = RouterMapping.ROUTER_ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
