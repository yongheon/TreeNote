package com.leeyh85.common.util;

public class CommonHtml {
	/**
	 * 모든 HTML 태그를 제거하고 반환한다.
	 * 
	 * @param html
	 * @return
	 * @author LYH
	 * @throws Exception
	 */
	public String removeTag(String html) throws Exception {
		System.out.println("removeTag 함수의 html : " + html);
		return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}
}
