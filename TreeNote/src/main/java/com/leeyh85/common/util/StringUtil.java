package com.leeyh85.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * StringUtil.java
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

public class StringUtil extends org.apache.commons.lang.StringUtils {
	
	public static final Log logger = LogFactory.getLog("com.synap.util.StringUtil");

	public static String nvl(String str, String def) {		
		return defaultIfEmpty(str, def);
	}

	public static String nvl(String str) {
		return defaultString(str);
	}
    /**
     * 문자열을 특정 문자열을 기준으로 split을 하여 리턴한다.
     * @param src
     * @param splitChar
     * @return
     */
    public static String[] split(String src,String splitChar,Logger log){
        ArrayList list = new ArrayList();
        int index = -1;
        String s1=null;
        String s2=null;
        s2 = src;
        for(index = s2.indexOf(splitChar); ; index = s2.indexOf(splitChar)) {
            if(index < 0) {
                break;
            }
//            Log.debug("StringUtil.split:index="+index+",s2="+s2);
            s1 = s2.substring(0,index);
            s2 = s2.substring(index+splitChar.length());
            list.add(s1);
        }
        if(s2.length()>0) {
            list.add(s2);
        }
        logger.debug("src.length()="+src.length()+",src.lastIndexOf(splitChar)="+src.lastIndexOf(splitChar));
        if(src.length() == src.lastIndexOf(splitChar)+splitChar.length()) {
            list.add("");
        }
        String[] result = new String[list.size()];
        list.toArray(result);
//        Log.debug("StringUtil.split:result="+list);
        return result;
    }
    
    
    /**
     * 문자열을 특정 문자열을 기준으로 split을 하여 리턴한다.
     * @param src
     * @param splitChar
     * @return
     */
    public static String[] split(String src,String splitChar)
    {
        StringTokenizer st = new StringTokenizer( src, splitChar );
        String[] srcArray = null;
        ArrayList<String> srcList = new ArrayList<String>();
        while( st.hasMoreTokens() )
        {
            String ns = ((String)st.nextElement()).trim();
            if( !ns.equalsIgnoreCase("") ) 
            {
                srcList.add(ns);
            }
            
        }
        srcArray = srcList.toArray(new String[srcList.size()]);
        return srcArray;
    }

	/**
	 * @param String str
	 * @return String
	 * @author 
	 */
	public static String nvl(Object str) {
		if(str == null){
			return "";
		}else{
			return defaultString((String)str);
		}
	}

	public static String putComma(int num) {
		String dotU = num + "";
		String outU = "";

		int CommaFlag = dotU.length() % 3;

		// 나머지가 있을경우 (예: 12345 또는 12345678 ...)
		if (CommaFlag > 0) {
			// out에 dotU앞자리를 CommaFlag길이만큼 대입
			outU = dotU.substring(0, CommaFlag);
			// dotU의 길이가 3자리 초과이면 out문자끝에 콤마를 추가
			if (dotU.length() > 3) {
				outU += ",";
			}
		} else {
			outU = "";
		}

		for (int i = CommaFlag; i < dotU.length(); i += 3) {
			outU += dotU.substring(i, i + 3);
			if (i < dotU.length() - 3) {
				outU += ",";
			}
		}

		return outU;
	}

	public static String putComma(String num) {
		String dotU = num;
		String outU = "";
		String decimal = "";

		if (num.indexOf(".") > -1) {
			decimal = num.substring(num.indexOf("."));
			dotU = num.substring(0, num.indexOf("."));
		}

		int CommaFlag = dotU.length() % 3;

		// 나머지가 있을경우 (예: 12345 또는 12345678 ...)
		if (CommaFlag > 0) {
			// out에 dotU앞자리를 CommaFlag길이만큼 대입
			outU = dotU.substring(0, CommaFlag);
			// dotU의 길이가 3자리 초과이면 out문자끝에 콤마를 추가
			if (dotU.length() > 3) {
				outU += ",";
			}
		} else {
			outU = "";
		}

		for (int i = CommaFlag; i < dotU.length(); i += 3) {
			outU += dotU.substring(i, i + 3);
			if (i < dotU.length() - 3) {
				outU += ",";
			}
		}

		return outU + decimal;
	}

