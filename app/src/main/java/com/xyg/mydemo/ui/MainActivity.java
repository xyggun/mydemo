package com.xyg.mydemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xyg.mydemo.R;
import com.xyg.mydemo.app.AppRedirectUrl;

public class MainActivity extends BaseActivity {

    ListView lvMain;
    ArrayAdapter<String> lvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("进入" + this.toString());

        getControls();
        initView();
    }

    private void getControls() { //获取控件
        lvMain = (ListView) findViewById(R.id.listview_main);
    }

    private void initView() { //对控件添加事件，及（简单数据初始化[不建议]）
        lvAdapter = new ArrayAdapter<String>(this, R.layout.item_main_list, AppRedirectUrl.UrlTitle);
        lvMain.setAdapter(lvAdapter);

        lvMain.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                @SuppressWarnings("static-access")
                Intent intent = new AppRedirectUrl(MainActivity.this).UrlActivity[position];
                startActivitys(intent);
            }
        });
    }

}
