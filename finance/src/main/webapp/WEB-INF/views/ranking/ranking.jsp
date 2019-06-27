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

<script type="text/javascript">
	$(document).ready(function() {

		var h = $(".navbar").height();
		var top = $(".navbar").css('padding-top').split('px')[0] * 1;
		var bottom = $(".navbar").css('padding-bottom').split('px')[0] * 1;
		var result = h + top + bottom;
		$("맨위에 클래스 및 아이디 명").css('margin-top', result);

		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;

		$('#curMonth').html("[" + month + "월 일일랭킹순위]");
		$('#preMonth').html("[" + date.getMonth() + "월 최종랭킹순위]");

	});
</script>

</head>
<body>

	<div class="boxRank">
		<h4 id="curMonth" class="rank_title"></h4>
		<div class="rankBox">

			<table class="rankTable">
				<tr>
					<th>순위</th>
					<th>아이디</th>
					<th>총점</th>
				</tr>
				<tr>
					<td><img id="rankTop"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png" />1위</td>
					<td>${mID_Realtime1}</td>
					<td>${rankRealtime1}</td>
				</tr>
				<tr>
					<td><img class="crown"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png" />2위</td>
					<td>${mID_Realtime2}</td>
					<td>${rankRealtime2}</td>
				</tr>
				<tr>
					<td><img class="crown"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png" />3위</td>
					<td>${mID_Realtime3}</td>
					<td>${rankRealtime3}</td>
				</tr>
				<tr>
					<td>4위</td>
					<td>${mID_Realtime4}</td>
					<td>${rankRealtime4}</td>
				</tr>
				<tr>
					<td>5위</td>
					<td>${mID_Realtime5}</td>
					<td>${rankRealtime5}</td>
				</tr>
			</table>

		</div>
	</div>

	<div class="boxRank">
		<h4 id="preMonth" class="rank_title"></h4>
		<div class="rankBox">

			<table class="rankTable">
				<tr>
					<th>순위</th>
					<th>아이디</th>
					<th>총점</th>
				</tr>
				<tr>
					<td><img id="rankTop"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png" />1위</td>
					<td>${mID_RankTotal1}</td>
					<td>${rankTotal1}</td>
				</tr>
				<tr>
					<td><img class="crown"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png" />2위</td>
					<td>${mID_RankTotal2}</td>
					<td>${rankTotal2}</td>
				</tr>
				<tr>
					<td><img class="crown"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png" />3위</td>
					<td>${mID_RankTotal3}</td>
					<td>${rankTotal3}</td>
				</tr>
				<tr>
					<td>4위</td>
					<td>${mID_RankTotal4}</td>
					<td>${rankTotal4}</td>
				</tr>
				<tr>
					<td>5위</td>
					<td>${mID_RankTotal5}</td>
					<td>${rankTotal5}</td>
				</tr>
			</table>

		</div>
	</div>

	<div class="box">
		<h4 class="rank_title">- NOTE -</h4>
		<div class="detailBox">
			<p class="detailText">- 일일 랭킹순위는 매일 오전 12시에 갱신됩니다.</p>
			<p class="detailText">- 일일 랭킹순위와 최종 랭킹순위는 차이가 있을 수 있습니다.</p>
			<p class="detailText">- N달 연속 성공 시, 가산점 N점 (최대 12점)</p>
			<p class="detailText">- 전 달 대비 이체금액 증가 시 가산점 부여</p>
			<p class="detailText">- 사용자별 총점 N점에서 지출금액별 차감(최대 N점)</p>
			<p class="detailText">- 최종순위점수: 일일 랭킹순위점수 * 0.4 + (이체금액 / (총 한도금액
				- 지출금액)) * 0.4 + 이체금액구간별 점수*0.2</p>
			<p class="detailText">- 이체구간별 점수: 1~9만원(10) / 10~49만원(20) /
				50~99만원(30) / 100만원~(40)</p>
		</div>

	</div>

	<%@include file="../includes/footer.jsp"%>
</body>
</html>