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
<title>Insert title here</title>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<style type="text/css">
.plan-form {
	width: 10%;
	height: calc(1.5em + 0.65rem + 2px);
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

.price-form {
	width: 70%;
	height: calc(1.5em + 0.65rem + 2px);
	padding: 0.365rem 0.75rem;
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

.category-name {
	font-weight: bold;
	margin-top: 1.0rem !important;
}

.plan-null {
	width: 100%;
	height: calc(1.5em + 0.65rem + 2px);
	padding: 7.5% 25.5%;
	font-size: 1.5rem;
	font-weight: bold;
	line-height: 1.5;
	color: #343a40;
	margin-top: 1.5rem !important;
	margin-bottom: 1.5rem !important;
}

.total-count {
	width: 20%;
	height: calc(1.5em + 0.65rem + 2px);
	padding: 0.365rem 0.75rem;
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
.footer2 {
	margin-bottom: 3.0rem !important;
}
</style>
<script type="text/javascript">
	function changeSelect(){
		var yearCheck = $("#yearSelectBox option:checked").text();
		var list1 = new Array();
		var list2 = new Array();
		var objSel = document.getElementById("month");

		for(i=objSel.length; i>=0; i--){
		  objSel.options[i]=null;
		}
	
		<c:forEach items="${dateList}" var="item1">
		list1.push("${item1}");
		</c:forEach>
		
		list1.sort(compNumber);
		function compNumber(a, b) {
		  return a - b;
		}
		for ( var i = 0; i < list1.length; i++) {
			if (list1[i].indexOf(yearCheck) != -1) {
    			list1[i] = list1[i].replace(yearCheck, "");
    			$("#month").append("<option value='"+list1[i]+"'>"+list1[i]+"</option>");
			}
		} 
	}
	
	function planUpdate(){
		var message = confirm("수정을 하면 포인트 지급이 불가하고 랭킹에 포함되지 않습니다. 정말 수정하시겠습니까?");
		if(message == true){
			alert("수정페이지로 이동합니다.");
			location.href="${path}/plan/planUpdate";
		}else{
			alert("수정이 취소되었습니다.");
			return false;
		}
		
	}
</script>
</head>
<body>
	<c:set var="preplan" value="${planList}"/>
	<c:set var="postplan" value="${planList}"/>
	<jsp:useBean id="date" class="java.util.Date" />
	<fmt:formatDate value="${date}" type="DATE" pattern="YYYY" var="year"/>
	<fmt:formatDate value="${date}" type="DATE" pattern="M" var="month"/>
	<div>
		<form action="${path}/plan/planSearchOK" method="get">
			<table width="80%" align="center">
				<tr>
					<td align="center">
						<h1 class="title" id="titleDate">카드 한도 조회</h1>
					</td>
				</tr>
				<tr>
					<td align="center">
						<select name="year" class="plan-form" onchange="changeSelect();" id="yearSelectBox">
						    <c:forEach var="i" items="${yearList}" begin="0">
							    <option value="${i}">${i}</option>						    
						    </c:forEach>
						</select> 년 
						<select name="month" class="plan-form" id="month">
						    <c:forEach var="i" items="${dateList}">
						    	<c:if test="${fn:contains(i, year)}">
						    	<c:set var="monthlist" value="${fn:replace(i, year, '')}" />
							    <option value="${monthlist}">${monthlist}</option>	
							    </c:if>					    
						    </c:forEach>
						</select> 월
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="submit" value="조회" class="search"/>
					</td>
				</tr>
			</table>
		</form>
	</div>	
	
	<p class="footer"></p>
	<hr class="note" />
	<p class="footer2"></p>
	
	<c:if test="${preplan == null || postplan == null}">
		<h5 class="plan-null">카드 한도를 조회하고 싶은 년도와 월을 선택 후 조회 버튼을 누르세요.</h5>		
	</c:if>
	
	<c:if test="${preplan != null && postplan != null}">
	<div>
			<table width="80%" align="center">
				<tr>
					<td width="25%" align="center"><h5 class="category-name">카페</h5></td>
					<td width="25%" align="center"><h5 class="category-name">편의점</h5></td>
					<td width="25%" align="center"><h5 class="category-name">오프라인쇼핑</h5></td>
					<td width="25%" align="center"><h5 class="category-name">온라인쇼핑</h5></td>
				</tr>
				<tr align="center">
					<c:forEach var="plan" items="${preplan}" begin="0" end="3">
						<td width="25%" align="center">
							<h3 class="price-form" >${plan.PLD_PRICE}</h3>
						</td>					
					</c:forEach>
				</tr>
				<tr>
					<td width="25%" align="center"><h5 class="category-name">외식</h5></td>
					<td width="25%" align="center"><h5 class="category-name">교통비</h5></td>
					<td width="25%" align="center"><h5 class="category-name">문화생활</h5></td>
					<td width="25%" align="center"><h5 class="category-name">기타</h5></td>
				</tr>
					<tr align="center">
						<c:forEach var="plan" items="${postplan}" begin="4" end="7">
							<td width="25%" align="center">
								<h3 class="price-form" >${plan.PLD_PRICE}</h3>
							</td>					
						</c:forEach>
					</tr>
				<tr align="right">
					<td width="80%" colspan="4"><h3 class="won">
							Total : <input type="text" class="total-count" value="${totalCount}" disabled="disabled" /> 원
						</h3></td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<input type="button" value="수정" class="update" onclick="return planUpdate();"/>
					</td>
				</tr>
			</table>
	</div>
	</c:if>
	<p class="footer"></p>
</body>
</html>












