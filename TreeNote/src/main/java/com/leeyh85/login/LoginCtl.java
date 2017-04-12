package com.leeyh85.login;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.leeyh85.common.session.SessionManager;
import com.leeyh85.login.service.LoginService;
import com.leeyh85.model.UserModel;


/**
 * LoginCtl.java
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

@Controller
public class LoginCtl extends MultiActionController {

	private static final Logger logger = LoggerFactory.getLogger(LoginCtl.class.getName());
	
	/**
	 * 로그인
	 */
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SessionManager session;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate)*/;
		return "index";
	}
	
	/**
	 * 로그인 체크
	 * @param locale
	 * @param model
	 * @author gomyuwoo
	 * @return
	 */
	@RequestMapping(value = "/loginCheck", method = RequestMethod.GET)
	public String loginCheck(Locale locale, Model model) {
		System.out.println("### LoginCtl loginCheck ###");
		
		String rtn = "login/login";
		String admin_id = (String)session.getAttribute("admin_id");
		
		if(admin_id != null)
		{
			rtn = "redirect:/main.do?admin_id="+admin_id;
		} else {
			rtn = "redirect:/login.do";
		}
		return rtn;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, UserModel userModel) {
		System.out.println("### LoginCtl login ###");
		String rtn = "login/login";
		return rtn;
	}
	
	/**
	 * 메인화면
	 * @param request
	 * @param userModel
	 * @author gomyuwoo
	 * @return
	 */
	@RequestMapping(value = "/main", method={RequestMethod.GET, RequestMethod.POST})
	public String main(HttpServletRequest request, UserModel userModel) {
		System.out.println("### LoginCtl main ###");
		String rtn = "login/login";
		String admin_id = (String) request.getParameter("admin_id");
		System.out.println("### admin_id = " + admin_id);
		if(admin_id != null)
		{
			rtn = "login/main";
		}
		return rtn;
	}
	
	/**
	 * ajax 를 이용하여  로그인 처리
	 * @param request
	 * @param response
	 * @param userModel
	 * @author gomyuwoo
	 * @return
	 */
	@RequestMapping(value = "/signIn", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String signIn(HttpServletRequest request, HttpServletResponse response, UserModel userModel) {
		String status = "0";
		
		//로그인정보 체크
		userModel.setGbn("check_idpw");
		
		int result = loginService.selectExistUser(userModel);
		
		// 로그인!
		if (result > 0) {
			status = "1";
			
		} else {
			status = "0";
		}
		return status;
	}

	/**
	 * ajax를 이용하여 중복 체크
	 * @param request
	 * @param userModel
	 * @author gomyuwoo
	 * @return
	 */
	@RequestMapping(value = "/signUp", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String signUp(HttpServletRequest request, UserModel userModel) {
//		System.out.println("### LoginCtl signUp ###");
		
		String status = "0";
		int result = loginService.insertUserInfo(userModel);
		
		// 중복
		if (result > 0) {
			status = "1";
		} else {
			status = "0";
		}
		return status;
	}
	
	/**
	 * ajax 를 이용하여 id를 입력했을 때 id 중복여부 체크
	 * @param request
	 * @param userModel
	 * @author gomyuwoo
	 * @return
	 */
	@RequestMapping(value = "/check_id", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String checkId(HttpServletRequest request, UserModel userModel) {
//		System.out.println("### LoginCtl checkId ###");
		//아이디 중복체크
		userModel.setGbn("check_id");
		
		int count = loginService.selectExistUser(userModel);
		
		String status = "";
		if (count > 0) { // 중복
//			System.out.println("### 요기 1 ");
			status = "1";
		} else {
//			System.out.println("### 요기 2 ");
			status = "0";
		}
		return status;
	}
	
	/**
	 * ajax 를 이용하여 email를 입력했을 때 email 중복여부 체크
	 * @param request
	 * @param userModel
	 * @author gomyuwoo
	 * @return
	 */
	@RequestMapping(value = "/check_email", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String checkEmail(HttpServletRequest request, UserModel userModel) {
//		System.out.println("### LoginCtl check_email ###");

		//이메일 중복체크
		userModel.setGbn("check_email");
		
		int count = loginService.selectExistUser(userModel);
		
		String status = "";
		if (count > 0) { // 중복
			status = "1";
		} else {
			status = "0";
		}
		return status;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
}