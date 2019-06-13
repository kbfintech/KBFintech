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
$(document).ready(function(){
	
	$('.form-control').keypress(function (event) { 
		
		if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) { 
			event.preventDefault();
			alert("숫자만 입력하세요.");
		} 
		
		if(event.value.length > event.maxLength{
			event.value = event.value.slice(0, event.maxLength);
		}
	});

	$("#cardRegist").click(function(){
		$.ajax({
			url: "${path}/card/inquery",
			type: "post",
			data: $('#regitForm').serialize(),
			// ModelAndView로 보내면 못 읽음
			// map으로 보내야함.
			// json으로 정해주면 data.key로 받을 수 있음.
			dataType:"json",
			timeout:3000,
			cache:false,
			success:function(data){
				alert("success");
				console.log(data);
				console.log(data.resultCode);
				if(data.resultCode == 1){
					alert("카드가 등록되었습니다.");
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
	<div class="container">
		<div class="row basic">
			<img class="cent" id ="cardImg" src="${path }/resources/KBFintech/img/KBCard.PNG">
		</div>
		
		<form id="regitForm" action="">
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardNum">카드번호(카드 앞면의 12자리)</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="12" class="form-control" name="cardNum"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardMonth">유효기간 월</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					
					<input type="text" maxlength="2" class="form-control" name="cardMonth"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardYear">유효기간 연도</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="2" class="form-control" name="cardYear"/>
				</div>
			</div>
			
			<div class="row basic">
				<div class="form-group col-lg-4 col-md-4 col-xs-4">
					<label for ="cardCVC">cvc 번호(카드 뒷면의 마지막 3자리)</label>
				</div>
				
				<div class="col-lg-8 col-md-8 col-xs-8">
					<input type="text" maxlength="3" class="form-control" name="cardCVC"/>
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