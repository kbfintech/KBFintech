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
<title>한도 수정</title>
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
	
	function update(){
		alert("한도 수정이 완료되었습니다. 메인페이지로 이동합니다.");
	}
</script>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" />
	<c:set var="preplan" value="${planList}"/>
	<c:set var="postplan" value="${planList}"/>
	<fmt:formatDate value="${date}" type="DATE" pattern="YYYY" var="year"/>
	<fmt:formatDate value="${date}" type="DATE" pattern="M" var="month"/>
	
	<div class="container">
	<form action="${path}/plan/planUpdateOK" method="get">
		<input type="hidden" name="year" value="${plyear}"/>
		<input type="hidden" name="month" value="${plmonth}"/>
		
		<div class="row " style="margin-top: 100px;">
			<div class="form-group col-lg-12 col-md-12 col-xs-12" align="center">
				<h1>${plyear}년 ${plmonth}월 카드 한도 수정</h1>
				한도 초과 시 결제 제한
				<input type="radio" name="limit" value="0" <c:if test="${limitCheck == 0 }">checked="checked"</c:if>/>NO
				<input type="radio" name="limit" value="1" <c:if test="${limitCheck == 1 }">checked="checked"</c:if>/>YES
			</div>
		</div>
			<div class="row">
				<c:forEach var="plan" items="${preplan}" begin="0" end="1" varStatus="status">
					<c:if test="${status.index eq 0}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="cafe">카페</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="cafe" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
					<c:if test="${status.index eq 1}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="convenience">편의점</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="convenience" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
				</c:forEach>
			</div>
			
			<div class="row">
				<c:forEach var="plan" items="${preplan}" begin="2" end="3" varStatus="status">
					<c:if test="${status.index eq 2}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="offlineshopping">오프라인 쇼핑</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="offlineshopping" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
					<c:if test="${status.index eq 3}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="onlineshopping">온라인 쇼핑</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="onlineshopping" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
				</c:forEach>
			</div>

			<div class="row">
				<c:forEach var="plan" items="${postplan}" begin="4" end="5" varStatus="status">
					<c:if test="${status.index eq 4}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="eat">외식</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="eat" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
					<c:if test="${status.index eq 5}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="trip">여행</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="trip" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
				</c:forEach>
			</div>

			<div class="row">
				<c:forEach var="plan" items="${postplan}" begin="6" end="7" varStatus="status">
					<c:if test="${status.index eq 6}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="cultural">문화생활</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="cultural" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
					<c:if test="${status.index eq 7}">
						<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
							<label for ="other">기타</label>
						</div>
						<div class="col-lg-4 col-md-4 col-xs-4">
							<input type="text" style="text-align: right;" maxlength="12" name="other" class="form-control" value="${plan.PLD_PRICE}" onkeyup="total();" />
						</div>
					</c:if>					
				</c:forEach>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-6 col-md-6 col-xs-6" align="center">
				</div>
				<div class="form-group col-lg-2 col-md-2 col-xs-2" align="center">
					<h3><label for ="other">Total</label></h3>
				</div>
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<input type="text" style="text-align: right;" name="totalCount" class="total-count" disabled="disabled" value="${totalCount}" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<h3 align="center">-WARNNING-</h3>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<h5 align="center">1. 한도 수정 시 이체 포인트와 랭킹 포인트 지급 대상에서 제외됩니다.</h5>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-lg-6 col-md-6 col-xs-6">
					<input type="submit" value="수정" class="btn btnYellow" onclick="update();"/>
				</div>
				<div class="form-group col-lg-6 col-md-6 col-xs-6">
					<input type="button" value="취소" class="btn btnYellow" onclick="location.href='${path}/plan/usingPayment?year=${plyear}&month=${plmonth}'"/>
				</div>
			</div>
		
		</form>
	</div>

	<%@include file="../includes/footer.jsp"%>
</body>
</html>












