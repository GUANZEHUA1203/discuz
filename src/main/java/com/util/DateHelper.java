package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	public String miss(String date) throws Exception {
		String str = "";
		// 系统时间
		Date curdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		// 输入时间
		Date pardate = sdf.parse(date);

		Calendar par = Calendar.getInstance();
		par.setTime(pardate);
		Calendar cur = Calendar.getInstance();
		cur.setTime(curdate);
		int parday = par.get(Calendar.DAY_OF_MONTH);
		int curday = cur.get(Calendar.DAY_OF_MONTH);
		int parmon = par.get(Calendar.MONTH);
		int curmon = cur.get(Calendar.MONTH);
		int paryear = par.get(Calendar.YEAR);
		int curyear = cur.get(Calendar.YEAR);
		// 距系统时间30秒内
		Long Srange = ((curdate.getTime() - pardate.getTime()) / 1000);
		Double Mrange = Math.ceil(Double.parseDouble(Srange.toString()) / 60);
		Double Hrange = Math.ceil(Mrange / 60);

		if (curyear == paryear) {
			if (curmon == parmon) {
				if (curday == parday) {
					if (Srange <= 30) {
						str = Srange + "秒前";
					} else if (Srange <= 60) {
						str = "半分钟前";
					} else if (Mrange <= 30) {
						str = Mrange + "分钟前";
					} else if (Mrange <= 60) {
						str = "半小时前";
					} else {
						str = Hrange + "小时前";
					}
				} else if ((curday - parday) == 1) {
					str = (new SimpleDateFormat("昨天 HH:mm").format(pardate));
				} else if ((curday - parday) == 2) {
					str = (new SimpleDateFormat("前天 HH:mm").format(pardate));
				} else if ((curday - parday) <= 15) {
					str = (curday - parday) + "天前";
				} else {
					str = "半个月前";
				}
			} else if (curmon - parmon == 1) {
				str = (new SimpleDateFormat("上个月dd日").format(pardate));
			} else if ((curmon - parmon) <= 12) {
				if ((curmon - parmon) >= 6 && curday > parday) {
					str = "半年前";
				} else {
					str = (new SimpleDateFormat("MM月dd日").format(pardate));
				}
			}
		} else if ((curyear - paryear) == 1) {
			str = (new SimpleDateFormat("去年MM月dd日").format(pardate));
		} else if ((curyear - paryear) == 2) {
			str = (new SimpleDateFormat("前年MM月dd日").format(pardate));
		} else if ((curyear - paryear) >= 3) {
			str = (new SimpleDateFormat("yyyy年MM月dd日").format(pardate));
		}
		return str;
	}
}
