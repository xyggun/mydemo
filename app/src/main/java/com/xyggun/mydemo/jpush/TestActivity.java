package com.xyggun.mydemo.jpush;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyggun.baselibrary.base.BaseActivity;
import com.xyggun.mydemo.R;

import cn.jpush.android.api.JPushInterface;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            tv.setText("Title : " + title + "  " + "Content : " + content);
        }

        LinearLayout viewHeader = (LinearLayout)(TestActivity.this).getLayoutInflater().inflate(
                R.layout.view_header, null);
        if(viewHeader != null){
            TextView back = (TextView)viewHeader.findViewById(R.id.back_title);
            back.setVisibility(View.INVISIBLE);
            addContentView(viewHeader,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10,viewHeader.getHeight(),10,0);
        addContentView(tv, layoutParams);
    }

}
