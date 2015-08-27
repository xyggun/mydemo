package com.xyggun.mydemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xyggun.mydemo.R;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.inject.base.BaseActivity;

@SetContentView(R.layout.activity_demo4)
public class Demo4Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHeader();
    }

    private void initHeader() {
        TextView back = (TextView)findViewById(R.id.back_title);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishs();
            }
        });
    }
}
                                                                                           