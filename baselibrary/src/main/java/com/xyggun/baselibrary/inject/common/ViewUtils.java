package com.xyggun.baselibrary.inject.common;


import java.math.BigDecimal;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tecoming.t_base.R;

public class ViewUtils {
	
	public static void StarsView(Context context,String starsnum,LinearLayout lyout,int spacing) {
		String S1 = "";
		String S2 = "";
		if(starsnum!=null&&!starsnum.equals("")&&starsnum.length() == 3){
			S1= starsnum.substring(0, 1);
			S2= starsnum.substring(2, 3);
		} else {
			S1= "0";
			S2= "0";
		}
		lyout.removeAllViews();
		for(int i= 0;i<Integer.valueOf(S1);i++){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_yellow);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
			lp.leftMargin =FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);  
			lyout.addView(image);
		}
		if(0==Integer.valueOf(S2)&&Integer.valueOf(S1)<5){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_grey);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
			lp.leftMargin =FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);  
			lyout.addView(image);
		}else if(0<Integer.valueOf(S2)&&Integer.valueOf(S2)<6&&Integer.valueOf(S1)<5){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_half);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
			lp.leftMargin =FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);  
			lyout.addView(image);
		} else if(Integer.valueOf(S1)<5){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_yellow);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
			lp.leftMargin =FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);  
			lyout.addView(image);
		}
		for(int j= 0;j<5-(Integer.valueOf(S1)+1);j++){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_grey);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
			lp.leftMargin =FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);  
			lyout.addView(image);
		}
	}
	
	public static void StarsViewNew(Context context, String starsnum, LinearLayout lyout, int spacing) {
		String S1 = "";
		String S2 = "";
		if (starsnum != null && !starsnum.equals("") && starsnum.length() == 3) {
			S1 = starsnum.substring(0, 1);
			S2 = starsnum.substring(2, 3);
		} else {
			S1 = "0";
			S2 = "0";
		}
		lyout.removeAllViews();
		for (int i = 0; i < Integer.valueOf(S1); i++) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new2);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}
		if (0 == Integer.valueOf(S2) && Integer.valueOf(S1) < 5) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new1);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		} else if (0 < Integer.valueOf(S2) && Integer.valueOf(S2) < 6 && Integer.valueOf(S1) < 5) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new_half);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		} else if (Integer.valueOf(S1) < 5) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new2);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}
		for (int j = 0; j < 5 - (Integer.valueOf(S1) + 1); j++) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new1);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}
	}
	
	public static void StarsViewEval(Context context, float starsnum, LinearLayout lyout, int spacing) {
		int S1 = (int) starsnum;
		Double S2 = (double)starsnum - S1;
		BigDecimal   b   =   new   BigDecimal(S2);  
		S2 =  b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
		lyout.removeAllViews();
		for (int i = 0; i < S1; i++) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new2);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}
		if(S1==0){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new2);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}
		if(S1==0 || (S1==1 && S2<=0.69)){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new_half);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		} else if(S2 != 0 && S2 > 0.69){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new2);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}else if(S1!=5 && S2 <= 0.29){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new1);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}else if(S1 != 5){
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new_half);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}
		for (int j = 0; j < 5 - (S1==0?1:S1) - 1; j++) {
			ImageView image = new ImageView(context);
			image.setImageResource(R.drawable.star_new1);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30, 30);
			lp.leftMargin = FileUtils.dip2px(context, spacing);
			image.setLayoutParams(lp);
			lyout.addView(image);
		}
	}
	
	/***
	 * listView�߶�����Ӧ����
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

        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
	
	/**
	 * ��һ�������е����ֱ��Ϊ��Ӧ����ɫ ע�⣺�÷���ֻ�ܶ��ַ���ֻ����һ�����ֵ��ַ�������
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
			// SpannableStringBuilderʵ��CharSequence�ӿ�
			style.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			textView.setText(style);
		}
	}
	
	/**
	 * ��һ�������е����ֱ��Ϊ��Ӧ����ɫ ע�⣺�÷���ֻ�ܶ��ַ���ֻ����һ�����ֵ��ַ�������
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
	 * �ж��ַ����Ƿ�������
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
