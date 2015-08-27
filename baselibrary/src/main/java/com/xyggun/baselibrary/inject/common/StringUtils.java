package com.xyggun.baselibrary.inject.common;

import android.annotation.SuppressLint;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class StringUtils {

	/**
	 * 字符串转整数
	 *
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 *
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static Long getlongDate() {
		long str = System.currentTimeMillis() / 1000;
		return str;
	}

	public static String getDate() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("hh:mm");
		String date = sDateFormat.format(new Date());
		return date;
	}

	public static String TimeProcess(String time) {
		if (time == null || time.equals("")) {
			return null;
		}
		String[] co = time.split(" ");
		return co[0];
	}

	public static String TimeProcessTODAY(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(0, 10);
	}

	public static String TimeProcessToMouthAndDay(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(5, 10);
	}

	public static String TimeProcessToTime(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(10, 16);
	}

	public static String TimeProcessToYear(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(0, 4);
	}

	public static String TimeProcessToMouth(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(5, 7);
	}

	public static String TimeProcessToDay(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(8, 10);
	}

	public static String CourseTime(String timeStr1, String timeStr2) {
		if (StringUtils.isEmpty(timeStr1) || StringUtils.isEmpty(timeStr2)) {
			return "";
		}

		String data = timeStr1.substring(5, 10).replace("-", "月") + "日";
		String time = timeStr1.substring(11, 16) + "-" + timeStr2.substring(11, 16);

		return data + " " + time;
	}

	public static String CourseDetailDay(String timeStr1) {
		if (StringUtils.isEmpty(timeStr1)) {
			return "";
		}

		String data = timeStr1.substring(5, 10).replace("-", "月") + "日";
		return data;
	}

	public static String CourseDetailTime(String timeStr1, String timeStr2) {
		if (StringUtils.isEmpty(timeStr1) || StringUtils.isEmpty(timeStr2)) {
			return "";
		}

		String data = timeStr1.substring(0, 10);
		String time = timeStr1.substring(11, 16) + "-" + timeStr2.substring(11, 16);

		return data + " " + time;
	}

	public static String MyIncomeDay(String timeStr1) {
		if (StringUtils.isEmpty(timeStr1)) {
			return "";
		}

		String data = timeStr1.substring(0, 7).replace("-", "年") + "月";
		return data;
	}

	public static String TimeNoPay(String timeStr) {
		if (StringUtils.isEmpty(timeStr)) {
			return "";
		}
		return timeStr.substring(0, 16);
	}

	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1]\\d{10}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		// String telRegex = "[1][3578]\\d{9}";//
		// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (!TextUtils.isEmpty(mobiles)){
			return mobiles.matches(telRegex);
		} else {
			return false;
		}
	}

	/**
	 * 清楚掉网页端 代码
	 */
	public static Spanned replaceHTML(String str) {
		return Html.fromHtml(str);
	}

	/**
	 *
	 * @Title: initialUpper
	 * @Description: 首字母大写,去除“_”并将之后的字母变成大写
	 * @param str
	 * @return String
	 * @throws
	 */
	public static String initialUpper(String str) {
		if (isEmpty(str))
			return "";
		StringBuffer sb = new StringBuffer();
		char[] cs = str.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (i == 0) {
				sb.append(Character.toUpperCase(cs[i]));
				continue;
			}
			if ((i - 1) > 0 && "_".equals(String.valueOf(cs[i - 1]))) {
				sb.append(Character.toUpperCase(cs[i]));
			} else if (!"_".equals(String.valueOf(cs[i]))) {
				sb.append(cs[i]);
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	public static String lowercaseFirstLetter(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		char firstLetter = str.charAt(0);
		firstLetter = Character.toLowerCase(firstLetter);
		return firstLetter + str.substring(1);
	}

	/**
	 * 判断字符是否integer
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符是否Long
	 */
	public static boolean isLong(String str) {
		try {
			Long.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符是否Float
	 */
	public static boolean isFloat(String str) {
		try {
			Float.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符是否Double
	 */
	public static boolean isDouble(String str) {
		try {
			Double.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 输出一个对象的所有属性和值
	 * @param model
	 * @return
	 */
	public static String classToString(Object model) {
		String result = "";
		Class<?> c = null;
		try {
			String className = model.getClass().getName();
			c = Class.forName(className);
			Field[] fields = c.getDeclaredFields();

			for (Field f : fields) {
				f.setAccessible(true);
			}
			for (Field f : fields) {
				String field = f.toString().substring(f.toString().lastIndexOf(".") + 1);
				result = result + (result.equals("") ? "" : "\n") + model + "." + field + " = " + f.get(model);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 计算排名后缀
	 * @param evalRanking
	 * @return
	 */
	public static String getSuffixRanking(String evalRanking) {
		try {
			if (evalRanking.equals("1")) {
				return "st";
			} else if (evalRanking.equals("2")) {
				return "nd";
			} else if (evalRanking.equals("3")) {
				return "rd";
			} else {
				return "th";
			}
		} catch (Exception e) {
			return "th";
		}
	}

}
