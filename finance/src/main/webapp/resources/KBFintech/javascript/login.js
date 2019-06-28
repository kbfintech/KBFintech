function isAllTyped() {

	var userId = document.forms["loginOk"]["userId"].value;
	var userPw = document.forms["loginOk"]["password"].value;

	if (userId == "") {
		alert("아이디를 입력해 주십시요.");
	} else if (userPw == "") {
		alert("비밀번호를 입력해 주십시요.");
	} else {
		document.getElementById("loginOk").submit();
		
		// 아이디 저장
		
		var keepId = $('#userId');
		
		console.log($('#checkId').prop('checked'));
		
		if($('#checkId').prop('checked')){
		    setCookie("id", keepId.val(), 7); 
		} else {

		    deleteCookie("id");
		}
		
		// 비밀번호 저장
		
		var keepPw = $('#inputPassword');
		
		if($('#checkPass').prop('checked')){
		    setCookie("password", keepPw.val(), 7); 
		} else {

		    deleteCookie("password");
		}
	}

}

// 쿠키 관련 함수
function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString();
}

function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if (start != -1) {
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if (end == -1)
			end = cookieData.length;
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}

$(document).ready(function() {
	var idCookie = getCookie("id");
	var passWdCookie = getCookie("password");
	
	if (idCookie) {
		$('#checkId').prop('checked', true);
		$('#userId').val(idCookie);
		
	}
	
	if (passWdCookie) {
		$('#checkPass').prop('checked', true);
		$('#inputPassword').val(passWdCookie);
	}
});