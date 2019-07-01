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
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<title>당신의 계획적인 소비를 도와줄 월렛버핏!지금 사용해보세요.</title>
<script type="text/javascript">
   function transferCheck(){
      var transfer = $('input[name=prd_transfer]').val();
      if(parseInt(transfer) > parseInt("<c:out value='${account_money}'/>")){
         alert("이체 실패 : 현재 계좌에 있는 잔액보다 적은 금액을 입력하세요.");
         return false;
      } else{
         if(transfer == null || transfer == 0){
            var message = confirm("이체 금액이 0원입니다. 계속 진행하시겠습니까?");
            if(!message == true){
               return false;   
            }
            alert("메인페이지로 이동합니다.");      
         }else{
            alert("이체 성공! 메인페이지로 이동합니다.");                  
         }
      }
   }
   function transferCancle(){
      var message = confirm("이체를 취소할 경우 포인트 지급에서 제외됩니다. 이번달 이체를 취소하시겠습니까?");
      if (message == true) {
         alert("이체가 취소되었습니다. 메인페이지로 이동합니다.");
         location.href = "${path}/main/transferOK?prd_transfer=0&m_id=<c:out value='${M_ID}'/>";
      } else {
         return false;
      }
   }
</script>
</head>
<body class="fontType">

<div class="container">
   <jsp:useBean id="date" class="java.util.Date" />
   <fmt:formatDate value="${date}" type="DATE" pattern="YYYY" var="year"/>
   <fmt:formatDate value="${date}" type="DATE" pattern="M" var="month"/>
      <form action="${path}/main/transferOK" method="get">
         <input type="hidden" name="m_id" value="${M_ID}"/>
         <input type="hidden" name="year" value="${year}"/>
         <input type="hidden" name="month" value="${month}"/>
         <div class="row" style="margin-top: 30px;">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h3 align="center" style="background-color: #FFCC33; padding: 10px">이체하기</h3>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h5 align="center">아이디 : ${M_ID}</h5>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h5 align="center">이름 : ${M_NAME}</h5>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h5 align="center">날짜: ${year}년 ${month}월</h5>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h5 align="center">가입 상품 : ${productName}</h5>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h5 align="center">가입 이체 계좌 : ${account_number}</h5>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h5 align="center">현재 나의 계좌 금액 : ${account_money}</h5>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <h5 align="center">이체 금액 : <input type="text" name="prd_transfer"  placeholder="이체 가능 금액 : ${account_money}" style="text-align: center;"/></h5>
            </div>
         </div>

         
         <div class="row" style="margin-top: 30px;">
            <div class="form-group col-lg-12 col-md-12 col-xs-12">
               <input type="button" value="취소" class="btn btnYellow" style="margin-bottom: 10px;" onclick="return transferCancle();"/>
               <input type="submit" value="이체" class="btn btnYellow" onclick="return transferCheck();"/>
            </div>
         </div>
      
      </form>
   </div>

</body>
</html>