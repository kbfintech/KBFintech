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
<title>한도 설정</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
.total-count {
  display: block;
  width: 100%;
  height: calc(1.5em + 0.75rem + 2px);
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  -webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
}
</style>
<script type="text/javascript">
	function total() {
		var sum = 0;
		for (i = 0; i < 8; i++) {
			var account = document.getElementsByClassName('form-control')[i].value;
			account *= 1;
			sum += account;
		}
		sum += "";
		document.getElementsByClassName('total-count')[0].value = sum;
	}

	function save() {
		var yearCheck = $("#yearSelectBox option:checked").text();
		var monthCheck = $("#monthSelectBox option:checked").text();
		var pldate = yearCheck + monthCheck;
		var list1 = new Array();

		<c:forEach items="${dateList}" var="item1">
		list1.push("${item1}");
		</c:forEach>

		for (var i = 0; i < list1.length; i++) {
			if (list1[i].indexOf(pldate) != -1) {
				alert("이미 한도를 설정한 날짜입니다.");
				for (i = 0; i < 8; i++) {
					document.getElementsByClassName('form-control')[i].value = "";
				}
				return false;
			}
		}
		alert("한도 설정이 완료되었습니다. 조회페이지로 이동합니다.");
	}
	function changeSelect() {
		var yearCheck = $("#yearSelectBox option:checked").text();
		if (yearCheck > 2019) {
			$(".yearChange").removeAttr("disabled");
		} else {
			$(".yearChange").attr("disabled", true);
		}
	}
</script>
</head>
<body class="fontType">
	<jsp:useBean id="date" class="java.util.Date" />
	<fmt:formatDate value="${date}" type="DATE" pattern="YYYY" var="year"/>
	<fmt:formatDate value="${date}" type="DATE" pattern="M" var="month"/>
	
	<div class="container">
		<form action="${path}/plan/planerOK" method="get">
		<div class="row" style="margin-top: 100px;">
			<div class="form-group col-lg-12 col-md-12 col-xs-12" align="center">
				<select name="year" onchange="changeSelect();" id="yearSelectBox">
					<c:forEach var="i" begin="${year}" end="${2119}">
						<option value="${i}"
							<c:if test="${i == yearCheck}">selected="selected"</c:if>>${i}</option>
					</c:forEach>
				</select> <select name="month" id="monthSelectBox">
					<c:forEach var="i" begin="${1}" end="${month-1}">
						<option value="${i}" disabled="disabled" class="yearChange">${i}</option>
					</c:forEach>
					<c:forEach var="i" begin="${month}" end="${12}">
						<option value="${i}"
							<c:if test="${i == month}">selected="selected"</c:if>>${i}</option>
					</c:forEach>
				</select> <h1>카드 한도 설정</h1>
				한도 초과 시 결제 제한
				<input type="radio" name="limit" value="0" checked="checked"/>NO
				<input type="radio" name="limit" value="1"/>YES
			</div>
		</div>

			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="cafe">카페</label>
				</div>
				<div class="col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" maxlength="12" name="cafe" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="convenience">편의점</label>
				</div>
				
				<div class="col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" maxlength="12" name="convenience" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="offlineshopping">오프라인 쇼핑</label>
				</div>
				
				<div class="col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" maxlength="12" name="offlineshopping" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="onlineshopping">온라인 쇼핑</label>
				</div>
				
				<div class="col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" maxlength="12" name="onlineshopping" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="eat">외식</label>
				</div>
				
				<div class="col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" maxlength="12" name="eat" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="trip">여행</label>
				</div>
				
				<div class="col-lg-4 col-md-4 col-xs-4">
				<input type="text" style="text-align: right;" maxlength="12" name="trip" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="cultural">문화생활</label>
				</div>
				
				<div class="col-lg-4 col-md-4 col-xs-4">
				<input type="text" style="text-align: right;" maxlength="12" name="cultural" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="other">기타</label>
				</div>
				
				<div class="col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" maxlength="12" name="other" class="form-control" placeholder="0" onkeyup="total();" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-6 col-md-6 col-xs-6" align="center">
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<h3><label for ="other">Total</label></h3>
				</div>
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" name="totalCount" class="total-count" disabled="disabled" placeholder="0" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<h5 align="center">-NOTICE-</h5>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<h5 align="center">1. 사용 금액이 한도를 넘어설 경우 결제가 제한됩니다.</h5>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<h5 align="center">2. 정해진 한도 내에 금액을 사용한 후 만 원 이상 이체를 할 경우 포인트가 지급됩니다.</h5>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<h5 align="center">3. 한도를 수정 할 경우 이체 포인트와 랭킹 포인트 지급 대상에서 제외됩니다.</h5>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<input type="submit" value="저장" maxlength="12" class="btn btnYellow" onclick="return save();"/>
				</div>
			</div>
		
		</form>
	</div>
	<%@include file="../includes/footer.jsp"%>
</body>
</body>
</html>












