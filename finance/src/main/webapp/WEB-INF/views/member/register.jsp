<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>

<script>
	$(document).ready(function() {
		var h = $(".navbar").height();
		var top = $(".navbar").css('padding-top').split('px')[0] * 1;
		var bottom = $(".navbar").css('padding-bottom').split('px')[0] * 1;
		var result = h + top + bottom;
		$("맨위에 클래스 및 아이디 명").css('margin-top', result);
	});
</script>

<script
	src="<%=application.getContextPath()%>/resources/KBFintech/javascript/member_register.js"
	type="text/javascript"></script>

</head>
<body>
	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">회원 가입</div>
			<div class="card-body">
				<form name="registerOk" id="registerOk"
					action="${path }/member/registerOk" method="post">
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-10">
								<input name="userId" type="text" id="userId" value="아이디"
									class="form-control"
									onfocus="if(this.value==this.defaultValue)this.value='';"
									onblur="if(this.value=='')this.value=this.defaultValue;" />
							</div>
							<div class="col-md-2">
								<input onclick="isUsableId()" class="btn btn-primary btn-block"
									value="중복여부" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<input name="name" type="text" id="username" value="이름"
							class="form-control"
							onfocus="if(this.value==this.defaultValue)this.value='';"
							onblur="if(this.value=='')this.value=this.defaultValue;" />

					</div>
					<div class="form-group">
						<!-- <div class="form-row"> -->
						<!-- <div class="col-md-10"> -->
						<input name="phoneNum" type="tel" id="phoneNum" value="휴대폰번호"
							class="form-control"
							onfocus="if(this.value==this.defaultValue)this.value='';"
							onblur="if(this.value=='')this.value=this.defaultValue;" />
						<!-- </div> -->
						<!-- <div class="col-md-2">
								<input id="btnPhone" onclick="isValidPhoneNum()"
									class="btn btn-primary btn-block" value="본인인증" />
							</div> -->
						<!-- </div> -->
					</div>
					<div class="form-group">
						<input name="email" type="email" id="inputEmail" value="이메일"
							class="form-control"
							onfocus="if(this.value==this.defaultValue)this.value='';"
							onblur="if(this.value=='')this.value=this.defaultValue;" />

					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<div class="form-label-group">
									<input name="password" type="password" id="inputPassword"
										class="form-control" /> <label for="inputPassword">비밀번호</label>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-label-group">
									<input name="confirmPassword" type="password"
										id="confirmPassword" class="form-control" /> <label
										for="confirmPassword">비밀번호 확인</label>
								</div>
							</div>
						</div>
					</div>
					<input onclick="isCorrectPassword()" class="btn btn-primary btn-block" value="확인" />
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="${path }/member/login">로그인
						하기</a> <a class="d-block small" href="${path }/member/forget">비밀번호
						찾기</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>