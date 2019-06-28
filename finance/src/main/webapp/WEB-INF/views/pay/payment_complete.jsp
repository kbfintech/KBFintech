<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제완료내역</title>

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

</head>
<body>
	<div class="receiptBox">

		<div class="titleHead">
			<h4>구매해주셔서 감사합니다.</h4>
			<hr />
		</div>
		<br />
		<div class="card">
			<div class="card-content">
				<p class="title">결제가 완료되었습니다.</p>
			</div>
			<footer class="card-footer">
				<p class="card-footer-item">
					<a href="<%=application.getContextPath()%>/payment/goodslist">쇼핑하러가기</a>
				</p>
			</footer>
		</div>
	</div>

</body>
</html>