/*******************************************************
 * 국토정보시스템 Open API 법정동 행정구역, 도로명주소 콤보박스 관련 script.
 * -----------------------------------------------------
 * 전제조건
 * 1. 반드시 id가 sido, sigungu, lgdong, li인 <select>태그가 
 * 존재해야 한다.
 * 2. 이 js파일을 포함하는 페이지에 jquery 라이브러리를 
 * 포함해야 한다.  
 *******************************************************/
/*
 * 시도 콤보박스 초기화 이벤트 핸들러
 */
function sidoInit(){
	$.ajax({
		type : "get"
		, dataType : "xml" 
		, url: "http://" + host + "/openapi/estateservice"
		, data: "apikey="+apikey+"&category=etc&target=LAD010"
		, success: sidoAttrSuccess
		, error: ladAttrError
	});
}

var defSigungu = null;
var defLgdong = null;
var defLi = null;
/*
 * 시도 콤보박스 onChange 이벤트 핸들러
 * @param sigunguOpt callBack시 default sigungu(Option)
 */
function sidoChange(sigunguOpt){
	var sidoobj = dojo.byId("sido");
	initLadObj(1);
	if(sigunguOpt && sigunguOpt.length!=null) {
		defSigungu = sigunguOpt;
	} else {
		defSigungu = null;
	}
	if(sidoobj.value!='') {
		$.ajax({
			type : "get"
			, dataType : "xml" 
			, url: "http://" + host + "/openapi/estateservice"
			, data: "apikey="+apikey+"&category=etc&target=LAD010&regn=" + sidoobj.value
			, success: sigunguAttrSuccess
			, error: ladAttrError
  	});
	}
}

/*
 * 시군구 콤보박스 onChange 이벤트 핸들러
 * @param lgdongOpt callBack시 default lgdong(Option)
 */
function sigunguChange(lgdongOpt){
	initLadObj(2);
	if(lgdongOpt && lgdongOpt.length!=null) {
		defLgdong = lgdongOpt;
	} else {
		defLgdong = null;
	}	
	var sigunguobj = dojo.byId("sigungu");
	if(sigunguobj.value!='') {
		var sidoobj = dojo.byId("sido");
		$.ajax({
  			type : "get"
  			, dataType : "xml" 
  			, url: "http://" + host + "/openapi/estateservice"
  			, data: "apikey="+apikey+"&category=etc&target=LAD010&regn=" + sidoobj.value + sigunguobj.value
  			, success: lgdongAttrSuccess
				, error: ladAttrError
  		});
	}
}

/*
 * 읍면동 콤보박스 onChange 이벤트 핸들러
 * @param lgdongOpt callBack시 default li(Option)
 */
function lgdongChange(liOpt){
	initLadObj(3);
	if(liOpt && liOpt.length!=null) {
		defLi = liOpt;
	} else {
		defLi = null;
	}	
	var lgdongobj = dojo.byId("lgdong");
	if(lgdongobj.value!='') {
		var sidoobj = dojo.byId("sido");
		var sigunguobj = dojo.byId("sigungu");
		$.ajax({
  			type : "get"
  			, dataType : "xml" 
  			, url: "http://" + host + "/openapi/estateservice"
  			, data: "apikey="+apikey+"&category=etc&target=LAD010&regn=" + sidoobj.value + sigunguobj.value + lgdongobj.value
  			, success: liAttrSuccess
				, error: ladAttrError
  		});
	}
}			

/*
 * 법정동 행정구역 콤보박스 초기화
 */
