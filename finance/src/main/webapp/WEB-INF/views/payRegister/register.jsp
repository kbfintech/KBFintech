<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>

<script>
var result = "<%=(String)request.getAttribute("result")%>"
if(result != 'null') {
	if(result == 'allNeed') {
	}
	
	if(result == 'card') {
		alert("카드를 등록해야합니다.");
		$(document).ready(function(){
			$('.account').hide();
		});
	}
	
	if(result == 'all') {
		alert("모두 등록 되어있습니다.");
		$(document).ready(function(){
			$('.account').hide();
			$('.cards').hide();
			location.href="/";
		});
	}
	
}
</script>
<script>
$(document).ready(function(){
	$("#accountRegist").click(function(){
		$.ajax({
			url: "/account/inquery",
			type: "post",
			data: $('#accountRegitForm').serialize(),
			// ModelAndView로 보내면 못 읽음
			// map으로 보내야함.
			// json으로 정해주면 data.key로 받을 수 있음.
			dataType:"json",
			timeout:3000,
			cache:false,
			success:function(data){
				console.log(data);
				console.log(data.resultCode);
				if(data.resultCode == 1){
					alert("계좌가 등록되었습니다.");
					$('.account').hide();
				}else if(data.resultCode == 0){
					alert("계좌 등록에 실패했습니다.");	
				}
				
				
			},
			error:function(	xhr, status, error ){
				alert("err");
				console.log(err);
			}
		});
	});
	
	$('.length').keypress(function (event) { 
		
		if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) { 
			event.preventDefault();
		} 
		
		if(event.value.length > event.maxlength){
			event.value = event.value.slice(0, event.maxLength);
		}
	});

	$("#cardRegist").click(function(){
		$.ajax({
			url: "/card/inquery",
			type: "post",
			data: $('#cardRegitForm').serialize(),
			// ModelAndView로 보내면 못 읽음
			// map으로 보내야함.
			// json으로 정해주면 data.key로 받을 수 있음.
			dataType:"json",
			timeout:3000,
			cache:false,
			success:function(data){
				console.log(data);
				console.log(data.resultCode);
				if(data.resultCode == 1){
					alert("카드가 등록되었습니다.");
					location.href="/";
				}else if(data.resultCode == 0){
					alert("카드 등록에 실패했습니다.");	
				}
				
				
			},
			error:function(	xhr, status, error ){
				alert("err");
				console.log(err);
			}
		});
	});
});
</script>

<body class="fontType">
	<div class="container account">
		<div class= "row basic">
			<h1> 1) 계좌 등록</h1>
			<img class="cent" id ="accountImg" src="${path }/resources/KBFintech/img/KBAccount.png">
		</div>
		
		<form id="accountRegitForm" action="">
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="accountNum">계좌번호(14자리)</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="14" class="form-control length" name="accountNum"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="accountName">예금주</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="5" class="form-control length"  name="accountName"/>
				</div>
			</div>

			<div class="row basic">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<input class="btn btnYellow" type="button" value="계좌 등록" name="accountRegist" id="accountRegist">
				</div>
			</div>
			
		</form>
	</div>
	
	<div class="container cards">
		<div class="row basic">
			<h1> 2) 카드 등록</h1>
			<img class="cent" id ="cardImg" src="${path }/resources/KBFintech/img/KBCard.PNG">
		</div>
		
		<form id="cardRegitForm" action="">
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardNum">카드번호(카드 앞면의 16자리)</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="16" class="form-control length" name="cardNum"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardMonth">유효기간 월</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					
					<input type="text" maxlength="2" class="form-control length" name="cardMonth"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardYear">유효기간 연도</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="2" class="form-control length" name="cardYear"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardCVC">cvc 번호(카드 뒷면의 마지막 3자리)</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="3" class="form-control length" name="cardCVC"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardComp">카드 회사</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<select id="selectComp" name="selectComp">
						<option value="0" selected="selected"> KB카드 </option>
						<option value="1"> 신한카드 </option>
						<option value="2"> 우리카드 </option>
					</select>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-12 col-md-12 col-xs-12">
					<input class="btn btnYellow" type="button" value="카드 등록" name="cardRegist" id="cardRegist">
				</div>
			</div>
		
		</form>
	</div>

</body>
</html>