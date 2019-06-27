<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>티끌모아건물주</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
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

	<!-- Page Content -->
	<div class="container">

		<h1 class="my-4">뱅크 샐러드에 오신것을 환영합니다.</h1>

		<!-- 서비스 설명 -->
		<div class="row">
			<div class="col-lg-4 mb-4">
				<div class="card h-100">
					<h4 class="card-header">한도설정(플래너)</h4>
					<div class="card-body">
						<p class="card-text">플래너를 사용하면 계획적인 지출을 할 수 있습니다. 플래너를 수정할 수는 있으나, 수정할 경우 전월 랭킹과 일일 랭킹에서 제외됩니다. 즉, KB국민은행의 예*적금 가입 대상자 중 플래너를 수정하지 않고 계획적인 지출을 하신 분에게 포인트를 지급합니다.
						KB 국민은행의 예*적금 상품을 가입하지 않으신 분도 플래너 기능은 사용하실 수 있습니다. </p>
					</div>
					<div class="card-footer">
						<a href="${path }/plan/planer" class="btn btn-primary">플랜 더 보기</a>
					</div>
				</div>
			</div>
			<div class="col-lg-4 mb-4">
				<div class="card h-100">
					<h4 class="card-header">포인트</h4>
					<div class="card-body">
						<p class="card-text">수백만가지 경우의 수를 계산하는 추천 알고리즘이 나를 위한 맞춤 상품을
							찾아드립니다.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary">더 보기</a>
					</div>
				</div>
			</div>
			<div class="col-lg-4 mb-4">
				<div class="card h-100">
					<h4 class="card-header">랭킹</h4>
					<div class="card-body">
						<p class="card-text">뱅크샐러드의 추천에는 광고도, 홍보도 없습니다. 오직 객관적인 데이터만을
							활용합니다.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary">더 보기</a>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

		<!-- Portfolio Section -->
		<h2>Portfolio Heading</h2>

		<div class="row">
			<div class="col-lg-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<a href="#"><img class="card-img-top"
						src="http://placehold.it/700x400" alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a href="#">Project One</a>
						</h4>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Amet numquam aspernatur eum quasi sapiente
							nesciunt? Voluptatibus sit, repellat sequi itaque deserunt,
							dolores in, nesciunt, illum tempora ex quae? Nihil, dolorem!</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<a href="#"><img class="card-img-top"
						src="http://placehold.it/700x400" alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a href="#">Project Two</a>
						</h4>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nam viverra euismod odio, gravida pellentesque
							urna varius vitae.</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<a href="#"><img class="card-img-top"
						src="http://placehold.it/700x400" alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a href="#">Project Three</a>
						</h4>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Quos quisquam, error quod sed cumque, odio
							distinctio velit nostrum temporibus necessitatibus et facere
							atque iure perspiciatis mollitia recusandae vero vel quam!</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<a href="#"><img class="card-img-top"
						src="http://placehold.it/700x400" alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a href="#">Project Four</a>
						</h4>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nam viverra euismod odio, gravida pellentesque
							urna varius vitae.</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<a href="#"><img class="card-img-top"
						src="http://placehold.it/700x400" alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a href="#">Project Five</a>
						</h4>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nam viverra euismod odio, gravida pellentesque
							urna varius vitae.</p>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-sm-6 portfolio-item">
				<div class="card h-100">
					<a href="#"><img class="card-img-top"
						src="http://placehold.it/700x400" alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a href="#">Project Six</a>
						</h4>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Itaque earum nostrum suscipit ducimus nihil
							provident, perferendis rem illo, voluptate atque, sit eius in
							voluptates, nemo repellat fugiat excepturi! Nemo, esse.</p>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

		<!-- Features Section -->
		<div class="row">
			<div class="col-lg-6">
				<h2>Modern Business Features</h2>
				<p>The Modern Business template by Start Bootstrap includes:</p>
				<ul>
					<li><strong>Bootstrap v4</strong></li>
					<li>jQuery</li>
					<li>Font Awesome</li>
					<li>Working contact form with validation</li>
					<li>Unstyled page elements for easy customization</li>
				</ul>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
					Corporis, omnis doloremque non cum id reprehenderit, quisquam totam
					aspernatur tempora minima unde aliquid ea culpa sunt. Reiciendis
					quia dolorum ducimus unde.</p>
			</div>
			<div class="col-lg-6">
				<img class="img-fluid rounded" src="http://placehold.it/700x450"
					alt="">
			</div>
		</div>
		<!-- /.row -->

		<hr>

		<!-- Call to Action Section -->
		<div class="row mb-4">
			<div class="col-md-8">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
					Molestias, expedita, saepe, vero rerum deleniti beatae veniam harum
					neque nemo praesentium cum alias asperiores commodi.</p>
			</div>
			<div class="col-md-4">
				<a class="btn btn-lg btn-secondary btn-block" href="#">Call to
					Action</a>
			</div>
		</div>

	</div>
	<!-- /.container -->

	<%@include file="../includes/footer.jsp"%>
</body>
</html>