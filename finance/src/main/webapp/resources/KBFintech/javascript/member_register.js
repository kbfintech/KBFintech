var flag = 0;

function isCorrectPassword() {

	var userId = document.forms["registerOk"]["userId"].value;
	var name = document.forms["registerOk"]["name"].value;
	var phoneNum = document.forms["registerOk"]["phoneNum"].value;
	var email = document.forms["registerOk"]["email"].value;
	var password = document.forms["registerOk"]["password"].value;
	var confirmPassword = document.forms["registerOk"]["confirmPassword"].value;

	var submit = 0;

	if (userId == "아이디") {
		alert("아이디를 입력하여 주시기 바랍니다.");
	} else if (flag == 0) {
		alert("아이디 중복검사를 해주시기 바랍니다.");
	} else if (name == "이름") {
		alert("이름을 입력하여 주시기 바랍니다.");
	} else if (phoneNum == "휴대폰번호") {
		alert("휴대폰번호를 입력하여 주시기 바랍니다.");
	} else if (email == "이메일") {
		alert("이메일을 입력하여 주시기 바랍니다.");
	} else if (password == "") {
		alert("비밀번호를 입력하여 주시기 바랍니다.");
	} else if (confirmPassword == "" || password != confirmPassword) {
		alert("비밀번호를 확인하여 주시기 바랍니다.");
	} else {
		submit = 1;
	}
	
	console.log(password + " / " + confirmPassword);
	
	isSubmit(submit);

}

function isSubmit(check) {
	if (check == 1) {
		document.getElementById("registerOk").submit();
	}
}

function isUsableId() {

	var id = document.forms["registerOk"]["userId"].value;

	if ("아이디" == id) {
		alert("아이디를 입력하여 주시기 바랍니다.");
	} else {
		var json = {
			"userId" : id
		};

		$.ajax({
			url : "/finance/member/isValid",
			method : "post",
			dataType : "json",
			data : json,
			success : function(data) {
				if (data == "0") {
					alert("사용가능한 아이디입니다.");
					flag = 1;
				} else {
					alert("이미 사용하고 있는 아이디입니다.");
				}
			},
			error : function() {
				alert("예기치 못한 에러가 발생하였습니다. 다시 시도해주시기 바랍니다.");
			}
		});
	}

}

// function isValidPhoneNum() {
//	
// var phoneNum = document.forms["registerOk"]["phoneNum"].value;
//	
// console.log(phoneNum);
//
// // iamport 휴대폰 본인인증 API
// var IMP = window.IMP;
// IMP.init("imp90062431"); // "imp90062431"
// IMP.certification({ // param
// phone: phoneNum
// }, function(rsp) { // callback
// if (rsp.success) {
// // 인증 성공 시 로직,
// alert("휴대폰 본인인증이 완료되었습니다.");
// document.getElementById('btnPhone').disabled = true;
// } else {
// // 인증 실패 시 로직,
// alert("휴대폰 본인인증에 실패하였습니다.");
// }
// });
//
// }
