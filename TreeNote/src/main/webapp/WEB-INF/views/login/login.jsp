<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>로그인</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/ci.ico" />
<link rel="stylesheet" type="text/css" href="/css/common.css" />
<link rel="stylesheet" type="text/css" href="/css/login.css" />
<script type="text/javascript" src="/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-font.js"></script>
</head>
<body>

<div class="topWrap">
	<div class="top"><img src="/images/common/ci.png" width="118" height="30" alt="한일네트웍스" /></div>
</div>
    
<div id="loginCWrap">
	<div class="bg">
		<div class="box">
			<div class="logo"><!-- <img src="/images/logo_login.png" width="409" height="80" alt="경영관리시스템" /> -->경영관리시스템로고</div>
			<form id="login" name="login" action="/login/login.hanil">
				<input type="hidden" name="forward_uri" value="${param.forward_uri}"/> 
				<input type="hidden" name="queryString" value="${param.queryString}"/>	
				<table border="0" cellspacing="0" cellpadding="0" summary="로그인">
					<caption></caption>
					<colgroup><col width="137px" /><col width="195px" /><col width="" /></colgroup>
					<tr>
						<td colspan="3" height="30"></td>
			      	</tr>
				  	<tr>
				    	<th height="35">아이디</th>
				    	<td>
				    		<input type="text" id="user_id" name="user_id" tabindex="1" style="IME-MODE:inactive;width:176px;" onchange="setUserChk();" maxlength="9" onkeypress="gosubmit()"/>
				    	</td>
				    	<td rowspan="2"><img src="images/login/btn_login.png" width="115" height="68" alt="로그인" style="cursor:pointer"  onclick="javascript:Login();"/></td>
			      	</tr>
				  	<tr>
				    	<th height="37">비밀번호</th>
				    	<td><input type="password" id="passwd" name="passwd" tabindex="2" maxlength="20" style="width:176px;"  onkeypress="gosubmit()"/></td>
			      	</tr>
				  	<tr>
						<td colspan="3" class="text01"><input type="checkbox" name="user_chk" id="user_chk" onclick="javascript:fncSaveCookie();" class="input-noborder"/> 아이디 저장 &nbsp;&nbsp; </td>
			      	</tr>
				</table>
			</form>
        	<div class="copy">현 시스템은 <strong>IE10 이상</strong>의 버전에서 <strong>최적화</strong>되어 있습니다. &nbsp;&nbsp;<img src="images/login/bul_ie.png" width="16" height="17" class="vm" /> <a href="http://www.microsoft.com/ko-kr/download/internet-explorer-8-details.aspx">인터넷 익스플로러 업그레이드</a> 하기<br />문의 02) 531-7151~7152</div>
      </div>
	</div>
</div> 

<div class="layer">
	<div class="bg"></div>
	<div class="pop-layer" id="layer2">
		<div class="pop-container">
			<div class="pop-conts">
				<!--content //-->
				<form id="frm_login_info" name="frm_login_info" action="<c:url value="/login/update.hanil"/>" method="post">		
					<h4>비밀번호 변경</h4>
					3개월 동안 비밀번호를 변경하지 않으셨습니다. 변경 후 사용해 주세요.
			    	<table border="0" cellspacing="0" cellpadding="0" id="table">
						<caption></caption>
				   		<colgroup><col width="90px" /></colgroup>
						<tbody>
							<tr>
								<th class="table_th">비밀번호</th>
								<td class="table_td"><input type="password" id="password" name="password" value=""> (영문+숫자+특수문자 8자리 이상)</td>
							</tr>
							<tr>
								<th class="table_th">비밀번호 확인</th>
								<td class="table_td"><input type="password" id="password_re" name="password_re" value=""></td>
							</tr>
						</tbody>
					</table>
					<div id="btn-intocenter">
						<span class="myButton" id="btnClose">닫기</span>
						<span class="myButton" onclick="onDataSubmit()">변경</span>
					</div>
				</form>
				<!--// content-->
			</div>
		</div>
	</div>
</div>

<div class="footerWrap">
    <ul>
    	<li><img src="images/login/footer_ci.png" width="151" height="24" alt="한일네트웍스" class="footer_logo" /></li>
        <li>152-777 서울 특별시 구로구 디지털로 32길 30 코오롱디지털타워빌란트 13층 <span></span> 사업자등록번호 139-81-37667<br />COPYRIGHTⓒ HANIL NETWORKS CO., LTD. ALL RIGHTS RESERVED.</li>
    </ul>
</div>
</body>
</html>