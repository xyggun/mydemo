package com.xyggun.baselibrary.inject;

import android.app.Activity;
import android.view.Window;

public class NoTitleInject {
	public static void inject(Activity instance) {
		if (instance.getClass().isAnnotationPresent(NoTitle.class)) {
			instance.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
	}
}
