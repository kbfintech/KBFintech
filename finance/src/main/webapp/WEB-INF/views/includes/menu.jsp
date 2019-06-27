<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<head>
<script type="text/javascript">
	$(document).ready(function() {

		var value = ${loginFlag};

		console.log("login유무체크: " + value);

		if (value == 0) {
			document.getElementById("divLogin").style.display = "block";
			document.getElementById("divLogout").style.display = "none";
		} else {
			document.getElementById("divLogin").style.display = "none";
			document.getElementById("divLogout").style.display = "block";
		}

	});
</script>
</head>
<body class="fontType">
	<!-- Navigation -->
	<nav style="background-color: #FFCC33"
		class="navbar fixed-top navbar-expand-lg navbar-light fixed-top">
		<div class="container">
			<a class="navbar-brand" href="${path }/">
				<div class="imgWrap">
					<div class="logoText">
						<p style="font-size: 30px;">월렛 버핏</p>
					</div>
					<img class="logo" src="${path }/resources/KBFintech/img/w1.PNG">
				</div>
			</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">

					<li class="nav-item"><a class="nav-link"
						href="${path }/payRegister">카드 등록</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${path }/product/question">상품 목록</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${path }/plan/planer">플랜 작성</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${path }/ranking/showRank">랭킹 확인하기</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownPortfolio" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> 내 정보 관리 </a>
						<div class="dropdown-menu dropdown-menu-right"
							aria-labelledby="navbarDropdownPortfolio">
							<a class="dropdown-item" href="${path }/member/memInfo">회원 정보</a>
							<a class="dropdown-item" href="portfolio-2-col.html">카드 관리하기</a>
							<a class="dropdown-item" href="portfolio-3-col.html">상품 관리하기</a>
							<a class="dropdown-item" href="portfolio-4-col.html">포인트 관리하기</a>
							<a class="dropdown-item" href="portfolio-item.html">플랜 관리하기</a>
						</div></li>
					<li class="nav-item">
						<div id="divLogin">
							<a class="nav-link" href="${path}/member/login">로그인</a>
						</div>
					</li>
					<li class="nav-item">
						<div id="divLogout">
							<p><%=session.getAttribute("name")%>님 <small>반갑습니다.</small>
							</p>
							<a class="nav-link" href="${path}/member/logout">로그아웃</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</body>