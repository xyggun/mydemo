package com.xyg.mydemo.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.xyg.mydemo.R;
import com.xyg.mydemo.common.GetImageTask;
import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.inject.base.BaseActivity;

/**
 * 异步加载网络图片
 * 2014-9-29 17：52 xiangyg
 **/

@SetContentView(R.layout.activity_demo1)
public class Demo1Activity extends BaseActivity {

    @InjectView(R.id.imgView_test)
    ImageView imgViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        int scWidth = 0;
        int scHeight = 0;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        scWidth = dm.widthPixels; //宽度
        scHeight = dm.heightPixels; //高度
        new GetImageTask(this, imgViewTest, "http://su.bdimg.com/static/superplus/img/logo_white_ee663702.png", scWidth, scHeight).execute("");
        //new GetImageTask(this, imgViewTest, "http://testclientservice.laoshi321.com/clientservice/GetTwodimensionalcode?content=http://test.laoshi321.com/teacherinfo/10456.html",scWidth,scHeight).execute("");
    }

}
                                                                                           