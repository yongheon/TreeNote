package com.leeyh85.common.session;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component( "SessionManager" )
public class SessionManager {
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 세션에 저장된 객체를 가져온다.
	 * @param name
	 * @return Object
	 */
	public Object getAttribute(String name) {
		return session.getAttribute(name);
	}
	
	/**
	 * 세션에 객체를 저장한다.
	 * @param name
	 * @param obj
	 */
	public void setAttribute(String name, Object obj) {
		session.setAttribute(name, obj);
	}
	
	/**
	 * 세션에 저장된 객체를 제거한다.
	 * @param name
	 */
	public void removeAttribute(String name) {
		session.removeAttribute(name);
	}
	
	/**
	 * 세션을 무효화한다.
	 */
	public void invalidate() {
		session.invalidate();
	}
	
	/**
	 * 새로 생성된 세션인지 확인한다.
	 * @return boolean
	 */
	public boolean isNew() {
		return session.isNew();
	}
	
	/**
	 * 세션 아이디를 가져온다.
	 * @return String
	 */
	public String getId() {
		return session.getId();
	}
	
	/**
	 * HTTP 세션 객체를 가져온다.
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		return session;
	}
}