	public static String getLinkURL(String url) {
		String linkUrl = "";
		if (nvl(url).equals("")) {
			return "";
		} else if (url.toLowerCase().startsWith("http://")) {
			linkUrl = "<a href='" + url + "' target='_blank'>" + url + "</a>";
		} else {
			linkUrl = "<a href='http://" + url + "' target='_blank'>http://"
					+ url + "</a>";
		}

		return linkUrl;
	}

	public static String getHighLightTxt(String str, String highlight,
			String color) {
		if (str.length() > 0) {
			if (color.length() < 0)
				color = "black";
			String temp = "";
			temp = str.replace(highlight, "<font color='" + color + "'><b>"
					+ highlight + "</b></font>");
			return temp;
		} else {
			return str;
		}
	}

	// 접수번호에 "-"를 포함시키기
	public static String convertReceriptNo(String receiptNo) {

		String convReceiptNo = "";
		if (receiptNo == null)
			return nvl(receiptNo);
		if (receiptNo.length() != 13)
			return nvl(receiptNo);

		convReceiptNo = receiptNo.substring(0, 3) + "-"
				+ receiptNo.substring(3, 5) + "-" + receiptNo.substring(5, 13);

		return nvl(convReceiptNo);

	}

	public static String convertIssueNo(String issueNo) {

		String convIssueNo = "";
		if (issueNo == null)
			return nvl(issueNo);
		if (issueNo.length() != 12)
			return nvl(issueNo);

		convIssueNo = issueNo.substring(0, 3) + "-" + issueNo.substring(3, 5)
				+ "-" + issueNo.substring(5, 12);

		return nvl(convIssueNo);

	}

	public static String convertAuthCode(String authCode) {

		String convAuthCode = "";
		if (authCode == null)
			return nvl(authCode);
		if (authCode.length() != 8)
			return nvl(authCode);

		convAuthCode = authCode.substring(0, 4) + "-"
				+ authCode.substring(4, 8);

		return nvl(convAuthCode);

	}

	// DB에 저장된 사업자등록번호를 '-'를 포함한 문자열로 변환하여 리턴
	public static String convertCOID(String coid) {

		String convertCOID = "";
		if (coid == null)
			return nvl(coid);
		if (coid.length() != 10)
			return nvl(coid);

		convertCOID = coid.substring(0, 3) + "-" + coid.substring(3, 5) + "-"
				+ coid.substring(5, 10);

		return nvl(convertCOID);

	}

	// DB에 저장된 수출신고필증번호를 '-'를 포함한 문자열로 변환하여 리턴
	public static String convertExportNum(String expno) {

		String convertExpno = "";
		if (expno == null)
			return nvl(expno);
		if (expno.length() != 15)
			return nvl(expno);

		convertExpno = expno.substring(0, 3) + "-" + expno.substring(3, 5)
				+ "-" + expno.substring(5, 7) + "-"
				+ expno.substring(7, expno.length());

		return nvl(convertExpno);

	}

	// DB에 저장된 HSCode를 '-'를 포함한 문자열로 변환하여 리턴
	public static String convertHSCode(String hcd) {

		String convertHSCode = "";
		if (hcd == null)
			return nvl(hcd);
		if (hcd.length() != 10)
			return nvl(hcd);

		convertHSCode = hcd.substring(0, 4) + "." + hcd.substring(4, 6) + "-"
				+ hcd.substring(6, hcd.length());

		return nvl(convertHSCode);

	}

	// DB에 저장된 날짜를 '-'를 포함한 문자열로 변환하여 리턴
	public static String convertDateType(String date) {

		String convertDate = "";
		if (date == null)
			return nvl(date);
		if (date.length() != 8)
			return nvl(date);

		convertDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
				+ date.substring(6, date.length());

		return nvl(convertDate);

	}

