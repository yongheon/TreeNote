package com.leeyh85.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leeyh85.common.util.Entity;
import com.leeyh85.model.BoardModel;
import com.leeyh85.model.PagingModel;

/**
 * BOARD_Mapper.java
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

@Repository(value="BOARD_Mapper")
public interface BOARD_Mapper {

	// 글 입력
	void insertArticle(BoardModel boardModel);
	
	// 글 전체 목록 받기
	List<BoardModel> selectArticleAll();

	// 글 전체 개수 받기
	int selectCountArticle(BoardModel boardModel);

	// 페이지의 글 받기
	List<BoardModel> selectArticleOfPage(PagingModel pagingModel);
	
	// 다음 글번호 시퀀스 받기
	int selectNextBoardId();

	// 글 정보 받기
	BoardModel selectArticleInfo(BoardModel boardModel);

	// 글 조회수 +1
	void updateArticleHitCount(int board_id);

	// 글 삭제
	void deleteArticle(int board_id);

	// 글 수정
	void updateArticle(BoardModel boardModel);

}