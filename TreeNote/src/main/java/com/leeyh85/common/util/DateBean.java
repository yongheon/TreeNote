package com.leeyh85.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * DateBean.java
 * @author SunAe Lim (sayim@haniln.com)
 * @since 2016.04.28
 * @version 1.0
 * <pre>
 * ==========================================================================
 *  SYSTEM            : 
 *  SUB SYSTEM        : 
 *  PROGRAM NAME      : 
 *  PROGRAM HISTORY   : 2016.04.28 최초 작성
 *  ==========================================================================
 * </pre> 
 * Copyright 2016 by HANILN All right reserved.
 */

public class DateBean {
	private GregorianCalendar calendar = null;

	public DateBean(String date) {
		int len = date.length();
		if (len == 8) {
			/* yyyyMMdd */
			calendar = new GregorianCalendar();
			calendar.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer
					.parseInt(date.substring(6, 8)));
		}else if (len == 10) {
			/* yyyy-MM-dd */
			calendar = new GregorianCalendar();
			calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
			calendar.add(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)) - 1);
			calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(date.substring(8, 10)));
		}
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public void setYear(int year) {
		calendar.set(Calendar.YEAR, year);
	}

	public int getMonth() {
		return calendar.get(Calendar.MONTH);
	}

	public void setMonth(int month) {
		calendar.set(Calendar.MONTH, month - 1);
	}

	public int getDay() {
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	public void setDay(int day) {
		calendar.set(Calendar.DAY_OF_YEAR, day);
	}

	public String toString() {
		return DateUtil.TO_CHAR(calendar.getTime());
	}

	public String toString(String pattern) {
		return DateUtil.TO_CHAR(pattern, calendar.getTime());
	}

	public Date getTime() {
		return calendar.getTime();
	}
}