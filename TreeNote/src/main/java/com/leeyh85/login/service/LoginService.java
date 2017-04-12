package com.leeyh85.login.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leeyh85.mapper.USER_Mapper;
import com.leeyh85.model.UserModel;

/**
 * LoginService.java
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

@Service
public class LoginService {
	
	static Logger log = Logger.getLogger(LoginService.class.getName());
	
	@Autowired
    private USER_Mapper user_mapper;
	
	// 사용자 목록 조회
	List<UserModel> selectUserAll(UserModel userModel) {
//		System.out.println("LoginService selectUserAll");
		return user_mapper.selectUserAll(userModel);
	}
	
	// 사용자 정보 반환
	UserModel selectUserInfo(UserModel userModel) {
//		System.out.println("LoginService selectUserInfo");
		return user_mapper.selectUserInfo(userModel);
	}

	// 사용자 존재여부 확인
	public int selectExistUser(UserModel userModel) {
//		System.out.println("LoginService selectExistUser");
		int result = 0;
		result = user_mapper.selectExistUser(userModel);
		return result;
	}
	
	// 사용자 추가
	public int insertUserInfo(UserModel userModel) {
//		System.out.println("LoginService insertUserInfo");
		int result = 0;
		result = user_mapper.insertUserInfo(userModel);
		return result;
	}
}