package com.xyggun.mydemo.ui.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.common.GetImageTaskOnImageView;
import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.base.BaseActivity;

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
        initHeader();
    }

    private void getControls() {
        imgViewTest = (ImageView) findViewById(R.id.imgView_test);
    }

    private void initHeader() {
        TextView back = (TextView)findViewById(R.id.back_title);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishs();
            }
        });
    }

    private void initView() {
        int scWidth = 0;
        int scHeight = 0;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        scWidth = dm.widthPixels; //宽度
        scHeight = dm.heightPixels; //高度
        new GetImageTaskOnImageView(this, imgViewTest, "http://images.apple.com/cn/ipad-pro/images/overview/canvas_large_2x.jpg", scWidth, scHeight).execute("");
    }

    @Override
    protected void onDestroy() {
        imgViewTest = null;
        super.onDestroy();
        System.gc();
    }
}
                                                                                           