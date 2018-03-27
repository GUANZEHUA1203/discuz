package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class StringUtil {

	//	密码最小长度
	public static final int minLenght = 6;

	//	密码最大长度
	public static final int maxLenght = 12;

	//	大写字母、小写字母、特殊字符、数字 两种及两种以上组合匹配(登录密码)  
	private static final Pattern PATTERNPWD 
		= Pattern.compile("^(?![A-Z]*$)(?![a-z]*$)(?![0-9]*$)(?![^a-zA-Z0-9]*$)\\S{" + minLenght + "," + maxLenght + "}$");

	//	纯数字密码(设备密码)
	private static final Pattern PATTERNDEVPWD
		= Pattern.compile("^[0-9]{" + minLenght + "," + maxLenght + "}$");

	//	电话号码验证
	private static final Pattern PATTERNPHONE = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$");

	public static String getString(Object obj) {
		if (obj == null) {
			return "";
		}
		try {
			return String.valueOf(obj);
		} catch (Exception e) {
			return obj.toString();
		}
	}

	public static boolean isNotBlock(String str) {
		return str != null && str.trim().length() > 0;
	}

	public static Map<String, Object> StringToMap(String mapText) {
		if ((mapText == null) || (mapText.equals(""))) {
			return null;
		}
		mapText = mapText.substring(1);

		Map<String, Object> map = new HashMap<String, Object>();
		String[] text = mapText.split("\\|");
		for (String str : text) {
			String[] keyText = str.split("=");
			if (keyText.length >= 1) {
				String key = keyText[0];

				String value = keyText.length > 1 ? keyText[1] : null;
				if (value != null && value.charAt(0) == 'M') {
					Map<String, Object> map1 = StringToMap(value);
					map.put(key, map1);
				} else if (value != null && value.charAt(0) == 'L') {
					List<Object> list = StringToList(value);
					map.put(key, list);
				} else {
					map.put(key, value);
				}
			}
		}
		return map;
	}

	public static List<Object> StringToList(String listText) {
		if ((listText == null) || (listText.equals(""))) {
			return null;
		}
		listText = listText.substring(1);

		List<Object> list = new ArrayList<Object>();
		String[] text = listText.split("\\,");
		String listStr = "";
		boolean flag = false;
		for (String str : text) {
			if (!str.equals("")) {
				if (str.charAt(0) == 'M') {
					Map<String, Object> map = StringToMap(str);
					list.add(map);
				} else if ((str.charAt(0) == 'L') || (flag)) {
					flag = true;
					listStr = listStr + str + ",";
				} else {
					list.add(str);
				}
			}
			if (str.equals("")) {
				flag = false;
				List<Object> lists = StringToList(listStr);
				list.add(lists);
			}
		}
		return list;
	}

	public static Long getLong(Object obj) {
		String string = getString(obj);
		if (string.equals("")) return 0L;
		try {
			return Long.valueOf(string);
		} catch (Exception e) {
			return Long.getLong(string);
		}
	}

	public static Integer getInteger(Object obj) {
		String string = getString(obj);
		if (string.equals("")) return new Integer(0);
		try {
			return Integer.valueOf(string);
		} catch (Exception e) {
			return Integer.getInteger(string);
		}
	}

	public static Short getShort(Object obj) {
		String string = getString(obj);
		if (string.equals("")) return (short) 0;
		try {
			return Short.valueOf(string);
		} catch (Exception e) {
			return Short.parseShort(string);
		}
	}

	//	验证密码
	public static boolean pwdIsOk(String password) {
		return PATTERNPWD.matcher(getString(password)).matches();
	}

	//	验证设备密码
	public static boolean devPwdIsOk(String password) {
		return PATTERNDEVPWD.matcher(getString(password)).matches();
	}

	//	验证电话号码
	public static boolean phoneIsOk(String phone) {
		return PATTERNPHONE.matcher(getString(phone)).matches();
	}

	public static void main(String[] args) {
		System.out.println(phoneIsOk("18583723999"));
		System.out.println(phoneIsOk("15351341302"));
		System.out.println(phoneIsOk("18888888888"));
		System.out.println(phoneIsOk("10564648892"));
		System.out.println(phoneIsOk("891654811"));
		System.out.println("大小写字符串5位密码	" + pwdIsOk("1R4.d"));
		System.out.println("大小写字符串6位密码	" + pwdIsOk("1D4.'e"));
		System.out.println("大小写字符串空格6位密码	" + pwdIsOk("1D4. e"));
		System.out.println("大小写字符串12位密码	" + pwdIsOk("1D4.eD4.e1D4"));
		System.out.println("大小写字符串13位密码	" + pwdIsOk("1D4.e1D4.e1D4"));
		System.out.println("纯数字6位密码	" + pwdIsOk("211232"));
		System.out.println("纯大写6位密码	" + pwdIsOk("ATHIVT"));
		System.out.println("纯小写6位密码	" + pwdIsOk("evssfw"));
		System.out.println("纯字符6位密码	" + pwdIsOk("\\][,//';'"));
		System.out.println("纯字符6位密码	" + pwdIsOk("\\][,//';'"));
		System.out.println("纯数字5位密码	" + devPwdIsOk("21232"));
		System.out.println("纯数字6位密码	" + devPwdIsOk("211232"));
		System.out.println("纯数字12位密码	" + devPwdIsOk("211232211232"));
		System.out.println("纯数字13位密码	" + devPwdIsOk("2112322112321"));
	}

	public static String[] removeRepeat(String[] strArr) {
		if (strArr == null || strArr.length < 1) return null;
		List<String> list = Arrays.asList(strArr);
		Set<String> set = new HashSet<String>(list);
		return (String [])set.toArray(new String[0]);
	}
}
