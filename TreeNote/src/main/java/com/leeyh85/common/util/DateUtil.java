package com.leeyh85.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * DateUtil.java
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

public class DateUtil {

	public final static int SUNDAY = 1;

	public final static int MONDAY = 2;

	public final static int TUESDAY = 3;

	public final static int WEDNESDAY = 4;

	public final static int THURSDAY = 5;

	public final static int FRIDAY = 6;

	public final static int SATURDAY = 7;

	private static String timeSeparator = ":";

	private static String dateSeparator = ".";

	public static String convert(String date) {
		return convert(date, false);
	}
	
	/**
	 * 오늘 날짜 정보를 yyyyMMdd.HHmmss.SSS 형태로 반환한다. 파일 업로드 구현시 사용
	 * @return
	 */
    public static String getDateString()
    {
        SimpleDateFormat dFormatter = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");
        Calendar aCalendar = Calendar.getInstance();return dFormatter.format(aCalendar.getTime() ) ;
    }
  
    /**
     * 오늘 날짜 정보를 yyyyMMdd 형태로 반환한다. 파일 업로드 구현시 사용
     * @return
     */
    public static String getTodayString()
    {
        SimpleDateFormat dFormatter = new SimpleDateFormat("yyyyMMdd");
        Calendar aCalendar = Calendar.getInstance();
        return dFormatter.format(aCalendar.getTime() ) ;
    }
    
    /**
     * 오늘 날짜 정보를 yyyyMMdd 형태로 반환한다. 파일 업로드 구현시 사용
     * @return
     */
    public static String getTodayPathString()
    {
        SimpleDateFormat dFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Calendar aCalendar = Calendar.getInstance();
        return dFormatter.format(aCalendar.getTime() ) ;
    }

	/**
	 * yyyyMMdd 형식의 날짜 String을 입력한 형태로 바꾸어 리턴한다. 예) formatDate("20040101","/")
	 * --> 2004/01/01 로 리턴
	 * @param strDate string date (20040101)
	 * @param format format 구분자
	 * @return String
	 */
	public static String formatDate(String date, String ch) {
		if (date == null || date.trim().length() == 0) {
			return "";
		}

		String str = date.trim();
		String yyyy = "";
		String mm = "";
		String dd = "";

		if (str.length() >= 8) {
			yyyy = str.substring(0, 4);
			if (yyyy.equals("0000")) {
				return "";
			}

			mm = str.substring(4, 6);
			if (mm.equals("00")) {
				return yyyy;
			}

			dd = str.substring(6, 8);
			if (dd.equals("00")) {
				return yyyy + ch + mm;
			}

			return yyyy + ch + mm + ch + dd;
		}
		else if (str.length() == 6) {
			yyyy = str.substring(0, 4);
			if (yyyy.equals("0000")) {
				return "";
			}

			mm = str.substring(4, 6);
			if (mm.equals("00")) {
				return yyyy;
			}

			return yyyy + ch + mm;
		}
		else if (str.length() == 4) {
			yyyy = str.substring(0, 4);
			if (yyyy.equals("0000")) {
				return "";
			}
			else {
				return yyyy;
			}
		}
		else {
			return "";
		}
	}

	/**
	 * yyyyMMdd 형식의 날짜문자열을 KR Format으로 돌려준다 <br>
	 *
	 * <pre>
	 *     ex) 20030405 -&gt; 2003년 4월 5일
	 * </pre>
	 *
	 * @param date yyyyMMdd 형식의 날짜문자열
	 * @return 변환된 문자열
	 */

	public static String formatDateKR(String date) {
		if (date == null || date.trim().length() == 0 || date.trim().length() != 8) {
			return "";
		}

		String str = date.trim();
		String yyyy = "";
		String mm = "";
		String dd = "";

		yyyy = str.substring(0, 4);
		mm = str.substring(4, 6);
		dd = str.substring(6);

		mm = mm.startsWith("0") ? (" " + mm.substring(1)) : mm;
		dd = dd.startsWith("0") ? (" " + dd.substring(1)) : dd;

		return yyyy + "년 " + mm + "월 " + dd + "일 ";
	}

	/**
	 * HH24MISS 형식의 시간문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다 <br>
	 *
	 * <pre>
	 *     ex) 151241, ch(/) -&gt; 15/12/31
	 * </pre>
	 *
	 * @param str HH24MISS 형식의 시간문자열
	 * @param ch 구분자
	 * @return 변환된 문자열
	 */

