package com.xyggun.mydemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xyggun.baselibrary.inject.InjectView;
import com.xyggun.baselibrary.inject.SetContentView;
import com.xyggun.baselibrary.inject.base.BaseActivity;
import com.xyggun.mydemo.R;
import com.xyggun.mydemo.app.AppContext;
import com.xyggun.mydemo.app.AppRedirectUrl;

@SetContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.listview_main)
    ListView lvMain;

    ArrayAdapter<String> lvAdapter;

    public AppContext appContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("进入" + this.toString());

        appContext = (AppContext) this.getApplication();

        initHeader();
        initView();
    }

    private void initHeader() {
        TextView back = (TextView)findViewById(R.id.back_title);
        back.setVisibility(View.INVISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishs();
            }
        });
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
