package com.xyggun.mydemo.jpush;

import com.xyggun.baselibrary.base.BaseActivity;

import cn.jpush.android.api.JPushInterface;

public class JPushBaseActivity extends BaseActivity {

    public JPushBaseActivity() {
    }

    public void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    public void onStop() {
        super.onStop();
    }

}
