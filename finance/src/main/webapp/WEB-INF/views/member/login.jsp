<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>

<script
	src="<%=application.getContextPath()%>/resources/KBFintech/javascript/login.js"
	type="text/javascript"></script>

</head>
<body>
	<div class="container" style="margin-top: 8em; margin-bottom: 6em;">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">로그인</div>
			<div class="card-body">
				<form name="loginOk" id="loginOk" action="${path }/member/loginOk"
					method="post">
					<div class="form-group">
						<div class="form-label-group">
							<input name="userId" type="text" id="userId" class="form-control"
								placeholder="UserId" required="required" autofocus="autofocus">
							<label for="userId">아이디</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input name="password" type="password" id="inputPassword"
								class="form-control" placeholder="Password" required="required">
							<label for="inputPassword">비밀번호</label>
						</div>
					</div>
					<div class="form-group">
						<div id="checkIdPasswd" class="checkbox" align="center">
							<label> <input id="checkId" type="checkbox"
								value="saveId"> 아이디 저장
							</label> <label> <input id="checkPass" type="checkbox"
								value="savePassword"> 비밀번호 저장
							</label>
						</div>
					</div>
					<input onclick="isAllTyped()" class="btn btn-primary btn-block"
						value="로그인" />
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="${path }/member/register">회원가입</a>
					<a class="d-block small" href="${path }/member/forget">비밀번호 찾기</a>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../includes/footer.jsp"%>
</body>
</html>