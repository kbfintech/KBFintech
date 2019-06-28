<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한도 조회</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
.control-form {
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
  border: 1px solid red;
  border-radius: 0.25rem;
  -webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
}
</style>
<script type="text/javascript">
	function checkDate() {
		var yearCheck = $("#yearSelectBox option:checked").text();
		var monthCheck = $("#monthSelectBox option:checked").text();
		var pldate = yearCheck + monthCheck;
		var list1 = new Array();

		<c:forEach items="${checkdateList}" var="item1">
		list1.push("${item1}");
		</c:forEach>

		for (var i = 0; i < list1.length; i++) {
			if (list1[i].indexOf(pldate) != -1) {
				var form = document.createElement('form');

				var input = new Array();
				for (var i = 1; i <= 2; i++) {
					input[i] = document.createElement("input");
					input[i].setAttribute('type', 'hidden');
					if (i == 1) {
						input[i].setAttribute('name', 'year');
						input[i].setAttribute('value', yearCheck);
					} else if (i == 2) {
						input[i].setAttribute('name', 'month');
						input[i].setAttribute('value', monthCheck);
					}
					form.appendChild(input[i]);
				}
				form.setAttribute('method', 'get');
				form.setAttribute('action', "${path}/plan/usingPayment");
				document.body.appendChild(form);
				form.submit();
				return false;
			}
		}
		alert("해당 날짜에 설정한 한도 내역이 존재하지 않습니다.");
	}

	function planUpdate() {
		var message = confirm("한도 수정 페이지로 이동하시겠습니까?");
		if (message == true) {
			location.href = "${path}/plan/planUpdate?year=" + ${searchyear}+"&month=" + ${searchmonth};
		} else {
			alert("한도 수정이 취소되었습니다.");
			return false;
		}

	}
</script>
</head>
<body>
	<c:set var="preplan" value="${planList}" />
	<c:set var="postplan" value="${planList}" />
	<c:set var="preuse" value="${usePriceList}" />
	<c:set var="postuse" value="${usePriceList}" />
	<jsp:useBean id="date" class="java.util.Date" />
	<fmt:formatDate value="${date}" type="DATE" pattern="YYYY"
		var="yearnow" />
	<fmt:formatDate value="${date}" type="DATE" pattern="M" var="monthnow" />
	
		<div class="container">
		<form action="${path}/plan/planerOK" method="get">
		<div class="row" style="margin-top: 100px;">
			<div class="form-group col-lg-12 col-md-12 col-xs-12" align="center">
				<select id="yearSelectBox">
					<c:forEach var="year" begin="2019" end="2119">
						<option value="${year}" <c:if test="${searchyear == year}">selected="selected"</c:if>>${year}</option>
					</c:forEach>
				</select> 년
				<select id="monthSelectBox" onchange="return checkDate();">
					<c:forEach var="month" begin="1" end="12">
						<option value="${month}" <c:if test="${searchmonth == month}">selected="selected"</c:if>>${month}</option>
					</c:forEach>
				</select> 월 <h3>설정 한도 VS <label style="color: red;">사용 금액</label></h3>
			</div>
		</div>

			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="cafe">카페</label>
				</div>
				<c:forEach var="plan" items="${preplan}" begin="0" end="0">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${preuse}" begin="0" end="0">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="convenience">편의점</label>
				</div>
				<c:forEach var="plan" items="${preplan}" begin="1" end="1">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${preuse}" begin="1" end="1">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
			</div>

			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="offlineshopping">오프라인 쇼핑</label>
				</div>
				<c:forEach var="plan" items="${preplan}" begin="2" end="2">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${preuse}" begin="2" end="2">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="onlineshopping">온라인 쇼핑</label>
				</div>
				<c:forEach var="plan" items="${preplan}" begin="3" end="3">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${preuse}" begin="3" end="3">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
			</div>
	
			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="eat">외식</label>
				</div>
				<c:forEach var="plan" items="${postplan}" begin="4" end="4">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${postuse}" begin="4" end="4">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="trip">여행</label>
				</div>
				<c:forEach var="plan" items="${postplan}" begin="5" end="5">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${postuse}" begin="5" end="5">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
			</div>

			<div class="row">
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="cultural">문화생활</label>
				</div>
				<c:forEach var="plan" items="${postplan}" begin="6" end="6">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${postuse}" begin="6" end="6">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<label for ="other">기타</label>
				</div>
				<c:forEach var="plan" items="${postplan}" begin="7" end="7">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="form-control" style="text-align: right;">${plan.PLD_PRICE}</h3>
					</div>
				</c:forEach>
				<c:forEach var="use" items="${postuse}" begin="7" end="7">
					<div class="col-lg-2 col-md-2 col-xs-2">
						<h3 class="control-form" style="text-align: right;">${use}</h3>
					</div>
				</c:forEach>
			</div>
		
			<div class="row">
				<div class="form-group col-lg-6 col-md-6 col-xs-6" align="center">
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<h3>Total</h3>
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2">
					<h3 class="form-control" style="text-align: right;">${totalCount}</h3>
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2">
					<h3 class="control-form" style="text-align: right;">${useTotalCount}</h3>
				</div>
			</div>
			
			<div class="row" style="margin-bottom: 40px;">
				<div class="form-group col-lg-12 col-md-12 col-xs-12" align="center">
					<input type="button" value="결제 내역" class="btn btnYellow" onclick="location.href='${path}/plan/paymentListCheck?year=${searchyear}&month=${searchmonth}'" />
				<c:if test="${searchyear >= yearnow && searchmonth >= monthnow }">
					<input type="button" value="한도 수정" style="margin-top: 10px;" class="btn btnYellow" onclick="return planUpdate();" />
				</c:if>
				</div>
			</div>
		
		</form>
	</div>

	<%@include file="../includes/footer.jsp"%>
</body>
</html>