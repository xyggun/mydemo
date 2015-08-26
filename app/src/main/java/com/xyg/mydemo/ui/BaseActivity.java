package com.xyg.mydemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xyg.mydemo.R;
import com.xyg.mydemo.app.AppContext;

public class BaseActivity extends Activity {


    public AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startActivitys(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void finishs() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void onBackPressed() {
        finishs();
    }
}