	/**
	 * 두개의 문자열을 비교하여 동일하면 selected를 return한다.
	 * HTML SelectBox에서 사용한다.
	 * <option value="A" <%=StringUtil.selected("A",value)%>></option>
	 *
	 * @param target option tag에서 value 값
	 * @param value  option tag의 value값과 비교할 값
	 * @return 두개의 문자열이 동일하면 "selected", 아니면 ""을 return한다.
	 */
	public static String selected(String target, String value) {
		if(target.equals(value)) {
			return "selected";
		}
		else {
			return "";
		}
	}
	
	/**
	 * 두개의 문자열을 비교하여 동일하면 checked를 return
	 * @param target
	 * @param value
	 * @return
	 * @author 
	 */
	public static String checked(String target, String value) {
		if(target.equals(value)) {
			return "checked";
		}
		else {
			return "";
		}
	}
	
	/**
	 * 금액을 한국통화 형식으로 표시 
	 * @param money
	 * @return String 한국통화
	 * @author 
	 */
	public static String TO_CURRENCY(long money){
		return TO_CURRENCY(Locale.KOREA, money);
	}
	
	/**
	 * 금액을 지정한 Locale형식으로 표시
	 * @param money
	 * @return String 한국통화
	 * @author 
	 */
	public static String TO_CURRENCY(Locale locale, long money){
		try{
			return java.text.NumberFormat.getCurrencyInstance(locale).format(money);
		}
		catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 금액을 통화 형식으로 표시 
	 * @param money
	 * @return 통화형식
	 * @author 
	 */
	public static String TO_MONEY(long money){
		try {
			return java.text.NumberFormat.getInstance(Locale.KOREA).format(money);
		}
		catch (Exception e) {
			return "";
		}
	}
	
	/**
	 *  String 타입의 금액을 통화 형식으로 표시 
	 * @param money
	 * @return 통화형식
	 * @author 
	 */
	public static String TO_MONEY(String money){
		try {
			return java.text.NumberFormat.getInstance(Locale.KOREA).format(Long.parseLong(money));
		}
		catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * ISO8859-1 문자열을 입력받아 UTF-8 인코딩으로 변환 
	 * @param iso8859
	 * @return String
	 * @author 
	 */
	public static String TO_UTF8(String iso8859){
		
		if(iso8859 == null )
			return "";
		else
			try {
				return new String(iso8859.getBytes("ISO-8859-1"),"UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("입력된 ISO8859-1 을 UTF-8로 변환하여 리턴하는데 실패했습니다.", e);
				}
				return (String) iso8859;
			}
	}
	
	/**
	 * ISO8859-1 문자열을 입력받아 UTF-8 인코딩으로 변환 
	 * @param iso8859
	 * @return String
	 * @author 
	 */
	public static String TO_UTF8(Object iso8859){
		
		if(iso8859 == null )
			return "";
		else
			try {
				return new String(((String)iso8859).getBytes("ISO-8859-1"),"UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("입력된 ISO8859-1 을 UTF-8로 변환하여 리턴하는데 실패했습니다.", e);
				}
				return (String) iso8859;
			}
	}
	
	/**
	 * 문자열을 특정 인코딩을 입력받아 UTF-8 인코딩으로 변환
	 * @param str
	 * @param encoding
	 * @return Stringf
	 * @author 
	 */
	public static String TO_UTF8(String str, String encoding){
		
		if(str == null)
			return "";
		else
			try {
				return new String(str.getBytes(encoding),"UTF-8");
			}
			catch (UnsupportedEncodingException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("입력된 "+ encoding +" 을 UTF-8로 변환하여 리턴하는데 실패했습니다.", e);
				}
				return str;
			}
	}
	
	/**
	 * 문자열을 특정 fromEncoding 을 입력받아 toEncoding 으로  변환
	 * @param iso8859
	 * @param encoding
	 * @return Stringf
	 * @author 
	 */
	public static String changeEncoding(String source, String fromEncoding, String toEncoding){
		try {
			return new String(source.getBytes(fromEncoding), toEncoding);
		}
		catch (UnsupportedEncodingException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("입력된 "+ fromEncoding +" 을  " + toEncoding + " 로 변환하여 리턴하는데 실패했습니다.", e);
			}
			return source;
		}
	}