function initLadObj(level) {
	switch(level){
	case 1: //시도를 전체로 선택시
		var sigunguobj = dojo.byId("sigungu");
		for(var i=sigunguobj.options.length;i>0;i--) {
			sigunguobj.remove(i);
		}
		var lgdongobj = dojo.byId("lgdong");
		for(var i=lgdongobj.options.length;i>0;i--) {
			lgdongobj.remove(i);
		}		
		var liobj = dojo.byId("li");
		for(var i=liobj.options.length;i>0;i--) {
			liobj.remove(i);
		}	
		break;
	case 2: //시군구를 전체로 선택시
		var lgdongobj = dojo.byId("lgdong");
		for(var i=lgdongobj.options.length;i>0;i--) {
			lgdongobj.remove(i);
		}			
		var liobj = dojo.byId("li");
		for(var i=liobj.options.length;i>0;i--) {
			liobj.remove(i);
		}	
		break;
	case 3: //읍면동을 전체로 선택시
		var liobj = dojo.byId("li");
		for(var i=liobj.options.length;i>0;i--) {
			liobj.remove(i);
		}	
		break;
	}
}

/*
 * 법정동행정구역 콤보박스  Errorback
 */
function ladAttrError(jqXHR, textStatus, errorThrown) {
	alert("::행정구역 정보를 가져올 수 없습니다.\n" + jqXHR);	
}

/*
 * 시도 콤보박스  Callback
 */
function sidoAttrSuccess(data){
	var root = $(data).find("sidos");
	if(root.length==0) return alertError(data);
	var items =	$(root).find("sido");	
	var sidoobj = dojo.byId("sido");		
 	dojo.forEach(items, function(item, i) {
	 	var opt =  new Option($(item).find("nam").text(), $(item).find("cd").text()) ;
	  sidoobj.options.add(opt);
	});		
}

/*
 * 시군구 콤보박스  Callback
 */
function sigunguAttrSuccess(data){
	var root = $(data).find("sigungus");
	if(root.length==0) return alertError(data);
	var items =	$(root).find("sigungu");
	var sigunguobj = dojo.byId("sigungu");
 	dojo.forEach(items, function(item, i) {
	 	var opt =  new Option($(item).find("nam").text(), $(item).find("cd").text()) ;
	 	sigunguobj.options.add(opt);
	});	
 	if(defSigungu!=null) {
 		sigunguobj.value = defSigungu;
 		if(defPnu!=null&&defPnu.length==19) {
 			sigunguChange(defPnu.substring(5,8));
 		}
 	} else {
 		sigunguobj.value = "";
 	}
}

/*
 * 읍면동 콤보박스  Callback
 */
function lgdongAttrSuccess(data){
	var root = $(data).find("lgdongs");
	if(root.length==0) return alertError(data);
	var items =	$(root).find("lgdong");
	var lgdongobj = dojo.byId("lgdong");
 	dojo.forEach(items, function(item, i) {
	 	var opt =  new Option($(item).find("nam").text(), $(item).find("cd").text()) ;
	 	lgdongobj.options.add(opt);
	});
 	if(defLgdong!=null) {
 		lgdongobj.value = defLgdong;
 		if(defPnu!=null&&defPnu.length==19) {
 			lgdongChange(defPnu.substring(8,10));
 		} 		
 	} else {
 		lgdongobj.value = "";
 	}
}			

/*
 * 리 콤보박스  Callback
 */
function liAttrSuccess(data){
	var root = $(data).find("lis");
	var items =	$(root).find("li");
	var liobj = dojo.byId("li");
 	dojo.forEach(items, function(item, i) {
	 	var opt =  new Option($(item).find("nam").text(), $(item).find("cd").text()) ;
	 	liobj.options.add(opt);
	});			
 	if(defLi!=null) {
 		liobj.value = defLi;
 		if(defPnu!=null&&defPnu.length==19) {
 			if(defPnu.substring(10,11)=='2') {
 				dojo.byId("ldgGb2").checked = true;
 			} else {
 				dojo.byId("ldgGb1").checked = true;
 			} 
 			dojo.byId("bonbun").value = parseInt(defPnu.substring(11,15),10);
 			dojo.byId("bubun").value = parseInt(defPnu.substring(15,19),10);
 		} 	 		
 	} else {
 		liobj.value = "00";
 	}
}		

