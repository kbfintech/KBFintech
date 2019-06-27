function isAllTyped() {
	
	var userId = document.forms["loginOk"]["userId"].value;
	var userPw = document.forms["loginOk"]["password"].value;

	if(userId == "") {
		alert("아이디를 입력해 주십시요.");
	} else if(userPw == "") {
		alert("비밀번호를 입력해 주십시요.");
	} else {
		document.getElementById("loginOk").submit();
	}

}