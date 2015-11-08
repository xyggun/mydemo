package com.xyggun.mydemo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.base.BaseActivity;
import com.xyggun.mydemo.R;
import com.xyggun.mydemo.weight.MyScrollView;

@SetContentView(R.layout.activity_demo4)
public class Demo4Activity extends BaseActivity {
    @InjectView(R.id.my_scroll_view)
    MyScrollView myScrollView;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myScrollView = null;
    }
}
                                                                                           