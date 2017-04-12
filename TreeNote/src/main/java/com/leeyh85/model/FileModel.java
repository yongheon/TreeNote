package com.leeyh85.model;

public class FileModel {
	private int idx;						//파일번호
	private int board_seq;					//글번호
	private String original_file_name; 		//실제파일명
	private String stored_file_name; 		//저장소파일명
	private int file_size; 					//파일크기
	private String create_date; 			//생성일자
	private String crea_id; 				//생성자
	private String hist_del_flag; 			//삭제여부
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getOriginal_file_name() {
		return original_file_name;
	}
	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}
	public String getStored_file_name() {
		return stored_file_name;
	}
	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getCrea_id() {
		return crea_id;
	}
	public void setCrea_id(String crea_id) {
		this.crea_id = crea_id;
	}
	public String getHist_del_flag() {
		return hist_del_flag;
	}
	public void setHist_del_flag(String hist_del_flag) {
		this.hist_del_flag = hist_del_flag;
	}
}