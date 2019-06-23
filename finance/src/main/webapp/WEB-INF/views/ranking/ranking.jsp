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

<script
	src="<%=application.getContextPath()%>/resources/KBFintech/javascript/ranking.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		
		$('#curMonth').html("[" + month +"월 실시간 랭킹순위]");
		$('#preMonth').html("[" + date.getMonth()+"월 최종랭킹순위]");
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
		   <td><img id="rankTop" src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png"/>1위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td><img class="crown" src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png"/>2위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td><img class="crown" src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png"/>3위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td>4위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td>5위</td>
		   <td>55577854</td>
		   <td>55577855</td>
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
		   <td><img id="rankTop" src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png"/>1위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td><img class="crown" src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png"/>2위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td><img class="crown" src="<%=application.getContextPath()%>/resources/KBFintech/img/crown.png"/>3위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td>4위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		 <tr>
		   <td>5위</td>
		   <td>55577854</td>
		   <td>55577855</td>
		 </tr>
		</table>
		
		</div>
	</div>

	<div class="box">
		<h4 class="rank_title">- NOTE -</h4>
		<div class="detailBox">
			<p class="detailText">- 실시간 랭킹순위는 1주 단위로 갱신됩니다.</p>
			<p class="detailText">- 실시간 랭킹순위와 최종 랭킹순위는 차이가 있을 수 있습니다.</p>
			<p class="detailText">- N달 연속 성공 시, 가산점 N점 (최대 12점)</p>
			<p class="detailText">- 전 달 대비 이체금액 증가 시 가산점 부여</p>
			<p class="detailText">- 사용자별 총점 100점에서 지출금액*0.01% 차감</p>
			<p class="detailText">- 최종순위점수: 실시간 순위점수 * 0.4 + (이체금액 / (총 한도금액
				- 지출금액)) * 0.4 + 이체금액구간별 점수*0.2</p>
			<p class="detailText">- 이체구간별 점수: 1~9만원(10) / 10~49만원(20) /
				50~99만원(30) / 100만원~(40)</p>
		</div>

	</div>

	<%@include file="../includes/footer.jsp"%>
</body>
</html>