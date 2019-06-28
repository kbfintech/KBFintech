<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<%@include file="../includes/header.jsp" %>
<%@include file="../includes/menu.jsp" %>
</head>
<body>
  <div class="container" style="margin-top: 8em; margin-bottom: 6em;">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">비밀번호 초기화하기</div>
      <div class="card-body">
        <div class="text-center mb-4">
          <h4>비밀번호를 잊으셨나요?</h4>
          <p>이메일 주소를 입력해주시면 비밀번호를 초기화하는 방법을 알려드리겠습니다.</p>
        </div>
        <form>
          <div class="form-group">
            <div class="form-label-group">
              <input type="email" id="inputEmail" class="form-control" placeholder="Enter email address" required="required" autofocus="autofocus">
              <label for="inputEmail">이메일</label>
            </div>
          </div>
          <a class="btn btn-primary btn-block" href="${path }/member/login">비밀번호 초기화</a>
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