package com.leeyh85.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.leeyh85.common.file.FileUpload;


/**
 * CommonCtl.java
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

@RestController
@RequestMapping("/common")
public class CommonCtl extends MultiActionController {
//	@Autowired
//	private BoardService boardService;
	
    @Autowired
	private FileUpload fileUpload;
	
	/**
	 * ajax 를 이용하여  파일 업로드 처리
	 * @param request
	 * @param response
	 * @param fileModel
	 * @author gomyuwoo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "upload", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String fileUpload(HttpServletRequest request, MultipartHttpServletRequest requserFile, HttpServletResponse response) throws Exception {
		String status = "0";
		
		//첨부파일 등록
		if (requserFile != null && !requserFile.getFileMap().isEmpty()) {
			String fieldName = request.getParameter("file_id").toString();
			fileUpload.uploadDocument(requserFile, fieldName);
		}
		return "";
	}
}