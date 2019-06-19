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
<title>Insert title here</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
.plan-form {
	width: 70%;
	height: calc(1.0em + 0.65rem + 2px);
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 400;
	text-align: right;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #4C4C4C;
	border-radius: 0.25rem;
	-webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow
		0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s
		ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
	margin-top: 0.5rem !important;
}

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
	margin-left: 11.5rem !important;
}

.save {
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
	margin-top: 1.5rem !important;
}

.category-name {
	font-weight: bold;
	margin-top: 1.0rem !important;
}

.total-count {
	width: 20%;
	height: calc(1.0em + 0.65rem + 2px);
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 400;
	text-align: right;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #4C4C4C;
	border-radius: 0.25rem;
	-webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow
		0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s
		ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
	margin-top: 1.5rem !important;
}

.note {
	border: 1px solid #4C4C4C;
}

.note-td {
	border-left: 1px solid #4C4C4C;
	border-right: 1px solid #4C4C4C;
}

.won {
	font-weight: bold; margin-top : 1.0rem !important;
	margin-right: 2.8rem !important;
	margin-top: 1.0rem !important;
}

.footer {
	margin-bottom: 2.0rem !important;
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
	
	function save(){
		alert("한도 설정이 완료되었습니다. 메인페이지로 이동합니다.");
	}
	function changeSelect(){
		var yearCheck = $("#yearSelectBox option:checked").text();
		if(yearCheck > 2019){
			$(".yearChange").removeAttr("disabled");			
		}else{
			$(".yearChange").attr("disabled", true);
		}
	}
</script>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" />
	<fmt:formatDate value="${date}" type="DATE" pattern="YYYY" var="year"/>
	<fmt:formatDate value="${date}" type="DATE" pattern="M" var="month"/>
	<div>
		<form action="${path}/plan/planerOK" method="get">
			<table width="80%" align="center">
				<tr>
					<td colspan="1" width="10%" align="right">
						<select name="year" onchange="changeSelect();" id="yearSelectBox">
						    <c:forEach var="i" begin="${year}" end="${2119}">
							    <option value="${i}" <c:if test="${i == yearCheck}">selected="selected"</c:if>>${i}</option>						    
						    </c:forEach>
						</select>
						<select name="month">
							    <c:forEach var="i" begin="${1}" end="${month-1}">
								    <option value="${i}" disabled="disabled" class="yearChange">${i}</option>						    
							    </c:forEach>								
						    <c:forEach var="i" begin="${month}" end="${12}">
							    <option value="${i}" <c:if test="${i == month}">selected="selected"</c:if>>${i}</option>						    
						    </c:forEach>
						</select>
					</td>
					<td colspan="3" align="left" width="90%">
						<h1 class="title" id="titleDate">카드 한도 수정</h1>
					</td>
				</tr>
				<tr>
					<td width="25%" align="center"><h5 class="category-name">카페</h5></td>
					<td width="25%" align="center"><h5 class="category-name">편의점</h5></td>
					<td width="25%" align="center"><h5 class="category-name">오프라인쇼핑</h5></td>
					<td width="25%" align="center"><h5 class="category-name">온라인쇼핑</h5></td>
				</tr>
				<tr align="center">
					<td width="25%" align="center"><input type="text" name="cafe"
						size="100%" class="plan-form" placeholder="0" onkeyup="total();" /></td>
					<td width="25%" align="center"><input type="text"
						name="convenience" size="100%" class="plan-form" placeholder="0"
						onkeyup="total();" /></td>
					<td width="25%" align="center"><input type="text"
						name="offlineshopping" size="100%" class="plan-form" placeholder="0"
						onkeyup="total();" /></td>
					<td width="25%" align="center"><input type="text"
						name="onlineshopping" size="100%" class="plan-form" placeholder="0"
						onkeyup="total();" /></td>
				</tr>
				<tr>
					<td width="25%" align="center"><h5 class="category-name">외식</h5></td>
					<td width="25%" align="center"><h5 class="category-name">교통비</h5></td>
					<td width="25%" align="center"><h5 class="category-name">문화생활</h5></td>
					<td width="25%" align="center"><h5 class="category-name">기타</h5></td>
				</tr>
				<tr align="center">
					<td width="25%" align="center"><input type="text" name="eat"
						size="100%" class="plan-form" placeholder="0" onkeyup="total();" /></td>
					<td width="25%" align="center"><input type="text"
						name="traffic" size="100%" class="plan-form" placeholder="0"
						onkeyup="total();" /></td>
					<td width="25%" align="center"><input type="text"
						name="cultural" size="100%" class="plan-form" placeholder="0"
						onkeyup="total();" /></td>
					<td width="25%" align="center"><input type="text" name="other"
						size="100%" class="plan-form" placeholder="0" onkeyup="total();" /></td>
				</tr>
				<tr align="right">
					<td width="80%" colspan="4"><h3 class="won">
							Total : <input type="text" name="other" size="100%"
								class="total-count" placeholder="0" /> 원
						</h3></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="submit" value="저장"
						class="save" onclick="save();"/></td>
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
				<td width="100%" align="center"><h5 class="category-name">1.
						설정한 한도 금액만큼 사용하거나, 적게 사용한 사용자에 한하여 포인트가 지급됩니다.</h5></td>
			</tr>
			<tr>
				<td width="100%" align="center"><h5 class="category-name">2.
						사용자들끼리 랭킹을 매겨 설정한 한도 금액과 사용한 금액의 차이가 큰 사용자 3명에게 추가로 포인트가 지급됩니다.</h5></td>
			</tr>
			<tr>
				<td width="100%" align="center"><h5 class="category-name">3.
						정해진 한도를 수정하면 목표를 달성해도 포인트를 지급받지 못하고, 랭킹에서 제외됩니다.</h5></td>
			</tr>
		</table>
	</div>

	<p class="footer"></p>

	<%@include file="../includes/footer.jsp"%>
</body>
</html>












