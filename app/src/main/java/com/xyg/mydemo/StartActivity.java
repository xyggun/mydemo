package com.xyg.mydemo;

import android.content.Intent;
import android.os.Bundle;

import com.xyg.mydemo.ui.MainActivity;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.inject.base.BaseActivity;

@SetContentView(R.layout.activity_start)
public class StartActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

}
