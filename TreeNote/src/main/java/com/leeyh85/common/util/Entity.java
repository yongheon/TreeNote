package com.leeyh85.common.util;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Entity.java
 * @author SunAe Lim (sayim@haniln.com)
 * @since 2016.04.28
 * @version 1.0
 * <pre>
 * ==========================================================================
 *  SYSTEM            : 
 *  SUB SYSTEM        : 
 *  PROGRAM NAME      : 
 *  PROGRAM HISTORY   : 2016.04.28 ���� �ۼ�
 *  ==========================================================================
 * </pre>
 * Copyright 2016 by HANILN All right reserved.
 */

public class Entity<K,V> extends HashMap<K,V>{

	private static final long serialVersionUID = -8824671614167035228L;
    
    public String getString(String key){
        Object obj = get(key);
        String result = (obj != null)? obj.toString():"";
        return result;
    }

    public int getInt(String key){
        Object obj = get(key);
        String result = (obj != null)? obj.toString():"";
        int intResult = Integer.parseInt( result );
        return intResult;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        String strKey = key.toString();
        strKey = strKey.toLowerCase();
        V value = super.get(strKey);
        if(value == null) 
        {
            value = (V)"";
        }
        return value;    //To change body of overridden methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public V put(K key, V value) {
        String strKey = key.toString();
        strKey = strKey.toLowerCase();
        super.put((K)strKey, value);
        value = get(key);
        return value;    //To change body of overridden methods use File | Settings | File Templates.
    }

    public String toString() {
        StringBuilder sbResult = new StringBuilder();
        Iterator<K> it = keySet().iterator();
        String key = null;
        String value = null;
        sbResult.append("[");
        while(it.hasNext()) {
            key = (String)it.next();
            value = getString(key);
            sbResult.append(key).append(",").append(value).append(":");
        }
        sbResult.append("]");
        return sbResult.toString();
    }
    
    public String toParam() {
        StringBuilder sbResult = new StringBuilder();
        Iterator<K> it = keySet().iterator();
        String key = null;
        String value = null;
        int isFirst = 0;
        while(it.hasNext()) {
            key = (String)it.next();
            value = getString(key);
            sbResult.append( isFirst == 0 ? "?" : "&" ).append(key).append("=").append(value);
            isFirst ++;
        }
        return sbResult.toString();
    }

    /**
     * ��ҹ��� ���о��� ����ϱ� ����HashMap�� �ִ� ���� Entity�� �ű��.
     * @param map
     * @return
     */
    public static Entity<Object,Object> hashToEntity(HashMap<Object,Object> map,Logger logger) {
    	Entity<Object,Object> entity = new Entity<Object, Object>();
        Object key = null;
        String strKey = null;
        String value = null;
        Iterator<Object> it = map.keySet().iterator();
        while(it.hasNext()) {
            key = it.next();
            value = map.get(key).toString();
            strKey = key.toString();
            entity.put(strKey.toLowerCase(),value);
            entity.put(strKey.toUpperCase(),value);
            entity.put(key,value);
        }
        return entity;
    }
    
    /**
     * ��ҹ��� ���о��� ����ϱ� ����HashMap�� �ִ� ���� Entity�� �ű��.
     * ����Ʈ������ ��ü
     * @param listMap
     * @return
     */
    public static ArrayList<Entity<Object,Object>> hashListToEntityList(List<HashMap<Object,Object>> listMap,Logger logger){
        ArrayList<Entity<Object,Object>> list = new ArrayList<Entity<Object,Object>>();
        HashMap<Object,Object> map = null;
        Entity<Object,Object> entity = null;
        for(int i=0;i<listMap.size();i++) {
            map = listMap.get(i);
            entity = hashToEntity(map,logger);
            list.add(entity);
        }
        return list;
    }
}