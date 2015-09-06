package com.xyggun.mydemo.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.common.GetImageTask;
import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * 异步加载网络图片
 * 2014-9-29 17：52 xiangyg
 **/

@SetContentView(R.layout.activity_demo1)
public class Demo1Activity extends BaseActivity {

    @InjectView(R.id.imgView_test)
    ImageView imgViewTest;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHeader();
        initView();
    }

    private void initHeader() {
        TextView back = (TextView) findViewById(R.id.back_title);
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

        WeakReference<ImageView> imageViewWeakReference = new WeakReference<ImageView>(imgViewTest);
        imageView = imageViewWeakReference.get();
        if (imageView != null) {
            new GetImageTask(this, imageView, "http://su.bdimg.com/static/superplus/img/logo_white_ee663702.png", scWidth, scHeight).execute("");
            //new GetImageTask(this, imgViewTest, "http://testclientservice.laoshi321.com/clientservice/GetTwodimensionalcode?content=http://test.laoshi321.com/teacherinfo/10456.html",scWidth,scHeight).execute("");
        }
    }

    @Override
    protected void onDestroy() {
        imageView = null;
        super.onDestroy();
        System.gc();
    }
}
                                                                                           