	public static String formatTime(String str, String ch) {
		if (str == null || str.length() == 0) {
			return "";
		}
		if (str.length() == 6) {
			return str.substring(0, 2) + ch + str.substring(2, 4) + ch + str.substring(4, 6);
		}
		else {
			return "";
		}
	}

	/**
	 * 현재(한국기준) 시간정보를 얻는다. <BR>
	 * (예) 입력파리미터인 format string에 "yyyyMMddhh"를 셋팅하면 1998121011과 같이 Return. <BR>
	 * (예) format string에 "yyyyMMddHHmmss"를 셋팅하면 19990114232121과 같이 (예) 밀리세컨드는
	 * yyyyMMddHHmmssSSS 0~23시간 타입으로 Return. <BR>
	 * String CurrentDate = BaseUtil.getKST("yyyyMMddHH");<BR>
	 *
	 * @param format 얻고자하는 현재시간의 Type
	 * @return string 현재 한국 시간.
	 */
	public static String getCurrentTime(String format) {
		// 1hour(ms) = 60s * 60m * 1000ms
		int millisPerHour = 60 * 60 * 1000;
		SimpleDateFormat fmt = new SimpleDateFormat(format);

		SimpleTimeZone timeZone = new SimpleTimeZone(9 * millisPerHour, "KST");
		fmt.setTimeZone(timeZone);

		String str = fmt.format(new java.util.Date(System.currentTimeMillis()));

		return str;
	}

	/**
	 * 현재(한국기준) 날짜정보를 얻는다. <BR>
	 * 표기법은 yyyy-mm-dd <BR>
	 * @param N/A
	 * @return String yyyymmdd형태의 현재 한국시간. <BR>
	 */
	public static String getCurrentDate() {
		return getCurrentDate("");
	}

	/**
	 * 현재(한국기준) 년도정보를 얻는다. <BR>
	 * 표기법은 yyyy <BR>
	 * @param N/A
	 * @return String yyyy형태의 현재 한국년도. <BR>
	 */
	public static String getCurrentYear() {
        String date = getCurrentDate("");
        String year = date.substring(0,4);
		return year;
	}

	public static String getOneMonthBeforeDate()
	{
	    Date todayDate = Calendar.getInstance().getTime();
	    Date oneMonthBeforeDate = getAfterDates( todayDate, Calendar.DAY_OF_MONTH, -10 );
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.format(oneMonthBeforeDate);
	}
	
	public static String getOneMonthAfterDate()
	{
	    Date todayDate = Calendar.getInstance().getTime();
	    Date oneMonthBeforeDate = getAfterDates( todayDate, Calendar.DAY_OF_MONTH, +10 );
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.format(oneMonthBeforeDate);
	}

	/**
	 * 현재(한국기준) 날짜정보를 얻는다. <BR>
	 * 표기법은 yyyy-mm-dd <BR>
	 * @param N/A
	 * @return String yyyymmdd형태의 현재 한국시간. <BR>
	 */
	public static String getCurrentDate(String dateType) {
		Calendar aCalendar = Calendar.getInstance();

		int year = aCalendar.get(Calendar.YEAR);
		int month = aCalendar.get(Calendar.MONTH) + 1;
		int date = aCalendar.get(Calendar.DATE);
		String strDate = Integer.toString(year)
				+ ((month < 10) ? "0" + Integer.toString(month) : Integer.toString(month))
				+ ((date < 10) ? "0" + Integer.toString(date) : Integer.toString(date));

		if (!"".equals(dateType)) {
			strDate = convertDate(strDate, "yyyyMMdd", dateType);
		}

		return strDate;
	}
	
	public static java.sql.Date getDate( String dateString )
	{
		java.sql.Date date = null;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = new java.sql.Date( formatter.parse(dateString).getTime() );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		return date;
	}

	/**
	 * 입력한 날짜 기준으로 몇일 전,후 (주의)입력날짜는 구분자가 없는 string형
	 * @param dayString string date (19991002)
	 * @param day 가감하려고 하는 일자
	 * @return String
	 */
	public static String getDateWithSpan(String dayString, int day) {
		SimpleDateFormat m_formatter = new SimpleDateFormat("yyyyMMdd");
		int y = Integer.parseInt(dayString.substring(0, 4));
		int m = Integer.parseInt(dayString.substring(4, 6));
		int d = Integer.parseInt(dayString.substring(6, 8));

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(y, m - 1, d + day);

		return m_formatter.format(aCalendar.getTime());
	}

