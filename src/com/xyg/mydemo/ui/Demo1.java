package com.xyggun.mydemo.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.common.GetImageTask;
/**
 * �첽��������ͼƬ
 * 2014-9-29 17��52 xiangyg
 **/
public class Demo1 extends BaseActivity {
	ImageView imgViewTest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo1);
		
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
		new GetImageTask(this, imgViewTest, "http://su.bdimg.com/static/superplus/img/logo_white_ee663702.png",scWidth,scHeight).execute("");
		//new GetImageTask(this, imgViewTest, "http://testclientservice.laoshi321.com/clientservice/GetTwodimensionalcode?content=http://test.laoshi321.com/teacherinfo/10456.html",scWidth,scHeight).execute("");
	}
	
}
                                                                                           