/*
 * 도로명주소 시도 콤보박스 초기화 이벤트 핸들러
 */
function rdSidoInit(){
	$.ajax({
		type : "get"
		, dataType : "xml" 
		, url: "http://" + host + "/openapi/estateservice"
		, data: "apikey="+apikey+"&category=etc&target=LAD010"
		, success: rdSidoAttrSuccess
		, error: ladAttrError
	});
}

var defRdSigungu = null;
var defInx = null;
var defRdNm = null;
/*
 * 도로명주소 시도 콤보박스 onChange 이벤트 핸들러
 * @param rdSigunguOpt callBack시 default rdSigungu(Option)
 */
function rdSidoChange(rdSigunguOpt){
	var rdSidoobj = dojo.byId("rdSido");
	initRdObj(1);
	if(rdSigunguOpt) {
		defRdSigungu = rdSigunguOpt;
	} else {
		defRdSigungu = null;
	}
	if(rdSidoobj.value!='') {
		$.ajax({
			type : "get"
			, dataType : "xml" 
			, url: "http://" + host + "/openapi/estateservice"
			, data: "apikey="+apikey+"&category=etc&target=LAD010&regn=" + rdSidoobj.value
			, success: rdSigunguAttrSuccess
			, error: ladAttrError
  	});
	}
}

/*
 * 도로명주소 시군구 콤보박스 onChange 이벤트 핸들러
 * @param inxOpt callBack시 default inx(Option)
 */
function rdSigunguChange(rdNmOpt){
	initRdObj(2);
	if(rdNmOpt=='188') {
		dojo.byId("inx").value = '01';
		defRdNm = rdNmOpt;
	}	else {
		defRdNm = null;
	}
	var rdSigunguobj = dojo.byId("rdSigungu");
	var inxobj = dojo.byId("inx");
	if(rdSigunguobj.value!='' && inxobj.value!='') {
		var rdSidoobj = dojo.byId("rdSido");
		$.ajax({
  			type : "get"
  			, dataType : "xml" 
  			, url: "http://" + host + "/openapi/estateservice"
  			, data: "apikey="+apikey+"&category=etc&target=RDN010&road=" + rdSidoobj.value + rdSigunguobj.value + inxobj.value
  			, success: rdNmAttrSuccess
				, error: ladAttrError
  		});
	}
}

/*
 * 도로명 주소 초성 콤보박스 onChange 이벤트 핸들러
 * @param rdNmOpt callBack시 default rdNm(Option)
 */
function inxChange(rdNmOpt){
	initRdObj(3);
	var inxobj = dojo.byId("inx");
	if(inxobj.value!='') {
		var rdSidoobj = dojo.byId("rdSido");
		var rdSigunguobj = dojo.byId("rdSigungu");
		$.ajax({
  			type : "get"
  			, dataType : "xml" 
  			, url: "http://" + host + "/openapi/estateservice"
  			, data: "apikey="+apikey+"&category=etc&target=RDN010&road=" + rdSidoobj.value + rdSigunguobj.value + inxobj.value
  			, success: rdNmAttrSuccess
				, error: ladAttrError
  		});
	}
}		

/*
 * 도로명 주소 콤보박스 초기화
 */
function initRdObj(level) {
	switch(level){
	case 1: //도로명 주소 시도를 전체로 선택시
		var rdSigunguobj = dojo.byId("rdSigungu");
		for(var i=rdSigunguobj.options.length;i>0;i--) {
			rdSigunguobj.remove(i);
		}
		var rdNmobj = dojo.byId("rdNm");
		for(var i=rdNmobj.options.length;i>0;i--) {
			rdNmobj.remove(i);
		}	
		break;
	case 2: // 도로명 주소 시군구를 전체로 선택시
		var rdNmobj = dojo.byId("rdNm");
		for(var i=rdNmobj.options.length;i>0;i--) {
			rdNmobj.remove(i);
		}	
		break;
	case 3: // 도로명 주소 초성을 전체로 선택시
		var rdNmobj = dojo.byId("rdNm");
		for(var i=rdNmobj.options.length;i>0;i--) {
			rdNmobj.remove(i);
		}	
		break;
	}
}

