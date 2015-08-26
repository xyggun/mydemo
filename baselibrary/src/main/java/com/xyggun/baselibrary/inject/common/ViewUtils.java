package com.xyggun.baselibrary.inject.common;


import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ViewUtils {

	/***
	 * listView高度自适应辅助
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 将一串文字中的数字标记为相应的颜色 注意：该方法只能对字符中只含有一个数字的字符做处理
	 *
	 * @param textView
	 * @param wholeStr
	 * @param color
	 * @author xyggun
	 */
	public static void setSpanStr(TextView textView, String wholeStr, int color) {
		if (StringUtils.isEmpty(wholeStr)) {
			return;
		}

		SpannableStringBuilder style = new SpannableStringBuilder(wholeStr);

		String[] array = wholeStr.split("");
		int startIndex = -1;
		int endIndex = -1;
		for (int i = 0; i < array.length; i++) {
			if (isInteger(array[i])) {
				if (startIndex == -1) {
					startIndex = (i - 1) < 0 ? 0 : i - 1;
				}
				endIndex = i;
			}
		}

		if (startIndex != -1) {
			// SpannableStringBuilder实现CharSequence接口
			style.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			textView.setText(style);
		}
	}

	/**
	 * 将一串文字中的数字标记为相应的颜色 注意：该方法只能对字符中只含有一个数字的字符做处理
	 * @param textView
	 * @param wholeStr
	 * @param filterStr
	 * @param color
	 */
	public static void setSpanStr(TextView textView, String wholeStr,String filterStr, int color) {
		if (StringUtils.isEmpty(wholeStr) || StringUtils.isEmpty(filterStr) || wholeStr.indexOf(filterStr) <0) {
			return;
		}

		SpannableStringBuilder style = new SpannableStringBuilder(wholeStr);
		try {
			style.setSpan(new ForegroundColorSpan(color), wholeStr.indexOf(filterStr), wholeStr.indexOf(filterStr)+filterStr.length()+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			textView.setText(style);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
