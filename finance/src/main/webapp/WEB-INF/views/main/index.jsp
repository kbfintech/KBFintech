<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당신의 계획적인 소비를 도와줄 월렛버핏!지금 사용해보세요.</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
a:link { color: #96B1F2; text-decoration: none;}
a:visited { color: #96B1F2; text-decoration: none;}
a:hover { color: #96B1F2; text-decoration: none;}
</style>
<script>
	$(document).ready(function() {
		var h = $(".navbar").height();
		var top = $(".navbar").css('padding-top').split('px')[0] * 1;
		var bottom = $(".navbar").css('padding-bottom').split('px')[0] * 1;
		var result = h + top + bottom;
		$("div.carousel-inner").css('margin-top', result);
	});
</script>
</head>
<body class="fontType">
	<header>
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<!-- Slide One - Set the background image for this slide in the line below -->
				<div class="carousel-item active"
					style="background-image:url('${path }/resources/KBFintech/img/w3.png')">
					<div class="carousel-caption d-none d-md-block">
								<h1 class="w31">1) 계좌와 카드를</h1>
								<h1 class="w32">등록하세요.</h1>
					</div>
				</div>
				<!-- Slide Two - Set the background image for this slide in the line below -->
				<div class="carousel-item"
					style="background-image: url('${path }/resources/KBFintech/img/w4.png')">
					<div class="carousel-caption d-none d-md-block">
								<h1 class="w41">2) 한도를 설정하세요.</h1>
					</div>
				</div>
				<!-- Slide Three - Set the background image for this slide in the line below -->
				<div class="carousel-item"
					style="background-image: url('${path }/resources/KBFintech/img/w5.png')">
					<div class="carousel-caption d-none d-md-block">
						<h1 class="w51">3) 랭킹을 통해서 보상을 받을 수 있습니다.</h1>
						<h3 class="w52">(1위 5000 포인트, 2위 3000포인트, 3위 1000포인트)</h3>
					</div>
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</header>
	<hr>
	<!-- Page Content -->
	<div class="container">
		<h1 class="my-4">월렛 버핏에 오신것을 환영합니다.</h1>

		<!-- 서비스 설명 -->
		<div class="row">
			<div class="col-lg-4 mb-4">
				<div class="card h-100">
					<h4 class="card-header" style="background-color: #96B1F2; color: #fff">한도설정(플래너)</h4>
					<div class="card-body">
						<p class="card-text">
						매달 플래너 설정을 통해 계획적인 지출이 가능합니다. 
						플래너 수정 시, 이체 포인트를 제공받지 못하고 전월 랭킹과 일일 랭킹에서 제외됩니다.
						즉, KB국민은행의 예*적금 가입 대상자 중 플래너를 수정하지 않고 계획적인 지출을 하신 분에게 포인트가 지급됩니다.
						KB 국민은행의 예*적금 상품을 가입하지 않으신 분도 플래너 기능은 사용하실 수 있습니다. </p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 mb-4">
				<div class="card h-100">
					<h4 class="card-header" style="background-color: #96B1F2; color: #fff">포인트</h4>
					<div class="card-body">
						<p class="card-text">
						1. 초기 상품 가입 시 포인트 지급 : 1000P<br/>
							조건 - 원금 만 원 이상 이체 시<br/>
						2. 가입 상품이 있을 때 해당 달의 한도 설정 달성 후 이체 포인트 지급 : 1000P<br/>
							조건 - 한도 수정 X, 만 원 이상 이체 시<br/>
						3. 전월 랭킹이나 일일 랭킹 순위 중 3위까지 랭킹 포인트 지급<br/>
						</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 mb-4">
				<div class="card h-100">
					<h4 class="card-header" style="background-color: #96B1F2; color: #fff">랭킹</h4>
					<div class="card-body">
						<p class="card-text">
						- 일일 랭킹순위는 매일 오전 12시에 갱신됩니다.<br/>
						- 일일 랭킹순위와 최종 랭킹순위는 차이가 있을 수 있습니다.<br/>
						- N달 연속 성공 시, 가산점 N점 (최대 12점)<br/>
						- 전 달 대비 이체금액 증가 시 가산점 부여<br/>
						- 사용자별 총점 N점에서 지출금액별 차감(최대 N점)<br/>
						- 최종순위점수: 일일 랭킹순위점수 * 0.4 + (이체금액 / (총 한도금액 - 지출금액)) * 0.4 + 이체금액구간별 점수*0.2<br/>
						- 이체구간별 점수: 1~9만원(10) / 10~49만원(20) / 50~99만원(30) / 100만원~(40)<br/>
						</p>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

		<!-- Portfolio Section -->
		<div class="row">
			<div class="col-lg-6 col-md-6 col-xs-6" align="left">
				<h2>KB국민은행 예·적금 상품</h2>		
			</div>
			<div class="col-lg-6 col-md-6 col-xs-6" align="right">
				<h2><a href="${path }/product/list">상품 페이지로 이동</a></h2>
			</div>
		</div>

		<div class="row">
		<c:forEach var="product" items="${productListS}" begin="0" end="2" varStatus="status">
			<div class="col-lg-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<c:if test="${status.index eq 0}">
						<a href="${path }/product/info?fin_prdt_cd=${product.fin_prdt_cd}"><img class="card-img-top"
							src="${path }/resources/KBFintech/img/kb1.PNG" alt=""></a>					
					</c:if>
					<c:if test="${status.index eq 1}">
						<a href="${path }/product/info?fin_prdt_cd=${product.fin_prdt_cd}"><img class="card-img-top"
							src="${path }/resources/KBFintech/img/kb2.PNG" alt=""></a>					
					</c:if>
					<c:if test="${status.index eq 2}">
						<a href="${path }/product/info?fin_prdt_cd=${product.fin_prdt_cd}"><img class="card-img-top"
							src="${path }/resources/KBFintech/img/kb3.PNG" alt=""></a>					
					</c:if>
					<div class="card-body">
						<h4 class="card-title">
							<a href="${path }/product/info?fin_prdt_cd=${product.fin_prdt_cd}">${product.fin_prdt_nm}</a>
						</h4>
						<p class="card-text">
						${product.best_rate}<br/>
						<c:forEach var="spcl_cnd" items="${product.spcl_cnd}">
						${spcl_cnd}<br/>
						</c:forEach><br/>
						<label style="font-weight: bold;">가입 대상 :</label><br/>
						${product.join_member}<br/><br/>
						<label style="font-weight: bold;">만기 후 이자율 :</label><br/>
						<c:forEach var="mtrt_int" items="${newTwo}">
						${mtrt_int}<br/>
						</c:forEach>
						</p>
					</div>
				</div>
			</div>		
		</c:forEach>
		</div>
		<!-- /.row -->
		<hr>

	</div>
	<!-- /.container -->

	<%@include file="../includes/footer.jsp"%>
</body>
</html>