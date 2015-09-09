package com.xyggun.mydemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xyggun.baselibrary.base.BaseActivity;
import com.xyggun.mydemo.R;

public class LifeCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        System.out.println("activity===============>onCreate");

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

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("activity===============>onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("activity===============>onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("activity===============>onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("activity===============>onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("activity===============>onDestroy");
    }
}
