package com.xyggun.baselibrary.inject;

import android.app.Activity;
import android.view.WindowManager;

public class FullscreenInject {
	public static void inject(Activity instance) {
		if (instance.getClass().isAnnotationPresent(Fullscreen.class)) {
			instance.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}
}