/*
 * 도로명주소 시도 콤보박스  Callback
 */
function rdSidoAttrSuccess(data){
	var root = $(data).find("sidos");
	
	if(root.length==0) return alertError(data);
	
	var items =	$(root).find("sido");	
	var rdSidoobj = dojo.byId("rdSido");		
 	dojo.forEach(items, function(item, i) {
	 	var opt =  new Option($(item).find("nam").text(), $(item).find("cd").text()) ;
	 	rdSidoobj.options.add(opt);
	});		
}

/*
 * 도로명주소 시군구 콤보박스  Callback
 */
function rdSigunguAttrSuccess(data){
	var root = $(data).find("sigungus");
	
	if(root.length==0) return alertError(data);
	
	var items =	$(root).find("sigungu");
	var rdSigunguobj = dojo.byId("rdSigungu");
 	dojo.forEach(items, function(item, i) {
	 	var opt =  new Option($(item).find("nam").text(), $(item).find("cd").text()) ;
	 	rdSigunguobj.options.add(opt);
	});	
 	if(defRdSigungu!=null) {
 		rdSigunguobj.value = defRdSigungu;
 		rdSigunguChange('188'); // 	
 	}
}

/*
 * 도로명주소 도로명 콤보박스  Callback
 */
function rdNmAttrSuccess(data){
	var root = $(data).find("roadAddrs");
	
	if(root.length==0) return alertError(data);
	
	var items =	$(root).find("roadAddr");
	var rdNmobj = dojo.byId("rdNm");
 	dojo.forEach(items, function(item, i) {
	 	var opt =  new Option($(item).find("rdNm").text(), $(item).find("seq").text()) ;
	 	rdNmobj.options.add(opt);
	});
 	if(defRdNm!=null) {
 		rdNmobj.value = '188';
 	} 	
}		

/*
 * Open API Callback시 Error반환 처리
 */
function alertError(errorData) {
	var err = $(errorData).find("error");
	if(err.length==1) {
		var errCd = $(err).find("errorCode").text();
		if(errCd=="RESULT_000") {
			var content = "에러:" + $(err).find("errorCode").text() + ", " +   $(err).find("errorDesc").text();
			alert(content);
		}
	}
}

/* 
 * 숫자형 텍스트박스 onKeyUp 이벤트핸들러
 */
function numTest(obj) {
  var strarr = new Array();
  if (obj.value.length > 0) {
	  for (var i=0; i<obj.value.length; i++){
			strarr[i] = obj.value.charAt(i);
			if (!isNaN(parseInt(strarr[i], 10))) {
			  continue;
			} else {
			  alert("숫자만 입력 가능합니다.");
			  obj.value = "";
			  obj.focus();
			  return false;
			}
		}  
	 	return true;
  }
}

/*
 * pnu 유효성 검사
 */
function validatePnu(pnu) {
	//숫자로만 이루어진지 검사
	try{
		parseInt(pnu);
	} catch(e) {
		return false;
	}
	
	//19자리인지 검사
	if(pnu.length != 19) {
		return false;
	}
	return true;
}

/*
 * 문자 자리수 왼쪽 채움  - arguments[0] : 채울 자릿수, arguments[1] : 채울 문자
 * @return : String
 */
String.prototype.lPad = function() {
	var n  = arguments[0] ? arguments[0] : this.length;
	var padding  = arguments[1] ? arguments[1] : "";
   if (this.length >= n) return this;
   else
   {
      var len = n - this.length;
      var pad_str = this;
      for (var i=0; i<len; i++) pad_str = padding + pad_str;
      return pad_str;
   }
}
//-->