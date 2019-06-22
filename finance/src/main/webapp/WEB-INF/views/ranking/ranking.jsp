<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>랭킹순위쟁탈전</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<link
	href="<%=application.getContextPath()%>/resources/KBFintech/css/ranking.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="boxRank">
		<h4 class="rank_title">[6월 실시간 랭킹순위]</h4>
		
		<div class="rankBox"></div>
	</div>

	<div class="boxRank">
		<h4 class="rank_title">[5월 최종랭킹순위]</h4>

		<div class="rankBox"></div>
	</div>

	<div class="box">
		<h4 class="rank_title">- NOTE -</h4>
		<div class="detailBox">
			<p class="detailText">- 실시간 랭킹순위는 1주 단위로 갱신됩니다.</p>
			<p class="detailText">- 실시간 랭킹순위와 최종 랭킹순위는 차이가 있을 수 있습니다.</p>
			<p class="detailText">- N달 연속 성공 시, 가산점 N점 (최대 12점)</p>
			<p class="detailText">- 전 달 대비 이체금액 증가 시 가산점 부여</p>
			<p class="detailText">- 사용자별 총점 100점에서 지출금액*0.01% 차감</p>
			<p class="detailText">- 최종순위점수: 실시간 순위점수 * 0.4 + (이체금액 / (총 한도금액 - 지출금액)) * 0.4 + 이체금액구간별 점수*0.2</p>
			<p class="detailText">- 이체구간별 점수: 1~9만원(10) / 10~49만원(20) / 50~99만원(30) / 100만원~(40)</p>
		</div>

	</div>

	<%@include file="../includes/footer.jsp"%>
</body>
</html>