package com.leeyh85.model;

public class PagingModel {
	private int page_no; // 선택된 페이지
	private int list_count; // 한페이지의 글 개수
	private int start_page; // 한 페이지의 시작 번호
	private int current_page; // 선택된 페이지 번호
	private int start_row; // 한 페이지의 시작 번호
	private int end_row;   // 한 페이지의 끝 번호
	
	public int getPage_no() {
		return page_no;
	}
	public void setPage_no(int page_no) {
		this.page_no = page_no;
	}
	public int getList_count() {
		return list_count;
	}
	public void setList_count(int list_count) {
		this.list_count = list_count;
	}
	public int getStart_page() {
		return start_page;
	}
	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public int getStart_row() {
		return start_row;
	}
	public void setStart_row(int start_row) {
		this.start_row = start_row;
	}
	public int getEnd_row() {
		return end_row;
	}
	public void setEnd_row(int end_row) {
		this.end_row = end_row;
	}
}