	/**
	 * VB Script 의 MID 함수처럼 사용
	 * 
	 * @param source
	 * @param start
	 * @param length
	 * @return String
	 * @author 
	 */
	public static String mid(String source, int start, int length){
		
		int endIndex = start + length - 1;
		
		if(source == null || source.length() < endIndex){
			return "";
		}
		
		return source.substring(start - 1, endIndex);
	}
	
	/**
	 * VB Script의 LEFT 함수처럼 사용
	 * @param source
	 * @param endIndex
	 * @return String
	 * @author 
	 */
	public static String left(String source, int endIndex){
		if(source == null || source.length() < endIndex){
			return "";
		}else{
			return source.substring(0, endIndex);
		}
	}
	
	/**
	 * VB Script의 RIGHT 함수처럼 사용
	 * @param source
	 * @param beginIndex
	 * @return String
	 * @author 
	 */
	public static String right(String source, int beginIndex){
		int len = source.length();
		if(source == null || len < beginIndex){
			return "";
		}else{
			return source.substring(len - beginIndex);
		}
	}
	
	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
	
	
	
	public String getCoCodeTF(String targetParam){
		/************************************************************************************************************/
		/* 
		 * 0,1 = 1 
		 * 1,2 = 0  
		 * 2,3 = 4
		 * Integer.toString(int a);    정수형을 문자형으로 
		 * Integer.parseInt(String n); 문자형을 정수형으로
		*/
		String result = "";
		String coCode = targetParam;	//1048126714
		String charOne = "";
		int iCnt = 0;
		if(!coCode.equals("")){
			if(!(coCode.substring(0,1)).equals("0")){
				
				for(int i=0; i<coCode.length(); i++){
					charOne = coCode.substring(i,i+1);
					try {
						if( Integer.parseInt(charOne) < 0 || Integer.parseInt(charOne) > 9 ){ 
							iCnt = iCnt;
						}else{ 
							iCnt = iCnt + 1;
						} 
					} catch (Exception e){
						logger.debug("[[예외사항]]");
						//e.printStackTrace();
					}
				}
				
				if(iCnt == coCode.length()){
					int temp, mod, id_1, id_2, id_3, id_4, id_5, id_6, id_7, id_8, id_9, id_10 = 0;
					id_1 = Integer.parseInt(coCode.substring(0,1));
					id_2 = Integer.parseInt(coCode.substring(1,2));
					id_3 = Integer.parseInt(coCode.substring(2,3));
					id_4 = Integer.parseInt(coCode.substring(3,4));
					id_5 = Integer.parseInt(coCode.substring(4,5));
					id_6 = Integer.parseInt(coCode.substring(5,6));
					id_7 = Integer.parseInt(coCode.substring(6,7));
					id_8 = Integer.parseInt(coCode.substring(7,8));
					id_9 = Integer.parseInt(coCode.substring(8,9));
					id_10 = Integer.parseInt(coCode.substring(9,10));
					temp = (id_1 * 1) % 10 
							+ (id_2 * 3) % 10
							+ (id_3 * 7) % 10
							+ (id_4 * 1) % 10
							+ (id_5 * 3) % 10
							+ (id_6 * 7) % 10
							+ (id_7 * 1) % 10
							+ (id_8 * 3) % 10
							+ (id_9 * 5) % 10 
							+ Integer.parseInt((Integer.toString(((id_9 * 5) / 10)).substring(0,1)));
					mod = temp % 10;
					if(id_10 == (10 - mod)){
						logger.debug("[[TRUE11]]");
						result = "1";
					}else{
						if((mod == 0) && (id_10 == 0)){
							logger.debug("[[TRUE22]]");
							result = "1";
						}else{
							logger.debug("[[FALSE]]");
							result = "2";
						}
					}
				}else{
					logger.debug("[[FALSE]]");
					result = "2";
				}
			}else{
				logger.debug("[[FALSE]]");
				result = "2";
			}
		}
		/*************************************************************************************************************/		
		return result;
	}
}