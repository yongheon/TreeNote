package com.leeyh85.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leeyh85.model.UserModel;

/**
 * T_USER_Mapper.java
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

@Repository(value="USER_Mapper")
public interface USER_Mapper {
	// 사용자 목록 조회
	public List<UserModel> selectUserAll(UserModel userModel);

	// 사용자 정보 반환
	public UserModel selectUserInfo(UserModel userModel);

	// 사용자 존재여부 확인
	public int selectExistUser(UserModel userModel);
	
	// 사용자 추가
	public int insertUserInfo(UserModel userModel);
}