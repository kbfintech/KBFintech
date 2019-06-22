<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>payment_test</title>

<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<section class="hero is-light">
		<div class="hero-body">
			<div id="p_title_box" class="container is-widescreen">
				<h1 class="title">결제정보입력</h1>
				<h2 class="subtitle">기본정보입력</h2>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">결제수단</label>
				</div>

				<div class="field-body">
					<div class="field">
						<p class="control">
							<label>카드</label>
							<!-- 						<div class="dropdown is-hoverable">
							<div class="dropdown-trigger">
								<button class="button" aria-controls="dropdown-menu">
									<span>선택</span> <span class="icon is-small"> <i
										class="fas fa-angle-down" aria-hidden="true"></i>
									</span>
								</button>
							</div>
							<div class="dropdown-menu" id="dropdown-menu" role="menu">
								<div class="dropdown-content">
									<a href="#" class="dropdown-item">카드</a> <a
										class="dropdown-item">계좌</a>
								</div>
							</div>
						</div> -->
						</p>
					</div>
				</div>

			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">상품명</label>
				</div>
				<div class="field-body">
					<div class="field">
						<p id="bProductName" class="label p_mInfo"></p>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">판매회사</label>
				</div>

				<div class="field-body">
					<div class="field">
						<p id="bCompanyName" class="label"></p>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">상품코드</label>
				</div>

				<div class="field-body">
					<div class="field">
						<p id="bCode" class="labe"></p>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">구매자명</label>
				</div>
				<div class="field-body">
					<div class="field">
						<!-- DB에 저장된 정보 불러오기 -->
						<p id="bCustomerName" class="label p_mInfo">이다혜</p>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">휴대폰번호</label>
				</div>
				<div class="field-body">
					<div class="field">
						<!-- DB에 저장된 정보 불러오기 -->
						<p id="bPhoneNum" class="label p_mInfo">010-4110-2482</p>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">가격</label>
				</div>

				<div class="field-body">
					<div class="field">
						<p id="bPrice" class="label"></p>
					</div>
				</div>
			</div>

			<div id="bt_payment" class="field is-horizontal">
				<a class="button is-medium is-fullwidth is-link is-outlined"
					onclick="verifyAmount()">결제</a>
			</div>

		</div>
	</section>


</body>
</html>