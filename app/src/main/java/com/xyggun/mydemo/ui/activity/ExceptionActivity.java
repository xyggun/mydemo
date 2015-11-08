package com.xyggun.mydemo.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xyggun.baselibrary.base.BaseActivity;
import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.mydemo.R;

@SetContentView(R.layout.activity_exception)
public class ExceptionActivity extends BaseActivity {

    @InjectView(R.id.button_catch)
    private Button button_catch;

    private Button button_catch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHeader();
        button_catch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catchException();
            }
        });
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

    private void catchException(){
        button_catch2.setText("搜索");
    }

}
