package com.xyg.mydemo.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;
import com.xyg.mydemo.R;

public class Demo3 extends BaseActivity implements OnClickListener{
	GifView gifTest0,gifTest1,gifTest2,gifTest3,gifTest4,gifTest5,gifTest6,gifTest7,gifTest8,gifTest9,gifTest10
	,gifTest11,gifTest12,gifTest13,gifTest14,gifTest15,gifTest16,gifTest17,gifTest18,gifTest19,gifTest20
	,gifTest21,gifTest22,gifTest23,gifTest24,gifTest25,gifTest26,gifTest27,gifTest28,gifTest29,gifTest30;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo3);
		
		getControls();
		initView();
	}
	
	private void getControls() {
		// 从xml中得到GifView的句柄
		gifTest0=(GifView)findViewById(R.id.gif_test0);
		gifTest1=(GifView)findViewById(R.id.gif_test1);
		gifTest2=(GifView)findViewById(R.id.gif_test2);
		gifTest3=(GifView)findViewById(R.id.gif_test3);
		gifTest4=(GifView)findViewById(R.id.gif_test4);
		gifTest5=(GifView)findViewById(R.id.gif_test5);
		gifTest6=(GifView)findViewById(R.id.gif_test6);
		gifTest7=(GifView)findViewById(R.id.gif_test7);
		gifTest8=(GifView)findViewById(R.id.gif_test8);
		gifTest9=(GifView)findViewById(R.id.gif_test9);
		gifTest10=(GifView)findViewById(R.id.gif_test10);
		gifTest11=(GifView)findViewById(R.id.gif_test11);
		gifTest12=(GifView)findViewById(R.id.gif_test12);
		gifTest13=(GifView)findViewById(R.id.gif_test13);
		gifTest14=(GifView)findViewById(R.id.gif_test14);
		gifTest15=(GifView)findViewById(R.id.gif_test15);
		gifTest16=(GifView)findViewById(R.id.gif_test16);
		gifTest17=(GifView)findViewById(R.id.gif_test17);
		gifTest18=(GifView)findViewById(R.id.gif_test18);
		gifTest19=(GifView)findViewById(R.id.gif_test19);
		gifTest20=(GifView)findViewById(R.id.gif_test20);
		gifTest21=(GifView)findViewById(R.id.gif_test21);
		gifTest22=(GifView)findViewById(R.id.gif_test22);
		gifTest23=(GifView)findViewById(R.id.gif_test23);
		gifTest24=(GifView)findViewById(R.id.gif_test24);
		gifTest25=(GifView)findViewById(R.id.gif_test25);
		gifTest26=(GifView)findViewById(R.id.gif_test26);
		gifTest27=(GifView)findViewById(R.id.gif_test27);
		gifTest28=(GifView)findViewById(R.id.gif_test28);
		gifTest29=(GifView)findViewById(R.id.gif_test29);
		gifTest30=(GifView)findViewById(R.id.gif_test30);
	}
	
	private void initView() {
		SetGif(gifTest0,R.drawable.loading);
		SetGif(gifTest1,R.drawable.loading_1);
		SetGif(gifTest2,R.drawable.loading_2);
		SetGif(gifTest3,R.drawable.loading_3);
		SetGif(gifTest4,R.drawable.loading_4);
		SetGif(gifTest5,R.drawable.loading_5);
		SetGif(gifTest6,R.drawable.loading_6);
		SetGif(gifTest7,R.drawable.loading_7);
		SetGif(gifTest8,R.drawable.loading_8);
		SetGif(gifTest9,R.drawable.loading_9);
		/*SetGif(gifTest10,R.drawable.loading_10);
		SetGif(gifTest11,R.drawable.loading_11);
		SetGif(gifTest12,R.drawable.loading_12);
		SetGif(gifTest13,R.drawable.loading_13);
		SetGif(gifTest14,R.drawable.loading_14);
		SetGif(gifTest15,R.drawable.loading_15);
		SetGif(gifTest16,R.drawable.loading_16);
		SetGif(gifTest17,R.drawable.loading_17);
		SetGif(gifTest18,R.drawable.loading_18);
		SetGif(gifTest19,R.drawable.loading_19);
		SetGif(gifTest20,R.drawable.loading_20);
		SetGif(gifTest21,R.drawable.loading_21);
		SetGif(gifTest22,R.drawable.loading_22);
		SetGif(gifTest23,R.drawable.loading_23);
		SetGif(gifTest24,R.drawable.loading_24);
		SetGif(gifTest25,R.drawable.loading_25);
		SetGif(gifTest26,R.drawable.loading_26);
		SetGif(gifTest27,R.drawable.loading_27);
		SetGif(gifTest28,R.drawable.loading_28);
		SetGif(gifTest29,R.drawable.loading_29);
		SetGif(gifTest30,R.drawable.loading_30);*/
	}

	
	private void SetGif(GifView gif, int gifImage) {
		// 设置Gif图片源
		gif.setGifImage(gifImage);
		// 添加监听器
		gif.setOnClickListener(this);
//		// 设置显示的大小，拉伸或者压缩
//		gif.setShowDimension(300, 300);
		// 设置加载方式：先加载后显示、边加载边显示、只显示第一帧再显示
		gif.setGifImageType(GifImageType.COVER);
	}

	@Override
	public void onClick(View v) {
		
	}

}
                                                                                           