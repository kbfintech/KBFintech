<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<%@ page import="java.util.*, java.text.*"%>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
.memInfoBox {
	margin-top: 6em;
	margin-bottom: 6em;
}

.category-name {
	font-weight: bold;
	margin-top: 1.0rem !important;
}

.plus {
	width: 2em;
	height: 2em;
}

.text {
	font-size: 1rem;
}

table.type09 {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
	font-size: 1rem;
}

table.type09 thead th {
	padding: 5px;
	font-weight: bold;
	vertical-align: top;
	color: #369;
	border-bottom: 3px solid #036;
	font-size: 1rem;
}

table.type09 tbody th {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #ccc;
	background: #f3f6f7;
	font-size: 1rem;
	background: #f3f6f7;
}

table.type09 td {
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #ccc;
	font-size: 1rem;
}

.all {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	border-top: 1px solid #ccc;
	font-size: 1rem;
}
</style>
<script type="text/javascript">
	function total() {
		var sum = 0;
		for (i = 0; i < 8; i++) {
			var account = document.getElementsByClassName('plan-form')[i].value;
			account *= 1;
			sum += account;
		}
		sum += "";
		document.getElementsByClassName('total-count')[0].value = sum;
	}

	function getCardNum() {
		if (null != "${cardVO.getC_ID()}") {
			var a = "${cardVO.getC_ID()}".substring(0, 4);
			var b = "${cardVO.getC_ID()}".substring(5, 9);
			var c = "${cardVO.getC_ID()}".substring(10, 14);
			var d = "${cardVO.getC_ID()}".substring(15, 20);
		}
	}
</script>
</head>
<body>
	<div class="memInfoBox">
		<table align="center">
			<tr>
				<td><h5 class="category-name">회원정보</h5></td>
			</tr>
			<tr>
				<table class="type09" width="60%" align="center">
					<tr>
						<th scope="row">ID</th>
						<td>${memberVO.getM_ID()}</td>
					</tr>

					<tr>
						<th scope="row">이름</th>
						<td>${memberVO.getM_NAME()}</td>
					</tr>

					<tr>
						<th scope="row">E-Mail</th>
						<td>${memberVO.getEMAIL()}</td>
					</tr>

					<tr>
						<th scope="row">보유 포인트</th>
						<td>${memberVO.getM_POINT()}</td>
					</tr>
				</table>
			</tr>

			<tr>
				<td><hr width="100%"></td>
			</tr>
			<tr>
				<td><h5 class="category-name" align="center">카드 관리</h5></td>
				<td colspan="4"><a href="${path }/payRegister"><img
						class="plus" align="right"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/plus.png"></a>
				</td>
			</tr>

			<tr>
				<c:choose>
					<c:when test="${cardVO.getC_ID() == null}">
						<tr class="text">
							<td><p align="center">등록된 카드가 없습니다.</p></td>
						</tr>
					</c:when>
					<c:otherwise>

						<table class="type09" width="60%" align="center">
							<tr>
								<th scope="row">카드 번호</th>
								<td>${cardNo}</td>
							</tr>

							<tr>
								<th scope="row">유효기간</th>
								<td>${cardVO.getC_MM()}/${cardVO.getC_YY()}</td>
							</tr>
							<tr>
								<th scope="row">카드사/카드종류</th>
								<td>${cardCom}/${cardType}</td>
							</tr>
						</table>

					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td><hr width="100%"></td>
			</tr>
			<tr>
			<tr>
				<td><h5 class="category-name" align="center">가입 상품 관리</h5></td>
				<td colspan="4"><a href="${path }/product/list"><img
						class="plus" align="right"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/plus.png"></a>
				</td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${productVO.getM_ID() == null}">
						<tr class="text">
							<td><p align="center">가입하신 상품이 없습니다.</p></td>
						</tr>
					</c:when>
					<c:otherwise>

						<table class="type09" width="60%" align="center">
							<tr>
								<th scope="row">상품명</th>
								<td>${productVO.getPRD_NAME()}</td>
							</tr>
							<tr>
								<th scope="row">계좌잔액</th>
								<td>${prd_transfer}</td>
							</tr>
						</table>

					</c:otherwise>
				</c:choose>
			</tr>

			<tr>
				<td><hr width="100%"></td>
			</tr>
			<tr>
				<td><h5 class="category-name" align="center">내 플래너</h5></td>
				<td colspan="4"><a href="${path }/plan/planer"><img
						align="right" class="plus"
						src="<%=application.getContextPath()%>/resources/KBFintech/img/plus.png"></a>
				</td>
			</tr>
			<tr>
				<td>
					<p align="center">
						<%
							java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ");
							String today = formatter.format(new java.util.Date());
							out.print(today);
						%>기준
					</p>
				</td>
			</tr>
			<tr>


				<c:choose>
					<c:when test="${pld_price.isEmpty() == true || s_price[0] == ''}">
						<tr class="text">
							<td width="10%"><p align="center">작성하신 플랜이 없습니다.</p></td>
						</tr>
					</c:when>
					<c:otherwise>

						<table class="type09" align="center" width="60%">
							<tr>
								<th scope="row">카페</th>
								<td>${s_price[0]}/<b>${pld_price.get(0)}</b> 원
								</td>
								<th scope="row">편의점</th>
								<td>${s_price[1]}/<b>${pld_price.get(1)}</b> 원
								</td>
							</tr>
							<tr>
								<th scope="row">오프라인쇼핑</th>
								<td>${s_price[2]}/<b>${pld_price.get(2)}</b> 원
								</td>
								<th scope="row">온라인쇼핑</th>
								<td>${s_price[3]}/<b>${pld_price.get(3)}</b> 원
								</td>
							</tr>
							<tr>
								<th scope="row">외식</th>
								<td>${s_price[4]}/<b>${pld_price.get(4)}</b> 원
								</td>
								<th scope="row">여행</th>
								<td>${s_price[5]}/<b>${pld_price.get(5)}</b> 원
								</td>
							</tr>
							<tr>
								<th scope="row">문화생활</th>
								<td>${s_price[6]}/<b>${pld_price.get(6)}</b> 원
								</td>
								<th scope="row">기타</th>
								<td>${s_price[7]}/<b>${pld_price.get(7)}</b> 원
								</td>
							</tr>
							<tr>
								<td colspan="4" align="right">총 ${s_price[8]} / ${pld_sum}원</td>
							</tr>
						</table>

					</c:otherwise>
				</c:choose>

			</tr>

			<tr>
				<td><hr width="100%"></td>
			</tr>
		</table>
	</div>

	<%@include file="../includes/footer.jsp"%>
</body>
</html>
