package com.xyggun.baselibrary.inject.base;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tecoming.t_base.app.AppManager;
import com.tecoming.t_base.common.BitmapManager;
import com.tecoming.t_base.common.StringUtils;
import com.tecoming.t_base.inject.InjectorFactory;
import com.tecoming.teacher.R;
import com.tecoming.teacher.app.AppContext;
import com.tecoming.teacher.common.ToastUtils;
import com.tecoming.teacher.http.AsyncCfg;
import com.tecoming.teacher.util.NoDataModel;
import com.tecoming.teacher.util.UserModel;
import com.umeng.analytics.MobclickAgent;

public class BaseActivity extends FragmentActivity {
	public BitmapManager bmpManager;

	public static final int LISTVIEW_DATA_MORE = 1;
	public static final int LISTVIEW_DATA_LOADING = 2;
	public static final int LISTVIEW_DATA_FULL = 3;

	public AppContext appContext;

	public String errorMess;
	private String errorBaseMess;
	private String noread_msgcount;
	public ImageView footer_view_msg;
	public TextView noreadmsg_num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MobclickAgent.setDebugMode(false);
		MobclickAgent.openActivityDurationTrack(true);
		MobclickAgent.updateOnlineConfig(this);

		InjectorFactory.injectBeforeSetContentView(this);// ������ͼǰ��ע�����

		AppManager.getAppManager().addActivity(this);
		appContext = (AppContext) this.getApplication();
		bmpManager = new BitmapManager(BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
	}

	protected void onStart() {
		super.onStart();
		InjectorFactory.injectOnStart(this);// ��ִ��onstartʱ ��ע�����
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("SplashScreen"); // ͳ��ҳ��
		MobclickAgent.onResume(this); // ͳ��ʱ��
		if (!StringUtils.isEmpty(appContext.getUserId())) {
			refureHeaderText();
		}
	}

	protected void onPause() {
		super.onPause();
		// ��֤ onPageEnd ��onPause ֮ǰ����,��Ϊ onPause �лᱣ����Ϣ
		MobclickAgent.onPageEnd("SplashScreen");
		MobclickAgent.onPause(this);
	}

	public void startActivitys(Intent intent) {
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void finishs() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	public void defaultFinish() {
		super.finish();
	}

	@Override
	public void onBackPressed() {
		finishs();
	}

	public class BaseTask extends AsyncTask<Integer, Integer, Integer> {
		private int action;
		private int whatRefresh;
		private boolean isRefresh;

		public BaseTask(int action, boolean isRefresh, int whatRefresh) {
			this.action = action;
			this.isRefresh = isRefresh;
			this.whatRefresh = whatRefresh;
		}

		protected Integer doInBackground(Integer... params) {
			getData(action, isRefresh, whatRefresh);
			return 1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			Update(action, whatRefresh);
		}
	}

	private void getData(int action, boolean isRefresh, int whatRefresh) {
		errorBaseMess = null;
		switch (action) {
		case AsyncCfg.LOGIN:
			UserModel logininfo = appContext.Login(appContext.getLoginInfo().getAccount(), appContext.getLoginInfo().getPassword());
			if (logininfo.isSuccess()) {
				String id = logininfo.getUserId();
				String sessionId = logininfo.getSessionId();
				appContext.setUserId(id);
				appContext.setSessionId(sessionId);
				appContext.setToken(logininfo.getToken());
				appContext.setUserModel(logininfo);
			} else {
				errorBaseMess = logininfo.getDesc();
			}
			break;
		case AsyncCfg.NOREADMESSAGENUM:
			NoDataModel mode = appContext.noReadMessageNum();
			if (mode.isSuccess()) {
				noread_msgcount = mode.getDesc();
			} else {
				errorBaseMess = mode.getDesc();
			}
			break;
		}
	}

	private void Update(int action, int whatRefresh) {
		if (errorBaseMess == null) {
			switch (action) {
			case AsyncCfg.NOREADMESSAGENUM:
				appContext.setMessNum(noread_msgcount);
				refureHeaderText();
				break;
			}
		} else {
			ToastUtils.showToast(BaseActivity.this, errorBaseMess);
		}
	}

	private void refureHeaderText() {
		String num = appContext.getMessNum();
		if (num == null || num.equals("0")) {
			if (footer_view_msg != null && noreadmsg_num != null) {
				footer_view_msg.setVisibility(View.GONE);
				noreadmsg_num.setVisibility(View.GONE);
				noreadmsg_num.setText(num);
			}
		} else {
			if (footer_view_msg != null && noreadmsg_num != null) {
				footer_view_msg.setVisibility(View.VISIBLE);
				noreadmsg_num.setVisibility(View.VISIBLE);
				noreadmsg_num.setText(num);
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		InjectorFactory.Destory(this);// ��������
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent != null) {
			InjectorFactory.injectOnNewIntent(this);// �����µ���ͼ�� ����ע��
		}
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		InjectorFactory.injectAfterSetContentView(this);// �������ݺ��ע�����
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		InjectorFactory.injectAfterSetContentView(this);// �������ݺ��ע�����
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		InjectorFactory.injectAfterSetContentView(this);// �������ݺ��ע�����
	}

	/**
	 * �Խ϶̵�ʱ����toast��ʾ����Լ3������ʾ��
	 * 
	 * @param msg
	 */
	public void showMessage(String msg) {
		if (msg != null) {
			Toast.makeText(this, msg + "", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * �Խϳ���ʱ����toast��ʾ����Լ5������ʾ��
	 * 
	 * @param msg
	 */
	public void showLongMessage(String msg) {
		if (msg != null) {
			Toast.makeText(this, msg + "", Toast.LENGTH_LONG).show();
		}
	}

}
