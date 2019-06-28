<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<%@include file="../includes/header.jsp" %>
<%@include file="../includes/menu.jsp" %>
<script type="text/javascript">
	$(window).load(function() {
	    // 로딩되기 시작할때
		var error = "<c:out value='${error}'/>";
		if(error != ""){
			alert(error);
		}
	});
</script>
</head>
<body>
  <div class="container" style="margin-top: 8em; margin-bottom: 6em;">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">비밀번호 초기화하기</div>
      <div class="card-body">
        <div class="text-center mb-4">
          <h4>비밀번호를 잊으셨나요?</h4>
          <p>가입 아이디와 이메일을 적으면, 해당 이메일로 비밀번호가 전송됩니다.</p>
        </div>
        <form action="${path }/member/passwordSearch" method="get">
          <div class="form-group">
            <div class="form-label-group">
              <h5>아이디</h5><input type="text" id="mid" name="mid" class="form-control" placeholder="Enter id" required="required" autofocus="autofocus">
            </div>
            <div class="form-label-group">
              <h5>이메일</h5><input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Enter email address" required="required" autofocus="autofocus">
            </div>
          </div>
          <input type="submit" class="btn btn-primary btn-block" value="비밀번호 전송"/>
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="${path}/member/register">회원가입</a>
          <a class="d-block small" href="${path }/member/login">로그인</a>
        </div>
      </div>
    </div>
  </div>
  <%@include file="../includes/footer.jsp"%>
</body>
</html>