	/**
	 * 날짜형태의 String yyyyMMddHHmmss or yyyyMMdd 을 yyyy.MM.dd HH:mm:ss 또는
	 * yyyy.MM.dd 으로 변경
	 * @param date
	 * @param IS_TIME
	 * @return
	 * @author yudok2
	 */
	public static String convert(String date, boolean IS_TIME) {
		String result = null;

		if (date.length() == 8) {
			result = date.substring(0, 4) + dateSeparator + date.substring(4, 6) + dateSeparator + date.substring(6);
		}
		else if (date.length() == 14) {
			if (IS_TIME) {
				result = date.substring(0, 4) + dateSeparator + date.substring(4, 6) + dateSeparator
						+ date.substring(6, 8) + " " + date.substring(8, 10) + timeSeparator + date.substring(10, 12)
						+ timeSeparator + date.substring(12);
			}
			else {
				result = date.substring(0, 4) + dateSeparator + date.substring(4, 6) + dateSeparator
						+ date.substring(6, 8);
			}
		}

		return result;
	}

	/**
	 * <pre>
	 *      날짜형태의 String의 날짜 포맷을 [yyyy-MM-dd HH:mm:ss]에서 [yyyyMMddHHmmss]으로(default value) 변경해 주는 메소드
	 * </pre>
	 *
	 * @param String strSource 바꿀 날짜 String
	 * @return 소스 String의 날짜 포맷을 변경한 String
	 */
	public static String convertDate(String strSource) {
		return convertDate(strSource, "", "", "");
	}

	/**
	 * <pre>
	 *      날짜형태의 String의 날짜 포맷만을 변경해 주는 메소드
	 * </pre>
	 *
	 * @param String strSource 바꿀 날짜 String
	 * @param String toDateFormat 원하는 날짜 형태
	 * @return 소스 String의 날짜 포맷을 변경한 String
	 */
	public static String convertDate(String strSource, String toDateFormat) {
		return convertDate(strSource, "", toDateFormat, "");
	}

	/**
	 * <pre>
	 *      날짜형태의 String의 날짜 포맷만을 변경해 주는 메소드
	 * </pre>
	 *
	 * @param String strSource 바꿀 날짜 String
	 * @param String fromDateFormat 기존의 날짜 형태
	 * @param String toDateFormat 원하는 날짜 형태
	 * @return 소스 String의 날짜 포맷을 변경한 String
	 */
	public static String convertDate(String strSource, String fromDateFormat, String toDateFormat) {
		return convertDate(strSource, fromDateFormat, toDateFormat, "");
	}

