<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세설명</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
.title {
	width: 26.5%;
	height: calc(1.5em + 0.65rem + 2px);
	margin: auto;
	padding: 0.075rem 0.85rem;
	font-size: 2rem;
	font-weight: 400;
	line-height: 1.5;
	color: #fff;
	background-color: #343a40;
	background-clip: padding-box;
	border: 1px solid #4C4C4C;
	border-radius: 1.5rem;
	-webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow
		0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s
		ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
	margin-top: 1.5rem !important;
	margin-bottom: 1.5rem !important;
}

.search {
	width: 10%;
	height: calc(1.5em + 0.65rem + 2px);
	margin: auto;
	padding: 0.075rem 0.85rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #fff;
	background-color: #343a40;
	background-clip: padding-box;
	border: 1px solid #4C4C4C;
	border-radius: 1.5rem;
	-webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow
		0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s
		ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
	margin-top: 3.5rem !important;
}

.update {
	width: 10%;
	height: calc(1.5em + 0.65rem + 2px);
	margin: auto;
	padding: 0.075rem 0.85rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #fff;
	background-color: #343a40;
	background-clip: padding-box;
	border: 1px solid #4C4C4C;
	border-radius: 1.5rem;
	-webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow
		0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s
		ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
	margin-top: 0.5rem !important;
}

.note {
	border: 1px solid #4C4C4C;
}

.note-td {
	border-left: 1px solid #4C4C4C;
	border-right: 1px solid #4C4C4C;
}

.footer {
	margin-bottom: 2.0rem !important;
}

.footer2 {
	margin-bottom: 3.0rem !important;
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
</style>
<script type="text/javascript">
	function changeSelect() {
		var yearCheck = $("#yearSelectBox option:checked").text();
		var list1 = new Array();
		var list2 = new Array();
		var objSel = document.getElementById("month");

		for (i = objSel.length; i >= 0; i--) {
			objSel.options[i] = null;
		}

		<c:forEach items="${dateList}" var="item1">
		list1.push("${item1}");
		</c:forEach>

		list1.sort(compNumber);
		function compNumber(a, b) {
			return a - b;
		}
	}

	function checkInput() {

		var account_money = '${accountVO_money}';
		var money = $('input[name=money]').val().replace(/,/gi, "");

		if (money == 0)
			alert("이체 금액을 입력해 주세요.");
		else if (parseInt(account_money) < parseInt(money)) {
			alert("보유하신 금액 한도 내에서 이체 금액을 입력해 주세요.");
		} else {
			alert("상품 가입이 완료되었습니다.");
		}
	}

	var rgx1 = /\D/g; // /[^0-9]/g 와 같은 표현
	var rgx2 = /(\d+)(\d{3})/;

	function getNumber(obj) {

		var num01;
		var num02;
		num01 = obj.value;
		num02 = num01.replace(rgx1, "");
		num01 = setComma(num02);
		obj.value = num01;

	}

	function setComma(inNum) {

		var outNum;
		outNum = inNum;
		while (rgx2.test(outNum)) {
			outNum = outNum.replace(rgx2, '$1' + ',' + '$2');
		}
		return outNum;

	}
</script>
</head>
<body>
	<div class="container">
		<h1 class="my-4" align="center">추천 결과를 확인하세요</h1>
		<hr>

		<div align="center">상품 설명</div>
		<div class="card col-lg-10 col-md-10 panel panel-primary"
			align="center">
			<div class="card-body">
				<div class="row">

					<h3 class="card-title panel-heading">
						<c:out value='${fin_prdt_nm}' />
					</h3>
					<h4 class="card-subtitle panel-heading text-muted">
						<c:out value='${best_rate}' />
					</h4>

					<p>
						<c:forEach var="detailInfo" items='${infoList}'>
							<c:out value='${detailInfo}' />
							<br>
						</c:forEach>
					</p>
					
				</div>
			</div>
		</div>
	</div>

	<div align="center" style="margin-top: 1rem;">

		<form action="${path }/product/point" method="post">
			<table width="80%" align="center">
				<tr align="center" width="100%">
					<td width="100%" align="center"><h5 class="category-name">가입자
							정보</h5></td>
				</tr>
				<tr>
					<table class="type09" width="60%" align="center">

						<tr>
							<th scope="row">이름</th>
							<td>${accountVO.getM_NAME()}</td>
						</tr>

						<tr>
							<th scope="row">계좌번호</th>
							<td>${accountVO.getACCOUNT_NUMBER()}</td>
						</tr>

						<tr>
							<th scope="row">계좌 내 보유 금액</th>
							<td>${acc_money}</td>
						</tr>
						<tr>
							<th scope="row">가입 시 이체하실 금액</th>

							<td><input name="money" id="name" type="text"
								onchange="getNumber(this);" onkeyup="getNumber(this);"
								style="text-align: right;"> 원</td>
						</tr>
					</table>
				</tr>
			</table>
			<table width="80%" align="center">
				<tr>

					<td align="center" colspan="4"><input type="submit"
						value="가입하기" class="update" onclick="checkInput();" /></td>
				</tr>
			</table>

		</form>
	</div>
	<p class="footer"></p>
	<hr class="note" />

	<div>
		<table width="80%" align="center">
			<tr align="center" width="100%">
				<td colspan="4"><h4 class="my-4">- NOTE -</h4></td>
			</tr>
			<tr>
				<td align="center"><h5 class="category-name">1. 총 1개의 상품에만
						가입하실 수 있습니다.</h5></td>
			</tr>
			<tr>
				<td align="center"><h5 class="category-name">2. 상품 가입 시
						10,000원을 초과하는 금액을 이체하실 경우 가입 축하 포인트 1,000점을 드립니다.</h5></td>
			</tr>
			<tr>
				<td align="center"><h5 class="category-name">3. 지출 플랜 달성 시
						가입하신 상품으로 저축 금액이 이체됩니다.</h5></td>
			</tr>
		</table>
	</div>
</body>
</html>