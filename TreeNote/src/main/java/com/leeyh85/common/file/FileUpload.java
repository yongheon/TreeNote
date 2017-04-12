package com.leeyh85.common.file;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.leeyh85.common.util.DateUtil;
import com.leeyh85.mapper.FILE_Mapper;
import com.leeyh85.model.FileModel;

/**
 * FileService.java
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
public class FileUpload {
	
	static Logger log = Logger.getLogger(FileUpload.class.getName());
	
	@Autowired
    private FILE_Mapper file_mapper;
	
	/**
     * config.properties 설정된 사업별 첨부 문서 업로드 경로
     */
    @Value( "#{ConfigProperties['spring.uploadAttachtDir']}" )
    String uploadAttachtDir;
    
	// 첨부파일 업로드
	public boolean uploadDocument(MultipartHttpServletRequest requserFile, String fieldName) throws Exception {
		System.out.println("### uploadDocument ###");
		// TODO Auto-generated method stub
		String uploadDir = "";	//저장경로
        String changFileNm = "";//저장소파일명
        String fileNm = "";		//파일명
        String board_seq = ""; 	//글번호
        String idx = ""; 		//파일번호        
        String ext = "";		//확장자
        File destination = null;
        
        try {
	        List<MultipartFile> multipartFileList = requserFile.getFiles(fieldName);
	        uploadDir = uploadAttachtDir + "bbs/" + DateUtil.getTodayPathString(); //운영시스템 저장위치
	        
	        // 개발자용 설정 d:/ 경로 하위에 운영시스템 경로를 만든다.
	        String osName = System.getProperty("os.name");
			uploadDir = osName.indexOf("Windows") > -1 ? "d:" + uploadDir : uploadDir;
	        File uploadFileDirs = new File( uploadDir );
			if( !uploadFileDirs.exists() ) uploadFileDirs.mkdirs();
	        
	        /** 파일총용량이 설정용량보다 큰 경우 실패 리턴 */
	        //새로 첨부한 파일
//			double fileSize = 0;
//			for (int i=0; i<multipartFileList.size(); i++) {
//				if ( !multipartFileList.get(i).getOriginalFilename().equalsIgnoreCase("") ) {
//					MultipartFile multipartFile = multipartFileList.get(i);
//					fileSize += multipartFile.getSize();
//				}
//			}
//			//기존 첨부된 파일
//			List<KcbEntity<String,Object>> resultList = this.selectDocumentList(entityParam);
//			if (resultList != null) {
//				for (int i=0; i<resultList.size(); i++) {
//					fileSize += resultList.get(i).getInt("file_size");
//				}
//				DecimalFormat fmt = new DecimalFormat ("0.##");
//				if (fileSize > 0) {
//					fileSize = fileSize/1024000.00;
//				}
//			}
//			//첨부파일최대용량 조회
//			KcbEntity<String, Object> maxAttach = codeMapper.getMaxAttachCapacity02();
//			double attachMax = 0;
//			if (maxAttach != null) {
//				attachMax = Double.parseDouble((String) maxAttach.get("cd_id_nm"));
//			}
//			if (fileSize > attachMax) {
//				entityParam.put("fileMaxSizeOver", "T");
//			    return false;
//			}
			
			/* 파일 업로드 구현 */
			if( null != multipartFileList && 0 < multipartFileList.size() ) {
	            for(int i=0; i<multipartFileList.size(); i++) {
	            	if ( !multipartFileList.get(i).getOriginalFilename().equalsIgnoreCase("") ) {
	                	int nextFileSeq = selectNextSeq();
	                	
	                	FileModel fileModel = new FileModel();
	                	fileModel.setIdx(nextFileSeq);
		                
	                	/*changFileNm = new StringBuffer(512).append( bd_id ).append("_").append( bd_no ).append("_").append( file_no ).toString();
		            	System.out.println("### changFileNm = " + changFileNm);*/
		            
	                	/* 파일 업로드 구현 */
	                	MultipartFile multipartFile = multipartFileList.get(i);
	                	if( null != multipartFile && !multipartFile.getOriginalFilename().equalsIgnoreCase("") ) {
	                		/* 파일 확장자 구현 */
	                		String orgFileName = multipartFile.getOriginalFilename();
	                		int extPosition = orgFileName.lastIndexOf(".");
	                		ext = orgFileName.substring( extPosition + 1 , orgFileName.length() ).toLowerCase();
	                		destination = new File( uploadDir + "/" + changFileNm + "." + ext );
	                		if(!destination.exists()){
	                			destination.mkdirs();
	                		}
	                		multipartFile.transferTo(destination);
	                	}
		            	//기존파일삭제
//		            	if(entityParam.get("flag") != null && "I".equals(entityParam.get("flag"))){
//			            	if(i==0){
//			            		deletionTargetFile(entityParam); 
//			            		//기존정보삭제
//			            		deleteDocumentInfo(entityParam);
//			            	}
//		            	}
			            //첨부파일 등록
	                	fileModel.setBoard_seq(389);
	                	fileModel.setCrea_id("sayim");
	                	fileModel.setFile_size(100);
	                	
//			            entityParam.put("file_path", uploadDir);
//			            entityParam.put("file_change_nm", changFileNm + "." + ext);
//			            entityParam.put("file_nm", multipartFile.getOriginalFilename());
//			            entityParam.put("file_size", destination.length());
//			            entityParam.put("file_ext", ext);
//		                insertD010(entityParam);
	            	}
	            }
	        }
	    } catch( Exception ex ) {
	        ex.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	// 다음 파일번호 시퀀스
	public int selectNextSeq() {
		System.out.println("FileService --> selectNextSeq");
		return file_mapper.selectNextSeq();
	}
}