package com.leeyh85.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import com.leeyh85.common.session.SessionManager;


/**
 * 	@author KYJ
 *	사용자 권한 체크 
 */

public class UserAuthorityInterCeptor extends HandlerInterceptorAdapter {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	SessionManager session;
	
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		log.debug("requestURI"+request.getRequestURI());

		/*
		 * 상단 메뉴 아이디 쿠키로 처리 
		 * 세션으로 처리시 로그 아웃 되었을 경우 값을 잃어 버림(페이지 자동 이동시 문제)
		 * 쿠키는 뒤로 가기 문제점 
		 */
//		if (request.getParameter("top_menu_id") != null) {
//			Cookie cookie = new Cookie("top_menu_id", request.getParameter("top_menu_id"));
//			cookie.setPath("/");
//			response.addCookie(cookie);
//			session.setAttribute("top_menu_id", request.getParameter("top_menu_id"));
//		}

		String szUri = request.getRequestURI();
		String szQueryString = request.getQueryString();
		
		if (szUri.indexOf("/login") == -1) {
			if(request.getSession().getAttribute("admin_id") == null) {

				if (szQueryString != null && !"null".equals(szQueryString)) {
					response.sendRedirect("/login.do?forward_uri="+szUri+"&queryString="+szQueryString); //로그인이동
				} else {
					response.sendRedirect("/login.do?forward_uri="+szUri);
				}
			}	
		}
		
//		int menu001 = (Integer) session.getAttribute("menu001");
//		
//		if(session.getAttribute("admin_id") != null){
//			if((Integer) session.getAttribute("menu001")<1){
//				throw new HException(new Exception(), "권한 체크", IDCConst.EXCEPTION_SECTION_AUTH);
//			}
//		}


//		if(request.getSession().getAttribute("user_id") != null) {
//			/*
//			 * 권한 체크 
//			 */
//			// 센터 권한
//			if (szUri.indexOf("/center") != -1) {
//				if (!"4".equals(session.getAttribute("cen_level"))) {
//					throw new HException(new Exception(), "권한 체크", IDCConst.EXCEPTION_SECTION_AUTH);
//				}
//				// 청구 권한
//			} else if (szUri.indexOf("/invoice") != -1) {
//				if (szUri.indexOf("/invoice/pay") != -1) {
//					if (!"4".equals(session.getAttribute("pa_level"))) {
//						throw new HException(new Exception(), "권한 체크", IDCConst.EXCEPTION_SECTION_AUTH);
//					}
//				} else {
//					if (!"4".equals(session.getAttribute("in_level"))) {
//						throw new HException(new Exception(), "권한 체크", IDCConst.EXCEPTION_SECTION_AUTH);
//					}
//				}
//				// 계정권한
//			} else if (szUri.indexOf("/item") != -1) {
//				log.debug("계정권한 =" + session.getAttribute("au_level"));
//				if (!"4".equals(session.getAttribute("au_level"))) {
//					throw new HException(new Exception(), "권한 체크", IDCConst.EXCEPTION_SECTION_AUTH);
//				}
//				// 유저 권한
//			} else if (szUri.indexOf("/admin") != -1) {
//				if (!"4".equals(session.getAttribute("us_level"))) {
//					throw new HException(new Exception(), "권한 체크", IDCConst.EXCEPTION_SECTION_AUTH);
//				}
//				// 통계 권한
//			} else if (szUri.indexOf("/statistic") != -1) {
//				if (!"4".equals(session.getAttribute("st_level"))) {
//					throw new HException(new Exception(), "권한 체크", IDCConst.EXCEPTION_SECTION_AUTH);
//				}
//			} 
//		}

		return super.preHandle( request, response, handler );
	}

	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {
		super.postHandle( request, response, handler, modelAndView );
	}
}
