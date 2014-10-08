package com.xyg.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.xyg.mydemo.ui.Main;

public class StartActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		Intent intent = new Intent(this, Main.class);
		startActivity(intent);
		finish();
	}

}
