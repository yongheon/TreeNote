package com.leeyh85.common.session;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component( "SessionLoginManager" )
@Scope( value = "session", proxyMode = ScopedProxyMode.INTERFACES )
public class SessionLoginManager implements Serializable { 

	private static final long serialVersionUID = -2540135225533363070L;
	
	@Autowired
	private HttpSession session;
	
	private String loginid;
	
	public String getLoginId() {
		return loginid;
	}
	
	public void setLoginId( String loginid ) {
		this.loginid = loginid;
	}
}