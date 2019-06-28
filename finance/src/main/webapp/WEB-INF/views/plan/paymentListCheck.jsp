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
<title>결제내역</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
.updateBtn {
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
<script type="text/javascript" > 
$(document).ready(function(){
	$(".selectBox").click(function(i){
        $(".update").hide();
        $(".update").eq($(".selectBox").index(this)).show(); //메서드
    });
	
	$(".updateBtn").click(function(i){
        var idx = $(".idx").eq($(".updateBtn").index(this)).val(); //메서드
        var b_cd = $(".b_CD").eq($(".updateBtn").index(this)).val(); //메서드
        var b_name = $(".b_name").eq($(".updateBtn").index(this)).val(); //메서드
        var pay_price = $(".pay_price").eq($(".updateBtn").index(this)).val(); //메서드
        var pay_date = $(".pay_date").eq($(".updateBtn").index(this)).val(); //메서드
        var selectbox = $(".selectBox option:selected").eq($(".updateBtn").index(this)).val(); //메서드
	    //alert(idx + " " + b_name + " " + pay_price + " " + pay_date + " " + selectbox);
		//location.href="${path}/plan/paymentUpdate?b_name="+b_name+"&pay_price="+pay_price+"&pay_date="+pay_date+"&selectbox="+delectbox;
		//$(location).attr('href', "${path}/plan/paymentUpdate?b_name="+b_name+"&pay_price="+pay_price+"&pay_date="+pay_date+"&selectbox="+delectbox);
		var yearCheck = $("#yearSelectBox option:checked").text();
		var monthCheck = $("#monthSelectBox option:checked").text();
		var form = document.createElement('form');

			var input = new Array();
		     for(var i = 1; i <=6; i++){
		        input[i] = document.createElement("input");
				input[i].setAttribute('type', 'hidden');
		        if(i==1){
					input[i].setAttribute('name', 'year');
					input[i].setAttribute('value', yearCheck);
		        } else if(i==2){
					input[i].setAttribute('name', 'month');
					input[i].setAttribute('value', monthCheck);
		        } else if(i==3){
					input[i].setAttribute('name', 'pay_date');
					input[i].setAttribute('value', pay_date);
		        } else if(i==4){
					input[i].setAttribute('name', 'b_type');
					input[i].setAttribute('value', selectbox);
		        } else if(i==5) {
					input[i].setAttribute('name', 'idx');
					input[i].setAttribute('value', idx);
		        } else{
					input[i].setAttribute('name', 'b_cd');
					input[i].setAttribute('value', b_cd);		        	
		        }
		        form.appendChild(input[i]);
		     }
			form.setAttribute('method', 'get');
			form.setAttribute('action', "${path}/plan/paymentUpdate");
			document.body.appendChild(form);
			form.submit();

    });
});
function checkDate(){
		var yearCheck = $("#yearSelectBox option:checked").text();
		var monthCheck = $("#monthSelectBox option:checked").text();
	var pldate = yearCheck + monthCheck;
	var list1 = new Array();
	
	<c:forEach items="${checkdateList}" var="item1">
	list1.push("${item1}");
	</c:forEach>
	
	for ( var i = 0; i < list1.length; i++) {
		if (list1[i].indexOf(pldate) != -1) {
			var form = document.createElement('form');

			var input = new Array();
		     for(var i = 1; i <= 2; i++){
		        input[i] = document.createElement("input");
				input[i].setAttribute('type', 'hidden');
		        if(i==1){
					input[i].setAttribute('name', 'year');
					input[i].setAttribute('value', yearCheck);
		        } else if(i==2){
					input[i].setAttribute('name', 'month');
					input[i].setAttribute('value', monthCheck);
		        }
		        form.appendChild(input[i]);
		     }
			form.setAttribute('method', 'get');
			form.setAttribute('action', "${path}/plan/paymentListCheck");
			document.body.appendChild(form);
			form.submit();
			return false;
		}
	} 
	alert("해당 날짜에 결제한 내역이 존재하지 않습니다.");
}
</script>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" />
	<fmt:formatDate value="${date}" type="DATE" pattern="YYYY" var="nowyear"/>
	<fmt:formatDate value="${date}" type="DATE" pattern="M" var="nowmonth"/>
	
	<div class="container">
	
	<div class="row" style="margin-top: 100px;">
		<div class="form-group col-lg-12 col-md-12 col-xs-12" align="center">
			<select id="yearSelectBox">
				<c:forEach var="year" begin="2019" end="2119">
					<option value="${year}" <c:if test="${yearnow == year}">selected="selected"</c:if>>${year}</option>
				</c:forEach>
			</select> 년					
			<select id="monthSelectBox" onchange="return checkDate();">
				<c:forEach var="month" begin="1" end="12">
					<option value="${month}" <c:if test="${monthnow == month}">selected="selected"</c:if>>${month}</option>
				</c:forEach>
			</select> 월 <h1>${monthnow}월 결제 내역</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-lg-12 col-md-12 col-xs-12" align="center">
			<h6 style="color:#636363; font-size: 15px;">카테고리를 클릭하면 카테고리 수정이 가능합니다.</h6>
		</div>
	</div>
	
	<div class="row">
		<c:forEach var="paymentList" items="${list}" varStatus="status">
			<div class="form-group col-lg-3 col-md-3 col-xs-3" align="left" style="border: 1px solid #000;">
				<h5>내역 : <label style="font-weight: bold;">${paymentList.b_NAME}</label></h5>
				<h5>금액 : <label style="font-weight: bold;">${paymentList.PAY_PRICE}</label></h5>
				<h5>날짜 : <label style="font-weight: bold;">${paymentList.PAY_DATE}</label></h5>
				카테고리 : <select  class="selectBox" name="b_type" style="-webkit-appearance: none; border: none; text-align-last:left; width: 60%">
					<option <c:if test="${paymentList.b_TYPE == '카페'}">selected="selected"</c:if>>카페</option>
					<option <c:if test="${paymentList.b_TYPE == '편의점'}">selected="selected"</c:if>>편의점</option>
					<option <c:if test="${paymentList.b_TYPE == '오프라인쇼핑'}">selected="selected"</c:if>>오프라인쇼핑</option>
					<option <c:if test="${paymentList.b_TYPE == '온라인쇼핑'}">selected="selected"</c:if>>온라인쇼핑</option>
					<option <c:if test="${paymentList.b_TYPE == '외식'}">selected="selected"</c:if>>외식</option>
					<option <c:if test="${paymentList.b_TYPE == '여행'}">selected="selected"</c:if>>여행</option>
					<option <c:if test="${paymentList.b_TYPE == '문화생활'}">selected="selected"</c:if>>문화생활</option>
					<option <c:if test="${paymentList.b_TYPE == '기타'}">selected="selected"</c:if>>기타</option>
				</select>
				<div class="update" style="display: none;">
					<input type="button" value="수정" class="updateBtn" style="background-color: #FFCC33;" onclick="update();"/>
					<input type="hidden" class="idx" name="idx" value="${paymentList.p_IDX}"/>
					<input type="hidden" class="b_CD" name="b_CD" value="${paymentList.b_CD}"/>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="row">
		<div class="form-group col-lg-12 col-md-12 col-xs-12" align="center">
			<input type="button" value="카테고리별 총 사용 금액" class="btn btnYellow" onclick="location.href='${path}/plan/usingPayment?year=${yearnow}&month=${monthnow}'" />
		</div>
	</div>
	</div>
	
	<%@include file="../includes/footer.jsp"%>
</body>
</html>















