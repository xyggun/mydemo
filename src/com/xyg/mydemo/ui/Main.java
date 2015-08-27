package com.xyggun.mydemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xyggun.mydemo.R;
import com.xyggun.mydemo.app.AppRedirectUrl;

public class Main extends BaseActivity {

	ListView lvMian;
	ArrayAdapter<String> lvAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		System.out.println("����"+this.toString());
			
		getControls();
		initView();
	}

	private void getControls() {//��ȡ�ؼ�
		lvMian=(ListView)findViewById(R.id.listview_main);
	}

	private void initView() {//�Կؼ�����¼������������ݳ�ʼ��[������]��
		lvAdapter=new ArrayAdapter<String>(this,R.layout.main_list_item, AppRedirectUrl.UrlTitle);
		lvMian.setAdapter(lvAdapter);
		
		lvMian.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				@SuppressWarnings("static-access")
				Intent intent=new AppRedirectUrl(Main.this).UrlActivity[position];
				startActivitys(intent);
			}
		});
	}

}
