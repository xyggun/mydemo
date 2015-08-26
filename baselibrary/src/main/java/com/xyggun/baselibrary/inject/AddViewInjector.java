package com.xyggun.baselibrary.inject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * MobileUserDAO dao=new MobileUserDAO(); int vstatus=Integer.valueOf(request.getParameter("vstatus")); List<HelpVO> list=dao.getHelpVoList(vstatus);
 * 
 */
public class AddViewInjector {
	public static void inject(ViewGroup instance) {
		AddView addView = instance.getClass().getAnnotation(AddView.class);
		Context context = instance.getContext();
		if (addView != null) {
			int[] viewIds = addView.value();
			View child = null;
			if (viewIds != null) {
				for (int viewId : viewIds) {
					child = LayoutInflater.from(context).inflate(viewId, null);
					instance.addView(child);
				}
			}
		}
	}
}