	/**
	 * <pre>
	 *      날짜형태의 String의 날짜 포맷 밑 TimeZone을 변경해 주는 메소드
	 * </pre>
	 *
	 * @param String strSource 바꿀 날짜 String
	 * @param String fromDateFormat 기존의 날짜 형태
	 * @param String toDateFormat 원하는 날짜 형태
	 * @param String strTimeZone 변경할 TimeZone(""이면 변경 안함)
	 * @return 소스 String의 날짜 포맷을 변경한 String
	 */
	public static String convertDate(String strSource, String fromDateFormat, String toDateFormat, String strTimeZone) {
		SimpleDateFormat simpledateformat = null;
		Date date = null;
		if (StringUtil.nvl(strSource).trim().equals("")) {
			return "";
		}
		if (StringUtil.nvl(fromDateFormat).trim().equals("")) {
			fromDateFormat = "yyyyMMddHHmmss"; // default값
		}
		if (StringUtil.nvl(toDateFormat).trim().equals("")) {
			toDateFormat = "yyyy-MM-dd HH:mm:ss"; // default값
		}
		try {
			simpledateformat = new SimpleDateFormat(fromDateFormat);
			date = simpledateformat.parse(strSource);
			if (!"".equals(StringUtil.nvl(strTimeZone).trim())) {
				simpledateformat.setTimeZone(TimeZone.getTimeZone(strTimeZone));
			}
			simpledateformat = new SimpleDateFormat(toDateFormat);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		finally {
		}

		return simpledateformat.format(date);

	}

	/**
	 * 입력받은 날짜를 pattern 형식으로 출력
	 * @param pattern
	 * @param date
	 * @return String
	 */
	public static String TO_CHAR(String pattern, Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 현재 날짜를 pattern 형식으로 출력
	 * @param pattern
	 * @return
	 */
	public static String TO_CHAR(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	/**
	 * 입력받은 날짜를 yyyyMMdd형식으로 출력
	 * @param date
	 * @return String
	 */
	public static String TO_CHAR(Date date) {
		return TO_CHAR("yyyyMMdd", date);
	}
	
	
	public static Date getAfterDates(int TYPE, int after){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(TYPE, after);
		return calendar.getTime();
	}

	public static Date getAfterDates(Date fromDate, int TYPE, int after){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fromDate);
		calendar.add(TYPE, after);
		return calendar.getTime();
	}
	
	public static Date getAfterDates(int year, int month, int day, int TYPE, int after){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(year, month, day);
		calendar.add(TYPE, after);
		return calendar.getTime();
	}
	
	/**
	 * 현재날짜에 day만큼의 일수를 더한 Date 형식을 반환<br/>
	 * 음수가 들어오면 day만큼의 일수를 빼서 Date 형식으로 반환
	 * @param termOfdays
	 * @return Date
	 * @author 정혁준
	 */
	public static Date getAfterDays(int termOfdays){
		return getAfterDates(Calendar.DAY_OF_YEAR,termOfdays);
	}
	
	/**
	 * 현재날짜에 month만큼의 개월수를 더한 Date 형식을 반환<br/>
	 * 음수가 들어오면 month만큼의 개월수를 빼서 Date 형식으로 반환
	 * @param days
	 * @return Date
	 * @author 정혁준
	 */
	public static Date getAfterMonths(int termOfmonths) {
		return getAfterDates(Calendar.MONTH, termOfmonths);
	}
	
	/**
	 * 현재날짜에 year만큼의 년수를 더한 Date 형식을 반환<br/>
	 * 음수가 들어오면 year만큼의 년수를 빼서 Date 형식으로 반환
	 * @param days
	 * @return Date
	 * @author 정혁준
	 */
	public static Date getAfterYears(int termOfyears) {
		return getAfterDates(Calendar.YEAR, termOfyears);
	}
	
	/**
	 * Date 를 입력받아 day에 days 만큼 더한 Date를 반환
	 * @param fromDate
	 * @param days
	 * @return Date = (Date fromDate + days)
	 * @author 정혁준
	 */
	public static Date getAfterDays(Date fromDate, int days){
		return getAfterDates(fromDate, Calendar.DAY_OF_YEAR, days);
	}
	
	/**
	 * Date 를 입력받아 month에 months 만큼 더한 Date를 반환
	 * @param fromDate
	 * @param months
	 * @return Date = (Date fromDate + months)
	 * @author 정혁준
	 */
	public static Date getAfterMonths(Date fromDate, int months){
		return getAfterDates(fromDate, Calendar.MONTH, months);
	}
	
	/**
	 * Date 를 입력받아 year에 years 만큼 더한 Date를 반환
	 * @param fromDate
	 * @param years
	 * @return Date = (Date fromDate + months)
	 * @author 정혁준
	 */
	public static Date getAfterYears(Date fromDate, int years){
		return getAfterDates(fromDate, Calendar.YEAR, years);
	}
	
	/**
	 * String 형 Date YYYYMMDD를 입력받아 day에 days 만큼 더한 Date를 반환
	 * @param fromDate String YYYYMMDD
	 * @param days
	 * @return Date = (String fromDate + days)
	 * @author 정혁준
	 */
	public static Date getAfterDays(String fromDate, int days){
		DateBean date = new DateBean(fromDate);
		return getAfterDates(date.getYear(), date.getMonth(), date.getDay(), Calendar.DAY_OF_YEAR, days);
	}
	
	/**
	 * String 형 Date YYYYMMDD를 입력받아 month에 months 만큼 더한 Date를 반환
	 * @param fromDate String YYYYMMDD
	 * @param months
	 * @return Date = (String fromDate + months)
	 * @author 정혁준
	 */
	public static Date getAfterMonths(String fromDate, int months){
		DateBean date = new DateBean(fromDate);
		return getAfterDates(date.getYear(), date.getMonth(), date.getDay(), Calendar.MONTH, months);
	}
	
	/**
	 * String 형 Date YYYYMMDD를 입력받아 year에 years 만큼 더한 Date를 반환
	 * @param fromDate String YYYYMMDD
	 * @param months
	 * @return Date = (String fromDate + years)
	 * @author 정혁준
	 */
	public static Date getAfterYears(String fromDate, int years){
		DateBean date = new DateBean(fromDate);
		return getAfterDates(date.getYear(), date.getMonth(), date.getDay(), Calendar.YEAR, years);
	}

	/**
	 * 현재날짜에 day만큼의 일수를 더해 yyyyMMdd 형식으로 출력
	 *
	 * @param days
	 * @return String
	 * @author 정혁준
	 */
	public static String AFTER_DAYS(int days) {
		return TO_CHAR(getAfterDays(days));
	}
	
	/**
	 * 현재날짜에 months만큼의 개월수를 더해 yyyyMMdd 형식으로 출력
	 *
	 * @param months
	 * @return String
	 * @author 정혁준
	 */
	public static String AFTER_MONTHS(int months) {
		return TO_CHAR(getAfterMonths(months));
	}
	
	/**
	 * 현재날짜에 years만큼의 년수를 더해 yyyyMMdd 형식으로 출력
	 *
	 * @param years
	 * @return String
	 * @author 정혁준
	 */
	public static String AFTER_YEARS(int years) {
		return TO_CHAR(getAfterYears(years));
	}
	
	/**
	 * 현재날짜에 day만큼의 일수를 뺀 후 yyyyMMdd 형식으로 출력
	 *
	 * @param days
	 * @return String
	 * @author 정혁준
	 */
	public static String BEFORE_DAYS(int days) {
		return TO_CHAR(getAfterDays(days * -1));
	}
	
	/**
	 * 현재날짜에 months만큼의 월수를 뺀 후 yyyyMMdd 형식으로 출력
	 *
	 * @param months
	 * @return String
	 * @author 정혁준
	 */
	public static String BEFORE_MONTHS(int months) {
		return TO_CHAR(getAfterDays(months * -1));
	}
	
	/**
	 * 현재날짜에 years만큼의 년수를 뺀 후 yyyyMMdd 형식으로 출력
	 *
	 * @param years
	 * @return String
	 * @author 정혁준
	 */
	public static String BEFORE_YEARS(int years) {
		return TO_CHAR(getAfterDays(years * -1));
	}

	/**
	 * 현재날짜에 day만큼의 일수를 더해 pattern형식으로 출력
	 *
	 * @param pattern
	 * @param days
	 * @return String
	 * @author 정혁준
	 * 
	 */
	public static String AFTER_DAYS(int days, String pattern) {
		return TO_CHAR(pattern, getAfterDays(days));
	}
	
	/**
	 * 현재날짜에 months만큼의 개월수를 더해 pattern형식으로 출력
	 *
	 * @param pattern
	 * @param months
	 * @return String
	 * @author 정혁준
	 * 
	 */
	public static String AFTER_MONTHS(int months, String pattern) {
		return TO_CHAR(pattern, getAfterMonths(months));
	}
	
	/**
	 * 현재날짜에 years만큼의 년수를 더해 pattern형식으로 출력
	 *
	 * @param pattern
	 * @param years
	 * @return String
	 * @author 정혁준
	 * 
	 */
	public static String AFTER_YEARS(int years, String pattern) {
		return TO_CHAR(pattern, getAfterYears(years));
	}
	
	/**
	 * 현재날짜에 day만큼의 일수를 뺀 후  pattern형식으로 출력
	 *
	 * @param pattern
	 * @param days
	 * @return String
	 * @author 정혁준
	 */
	public static String BEFORE_DAYS(int days, String pattern) {
		return TO_CHAR(pattern, getAfterDays(days * -1));
	}
	
	/**
	 * 현재날짜에 months만큼의 일수를 뺀 후  pattern형식으로 출력
	 *
	 * @param pattern
	 * @param months
	 * @return String
	 * @author 정혁준
	 */
	public static String BEFORE_MONTHS(int months, String pattern) {
		return TO_CHAR(pattern, getAfterMonths(months * -1));
	}
	
	/**
	 * 현재날짜에 years만큼의 일수를 뺀 후  pattern형식으로 출력
	 *
	 * @param pattern
	 * @param years
	 * @return String
	 * @author 정혁준
	 */
	public static String BEFORE_YEARS(int years, String pattern) {
		return TO_CHAR(pattern, getAfterYears(years * -1));
	}
	
	public static String AFTER_DAYS(Date fromDate, int days, String pattern){
		return TO_CHAR(pattern, getAfterDates(fromDate, Calendar.DAY_OF_YEAR, days));
	}
	public static String AFTER_MONTHS(Date fromDate, int months, String pattern){
		return TO_CHAR(pattern, getAfterDates(fromDate, Calendar.MONTH, months));
	}
	public static String AFTER_YEARS(Date fromDate, int years, String pattern){
		return TO_CHAR(pattern, getAfterDates(fromDate, Calendar.YEAR, years));
	}
	
	
	public static String BEFORE_DAYS(Date fromDate, int days, String pattern){
		return TO_CHAR(pattern, getAfterDates(fromDate, Calendar.DAY_OF_YEAR, days*-1));
	}
	public static String BEFORE_MONTHS(Date fromDate, int months, String pattern){
		return TO_CHAR(pattern, getAfterDates(fromDate, Calendar.MONTH, months*-1));
	}
	public static String BEFORE_YEARS(Date fromDate, int years, String pattern){
		return TO_CHAR(pattern, getAfterDates(fromDate, Calendar.YEAR, years*-1));
	}
	
	public static Date AFTER_DAYS(Date fromDate, int days){
		return getAfterDates(fromDate, Calendar.DAY_OF_YEAR, days);
	}
	public static Date AFTER_MONTHS(Date fromDate, int months){
		return getAfterDates(fromDate, Calendar.MONTH, months);
	}
	public static Date AFTER_YEARS(Date fromDate, int years){
		return getAfterDates(fromDate, Calendar.YEAR, years);
	}
	
	public static Date BEFORE_DAYS(Date fromDate, int days){
		return getAfterDates(fromDate, Calendar.DAY_OF_YEAR, days*-1);
	}
	public static Date BEFORE_MONTHS(Date fromDate, int months){
		return getAfterDates(fromDate, Calendar.MONTH, months*-1);
	}
	public static Date BEFORE_YEARS(Date fromDate, int years){
		return getAfterDates(fromDate, Calendar.YEAR, years*-1);
	}
	
	
	/**
	 * 입력한 날짜 기준으로 해당월의 마지막날짜 리턴
	 * @param dayString string date (19991002)
	 * @return String
	 */
	public static String getLastDate(String dayString) {
		SimpleDateFormat m_formatter = new SimpleDateFormat("yyyyMMdd");
		int y = Integer.parseInt(dayString.substring(0, 4));
		int m = Integer.parseInt(dayString.substring(4, 6));
		int d = Integer.parseInt(dayString.substring(6, 8));

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(y, m - 1, d);
		int lastday = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		aCalendar.set(y, m - 1, lastday);
		return m_formatter.format(aCalendar.getTime());
	}
	
	/**
	 * 입력한 날짜 기준으로 다음월의 마지막날짜 리턴
	 * @param dayString string date (19991002)
	 * @return String
	 */
	public static String getNextLastDate(String dayString) {
		SimpleDateFormat m_formatter = new SimpleDateFormat("yyyyMMdd");
		int y = Integer.parseInt(dayString.substring(0, 4));
		int m = Integer.parseInt(dayString.substring(4, 6));
		int d = Integer.parseInt(dayString.substring(6, 8));

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(y, m, d);
		int lastday = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		aCalendar.set(y, m, lastday);
		return m_formatter.format(aCalendar.getTime());
	}
	
	/**
	 * 입력한 날짜 기준으로 해당일의 시작요일을 리턴
	 * @param dayString string date (19991002)
	 * @return String
	 */
	public static String getWeekInfo(String dayString) {
		SimpleDateFormat m_formatter = new SimpleDateFormat("yyyyMMdd");
		int y = Integer.parseInt(dayString.substring(0, 4));
		int m = Integer.parseInt(dayString.substring(4, 6));
		int d = Integer.parseInt(dayString.substring(6, 8));

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(y, m - 1, d);
		
		int startWeek = aCalendar.get(Calendar.DAY_OF_WEEK);
		return startWeek+"";
	}
	
}
