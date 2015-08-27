package com.xyggun.mydemo.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.common.GetImageTaskOnImageView;

public class Demo2 extends BaseActivity {
	ImageView imgViewTest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo2);
		
		getControls();
		initView();
	}
	
	private void getControls() {
		imgViewTest=(ImageView)findViewById(R.id.imgView_test);
	}
	
	private void initView() {
		int scWidth=0;
		int scHeight=0;
		DisplayMetrics dm = new DisplayMetrics();getWindowManager().getDefaultDisplay().getMetrics(dm);
		scWidth = dm.widthPixels;//���
		scHeight = dm.heightPixels ;//�߶�
		new GetImageTaskOnImageView(this,imgViewTest, "http://img.daimg.com/uploads/allimg/140923/3-140923001916.jpg",scWidth,scHeight).execute("");
	}
}
                                                                                           