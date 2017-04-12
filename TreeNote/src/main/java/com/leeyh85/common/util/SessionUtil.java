package com.leeyh85.common.util;

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * SessionUtil.java (쿠키 및 세션값 처리 유틸)
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

public class SessionUtil {
    static Logger log = Logger.getLogger(SessionUtil.class.getName());

    /**
     * 파라메터를 맵객체에 담아 리턴한다.
     * @param request
     */
    public static Entity<String, Object> getParam(HttpServletRequest request) throws Exception {
    	
    	Entity<String, Object> entity = new Entity<String, Object>();
    	
        //파라미터
        String key = null;
        String values[] = null;

        String strName = null;
        String strValue = null;

        String checkedData = null;
        List listCheckedData = new ArrayList();
        
        entity.put(Constants.LIST_DATA, listCheckedData);
        Entity entityCheckedData = null;
        Enumeration enumeration	= request.getParameterNames();
        
        /**
         * request parameter 를 추출한다.
         */
        while (enumeration.hasMoreElements()) {
        	key = (String)enumeration.nextElement();
            if (entity.containsKey(key)) {
            	//
            } else {
            	values = request.getParameterValues(key);
                if (values.length == 1) {
//					strValue = URLDecoder.decode(values[0], "UTF-8"); //% 기호가 들어간 경우 오류(이미 decoding이 되어있는지 체크, 로컬과 실서버 방식이 다를 수 있으므로 적용 후 테스트 해봐야함)
					strValue = values[0];
                    entity.put(key, strValue.trim());
                    if (key.equals("checkedData")) {
                        checkedData = strValue;
                    }
                } else {
                    entity.put(key, values);
                }
            }
        }
        
        /**
         * 검색조건을 추출한다.
         */
        if(checkedData != null && !checkedData.equals("")) {
            String[] arrCheckedLine = StringUtil.split(checkedData,Constants.LINE_SPLIT,log);
            String[] arrKey   = null;
            String[] arrValue = null;
            for(int i=0;i<arrCheckedLine.length;i++) {
            	/**
                 * 첫 line은 key정보
                 */
                if (i==0) {
                    arrKey = StringUtil.split(arrCheckedLine[i], Constants.COL_SPLIT,log);
                    entityCheckedData = new Entity();
                    listCheckedData.add(entityCheckedData);
                } else {
                	arrValue = StringUtil.split(arrCheckedLine[i], Constants.COL_SPLIT,log);
                    for (int j=0; j<arrKey.length; j++) {
                        entityCheckedData.put(arrKey[j], arrValue[j]);
                    }
                }
            }
        }
        
        /**
         * 쿠키값을 추출한다.
         */
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(int i=0,cnt=cookies.length; i<cnt; i++) {
                strName = cookies[i].getName ();
                strValue = URLDecoder.decode(cookies[i].getValue());
                entity.put(strName, strValue);
            }
        }
        
        entity.put("user_id",	request.getSession().getAttribute("user_id"));
        entity.put("user_name",	request.getSession().getAttribute("user_name"));

        /**
         * 한페이지당 조회건수셋팅한다.
         */
        entity.put("per_page", Constants.perPage);
        return entity;
    }
    
    /**
     * 쿠키를 맵객체에 담아 리턴한다.
     * @param request
     */
    public static Entity<Object, Object> getCookies(HttpServletRequest request,Logger log) throws Exception {
    	Entity<Object, Object> entity = new Entity<Object, Object>();
        String strName  = null;
        String strValue = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (int i=0,cnt=cookies.length; i<cnt; i++) {
                strName = cookies[i].getName ();
                strValue = cookies[i].getValue();
                strValue = URLDecoder.decode(cookies[i].getValue());
                entity.put(strName, strValue);
            }
        }
        return entity;
    }
}