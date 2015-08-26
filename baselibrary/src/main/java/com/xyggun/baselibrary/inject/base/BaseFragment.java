package com.xyggun.baselibrary.inject.base;

import com.xyggun.baselibrary.inject.InjectorFactory;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	private View decorView;// 当前视图

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LayoutInflater inflaters = LayoutInflater.from(getActivity());
		InjectorFactory.onFragmentCreate(this, inflaters, container);
		return this.decorView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		InjectorFactory.Destory(this);// 垃圾回收
		this.decorView = null;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	public View getDecorView() {
		return decorView;
	}

	public void setDecorView(View decorView) {
		this.decorView = decorView;
	}

	public View findViewById(int id) {
		if (this.decorView != null)
			return this.decorView.findViewById(id);
		return null;
	}

}
