<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제상품리스트</title>

<link
	href="<%=application.getContextPath()%>/resources/KBFintech/css/bulma/css/bulma.css"
	rel="stylesheet" type="text/css" />

<link
	href="<%=application.getContextPath()%>/resources/KBFintech/css/bulma/css/bulma.css.map"
	rel="stylesheet" type="text/css" />

<link
	href="<%=application.getContextPath()%>/resources/KBFintech/css/bulma/css/bulma.min.css"
	rel="stylesheet" type="text/css" />

<link
	href="<%=application.getContextPath()%>/resources/KBFintech/css/payment.css"
	rel="stylesheet" type="text/css" />

<link
	href="<%=application.getContextPath()%>/resources/KBFintech/css/basic.css"
	rel="stylesheet" type="text/css" />

<script
	src="<%=application.getContextPath()%>/resources/KBFintech/jquery/jquery-3.4.1.min.js"
	type="text/javascript"></script>

<script
	src="<%=application.getContextPath()%>/resources/KBFintech/javascript/payment_goodslist.js"
	type="text/javascript"></script>

<script
	src="<%=application.getContextPath()%>/resources/KBFintech/javascript/payment.js"
	type="text/javascript"></script>

</head>
<body>

	<div id="Context">
		<section class="section">
			<div class="container">
				<h1 class="title" align="center">결제상품리스트</h1>
				<p class="subtitle" align="center">온오프라인 매장 동시 판매!</p>
			</div>
		</section>

		<div class="tile is-ancestor">
			<div class="tile is-vertical is-parent">
				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(0)">
						<div class="card-image">
							<figure class="image is-4by3">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/편의점_쿠폰.jpg"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">모바일 편의점 쿠폰</p>
									<p class="subtitle is-3 gCompany" align="center">(주)GS편의점</p>
									<p class="subtitle is-6 gCode" align="center">552101</p>
									<p class="title is-5 gPrice" align="center">10,000won</p>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(1)">
						<div class="card-image">
							<figure class="image is-3by3">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/미국동부여행패키지.png"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">미국 동부 패키지여행(30일)</p>
									<p class="subtitle is-3 gCompany" align="center">(주)하나투어</p>
									<p class="subtitle is-6 gCode" align="center">552105</p>
									<p class="title is-5 gPrice" align="center">1,400,000won</p>
								</div>
							</div>

						</div>
					</div>
				</div>

				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(2)">
						<div class="card-image">
							<figure class="image is-3by3">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/외식_레스토랑.jpg"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">외식 어린이 1인 상품권</p>
									<p class="subtitle is-3 gCompany" align="center">더뷔페레스토랑</p>
									<p class="subtitle is-6 gCode" align="center">552104</p>
									<p class="title is-5 gPrice" align="center">60,000won</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="tile is-vertical is-parent">
				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(3)">
						<div class="card-image">
							<figure class="image is-3by3">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/투썸_딸기아이스음료.png"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">스트로베리라떼</p>
									<p class="subtitle is-3 gCompany" align="center">(주)투썸플레이스</p>
									<p class="subtitle is-6 gCode" align="center">552100</p>
									<p class="title is-5 gPrice" align="center">6,100won</p>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(4)">
						<div class="card-image">
							<figure class="image is-4by2">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/디즈니_전시회.jpg"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">디즈니 전시회 입장권(성인)</p>
									<p class="subtitle is-3 gCompany" align="center">DDP(동대문디자인플라자)</p>
									<p class="subtitle is-6 gCode" align="center">552106</p>
									<p class="title is-5 gPrice" align="center">12,000won</p>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(5)">
						<div class="card-image">
							<figure class="image is-4by3">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/supermarket.jpg"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">노브랜드 생필품 세트</p>
									<p class="subtitle is-3 gCompany" align="center">(주)이마트</p>
									<p class="subtitle is-6 gCode" align="center">552102</p>
									<p class="title is-5 gPrice" align="center">70,000won</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="tile is-vertical is-parent">
				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(6)">
						<div class="card-image">
							<figure class="image is-2by2">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/기타.jpg"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">기타</p>
									<p class="subtitle is-3 gCompany" align="center">낙원상가</p>
									<p class="subtitle is-6 gCode" align="center">552107</p>
									<p class="title is-5 gPrice" align="center">150,000won</p>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="tile is-child box">
					<div class="card" onclick="loadGoods(7)">
						<div class="card-image">
							<figure class="image is-3by3">
								<img
									src="<%=application.getContextPath()%>/resources/KBFintech/img/online_shopping.jpeg"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4 gName" align="center">에어프라이어</p>
									<p class="subtitle is-3 gCompany" align="center">(주)리빙코리아</p>
									<p class="subtitle is-6 gCode" align="center">552103</p>
									<p class="title is-5 gPrice" align="center">300,000won</p>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="tile is-child box">
					<div class="card">
						<div class="card-image">
							<figure class="image is-4by3">
								<img src="https://bulma.io/images/placeholders/1280x960.png"
									alt="Placeholder image">
							</figure>
						</div>
						<div class="card-content">
							<div class="media">
								<div class="media-content">
									<p class="title is-4" align="center">준비중</p>
									<p class="subtitle is-6" align="center">더 좋은 상품을 준비중에 있습니다.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>