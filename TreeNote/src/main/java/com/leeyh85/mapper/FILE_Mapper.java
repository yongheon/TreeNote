package com.leeyh85.mapper;

import org.springframework.stereotype.Repository;

/**
 * FILE_Mapper.java
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

@Repository(value="FILE_Mapper")
public interface FILE_Mapper {

	// 파일시퀀스
	int selectNextSeq();
}