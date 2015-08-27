package com.xyggun.mydemo.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.common.GetImageTaskOnImageView;
import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.inject.base.BaseActivity;

@SetContentView(R.layout.activity_demo2)
public class Demo2Activity extends BaseActivity {
    @InjectView(R.id.imgView_test)
    ImageView imgViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        getControls();
        initView();
    }

    private void getControls() {
        imgViewTest = (ImageView) findViewById(R.id.imgView_test);
    }

    private void initView() {
        int scWidth = 0;
        int scHeight = 0;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        scWidth = dm.widthPixels; //宽度
        scHeight = dm.heightPixels; //高度
        new GetImageTaskOnImageView(this, imgViewTest, "http://img.daimg.com/uploads/allimg/140923/3-140923001916.jpg", scWidth, scHeight).execute("");
    }
}
                                                                                           