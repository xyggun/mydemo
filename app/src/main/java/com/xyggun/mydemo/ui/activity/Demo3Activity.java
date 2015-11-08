package com.xyggun.mydemo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ant.liao.GifView;
import com.xyggun.baselibrary.base.BaseActivity;
import com.xyggun.mydemo.R;

public class Demo3Activity extends BaseActivity implements OnClickListener {
    GifView gifTest0, gifTest1, gifTest2, gifTest3, gifTest4, gifTest5, gifTest6, gifTest7, gifTest8, gifTest9, gifTest10, gifTest11, gifTest12, gifTest13, gifTest14, gifTest15, gifTest16, gifTest17, gifTest18, gifTest19, gifTest20, gifTest21, gifTest22, gifTest23, gifTest24, gifTest25, gifTest26, gifTest27, gifTest28, gifTest29, gifTest30;
    private static GifView gifView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);

        getControls();
        initView();
        initHeader();
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

    private void getControls() {
        // 从xml中得到GifView的句柄
//        gifTest0 = (GifView) findViewById(R.id.gif_test0);
//        gifTest1 = (GifView) findViewById(R.id.gif_test1);
//        gifTest2 = (GifView) findViewById(R.id.gif_test2);
//        gifTest3 = (GifView) findViewById(R.id.gif_test3);
//        gifTest4 = (GifView) findViewById(R.id.gif_test4);
//        gifTest5 = (GifView) findViewById(R.id.gif_test5);
//        gifTest6 = (GifView) findViewById(R.id.gif_test6);
//        gifTest7 = (GifView) findViewById(R.id.gif_test7);
//        gifTest8 = (GifView) findViewById(R.id.gif_test8);
//        gifTest9 = (GifView) findViewById(R.id.gif_test9);
//        gifTest10 = (GifView) findViewById(R.id.gif_test10);
//        gifTest11 = (GifView) findViewById(R.id.gif_test11);
//        gifTest12 = (GifView) findViewById(R.id.gif_test12);
//        gifTest13 = (GifView) findViewById(R.id.gif_test13);
//        gifTest14 = (GifView) findViewById(R.id.gif_test14);
//        gifTest15 = (GifView) findViewById(R.id.gif_test15);
//        gifTest16 = (GifView) findViewById(R.id.gif_test16);
//        gifTest17 = (GifView) findViewById(R.id.gif_test17);
//        gifTest18 = (GifView) findViewById(R.id.gif_test18);
//        gifTest19 = (GifView) findViewById(R.id.gif_test19);
//        gifTest20 = (GifView) findViewById(R.id.gif_test20);
//        gifTest21 = (GifView) findViewById(R.id.gif_test21);
//        gifTest22 = (GifView) findViewById(R.id.gif_test22);
//        gifTest23 = (GifView) findViewById(R.id.gif_test23);
//        gifTest24 = (GifView) findViewById(R.id.gif_test24);
//        gifTest25 = (GifView) findViewById(R.id.gif_test25);
//        gifTest26 = (GifView) findViewById(R.id.gif_test26);
//        gifTest27 = (GifView) findViewById(R.id.gif_test27);
//        gifTest28 = (GifView) findViewById(R.id.gif_test28);
//        gifTest29 = (GifView) findViewById(R.id.gif_test29);
//        gifTest30 = (GifView) findViewById(R.id.gif_test30);
    }

    private void initView() {
//        SetGif(gifTest0, R.drawable.loading);
//        SetGif(gifTest1, R.drawable.loading_1);
//        SetGif(gifTest2, R.drawable.loading_2);
//        SetGif(gifTest3, R.drawable.loading_3);
//        SetGif(gifTest4, R.drawable.loading_4);
//        SetGif(gifTest5, R.drawable.loading_5);
//        SetGif(gifTest6, R.drawable.loading_6);
//        SetGif(gifTest7, R.drawable.loading_7);
//        SetGif(gifTest8, R.drawable.loading_8);
//        SetGif(gifTest9, R.drawable.loading_9);
    }


    private void SetGif(GifView gif, int gifImage) {
//        WeakReference<GifView> gifViewWeakReference = new WeakReference<GifView>(gif);
//        gifView = gifViewWeakReference.get();
//        if (gifView != null) {
//            // 设置Gif图片源
//            gifView.setGifImage(gifImage);
//            // 添加监听器
////            gifView.setOnClickListener(this);
//            // 设置显示的大小，拉伸或者压缩
////            gif.setShowDimension(300, 300);
//            // 设置加载方式：先加载后显示、边加载边显示、只显示第一帧再显示
//            gifView.setGifImageType(GifImageType.COVER);
//        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        gifTest0 = null;
        gifTest1 = null;
        gifTest2 = null;
        gifTest3 = null;
        gifTest4 = null;
        gifTest5 = null;
        gifTest6 = null;
        gifTest7 = null;
        gifTest8 = null;
        gifTest9 = null;
        gifTest10 = null;
        gifTest11 = null;
        gifTest12 = null;
        gifTest13 = null;
        gifTest14 = null;
        gifTest15 = null;
        gifTest16 = null;
        gifTest17 = null;
        gifTest18 = null;
        gifTest19 = null;
        gifTest20 = null;
        gifTest21 = null;
        gifTest22 = null;
        gifTest23 = null;
        gifTest24 = null;
        gifTest25 = null;
        gifTest26 = null;
        gifTest27 = null;
        gifTest28 = null;
        gifTest29 = null;
        gifTest30 = null;
        gifView = null;
        super.onDestroy();
        System.gc();
    }
}
                                                                                           