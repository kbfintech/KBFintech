<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
</head>
<body>
	<div class="container">
		<h1 class="my-4" align="center">추천 결과를 확인하세요</h1>
		<hr>
		<div class="row">
			<c:forEach var="detailInfo" items='${infoList}'>
				<c:out value='${detailInfo}'/><br>
			</c:forEach>
		</div>
			
	</div>
	<%@include file="../includes/footer.jsp"%>
</body>
</html>