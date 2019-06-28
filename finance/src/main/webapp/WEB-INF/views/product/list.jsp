<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/menu.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
<script type="text/javascript">
	$(document).ready(

			function() {

				var actionForm = $("#actionForm");
				$(".page-item a").on(
						"click",
						function(e) {
							e.preventDefault();
							actionForm.find("input[name='pageNum']").val(
									$(this).attr("href"));
							actionForm.submit();
						});

				var panel = $(".panel");
				panel.find(".panel-body").slideUp();
				panel.find(".panel-heading span.clickable").addClass(
						"panel-collapsed");
				panel.find(".panel-heading span.clickable").find('i')
						.removeClass('glyphicon-chevron-down').addClass(
								'glyphicon-chevron-up');

				$(".panel-heading span.clickable").on(
						"click",
						function(e) {

							var $this = $(this);
							if (!$this.hasClass('panel-collapsed')) {
								$this.parents('.panel').find('.panel-body')
										.slideUp();
								$this.addClass('panel-collapsed');
								$this.find('i').removeClass(
										'glyphicon-chevron-down').addClass(
										'glyphicon-chevron-up');

							} else {
								$this.parents('.panel').find('.panel-body')
										.slideDown();
								$this.removeClass('panel-collapsed');
								$this.find('i').removeClass(
										'glyphicon-chevron-up').addClass(
										'glyphicon-chevron-down');

							}
						});
			});
</script>
</head>
<body class="fontType">

	<div class="container">
		<h1 class="my-4" align="center">추천 결과를 확인하세요</h1>
		<hr>
		<div class="row">
			<div class="col-lg-6 col-md-6 col-xs-6">
				<div class="row">
					<h1>적금 상품</h1>
					<c:forEach var="baseInfo" items='${productListS}'>
						<div class="col-lg-2 col-md-2"></div>
						<div class="card col-lg-9 col-md-9 panel panel-primary">
							<div class="card-body">
								<div class="row">
									<div class="col-lg-9 col-md-9 panel-heading">
										<form id="formTarget" action="${path }/product/info"
											method="get">
											<h3 class="card-title panel-heading" id="no">${baseInfo.fin_prdt_nm}</h3>
											<h4 class="card-subtitle panel-heading text-muted">${baseInfo.best_rate}</h4>
											<input type="hidden" name="fin_prdt_cd"
												value='${baseInfo.fin_prdt_cd}'> <input
												type="hidden" name="fin_prdt_nm" value='$'> <input
												type="hidden" name="best_rate" value='${baseInfo.best_rate}'>

											<a
												href="/product/info?fin_prdt_cd=${baseInfo.fin_prdt_cd}&fin_prdt_nm=${baseInfo.fin_prdt_nm}">
												<button type="button" class="btn btn-outline-info">
													<h3>자세히 보기</h3>
												</button>
											</a>
										</form>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="col-lg-1 col-md-1"></div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-xs-6">
				<div class="row">
					<h1>예금 상품</h1>
					<c:forEach var="baseInfo" items='${productListD}'>
						<div class="col-lg-2 col-md-2"></div>
						<div class="card col-lg-9 col-md-9 panel panel-primary">
							<div class="card-body">
								<div class="row">
									<div class="col-lg-9 col-md-9 panel-heading">
										<h3 class="card-title panel-heading" id="no">${baseInfo.fin_prdt_nm}</h3>
										<h4 class="card-subtitle panel-heading text-muted">${baseInfo.best_rate}</h4>
										<a href="/product/info?fin_prdt_cd=${baseInfo.fin_prdt_cd}&fin_prdt_nm=${baseInfo.fin_prdt_nm}"><button
												type="button" class="btn btn-outline-info">
												<h3>자세히 보기</h3>
											</button></a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<div class="col-lg-1 col-md-1"></div>
				</div>
			</div>
		</div>

		<hr>
		<!-- Pagination -->
		<ul class="pagination justify-content-center">
			<c:if test="${pageMaker.prev }">
				<li class="page-item"><a class="page-link"
					href="${pageMaker.startPage-1 }" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
				</a></li>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startPage }"
				end="${pageMaker.endPage }">
				<li
					class="page-item ${pageMaker.cri.pageNum == num ? 'active' : '' }">
					<a class="page-link" href="${num }">${num }</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next }">
				<li class="page-item"><a class="page-link"
					href="${pageMaker.endPage+1 }" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
				</a></li>
			</c:if>
		</ul>
		<!--  -->
	</div>
	<!-- /.container -->
	<form id="actionForm" action="${path}/product/list" method="post">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
		<input type="hidden" name="fin_prdt_cd"
			value='${baseInfo.fin_prdt_cd}'> <input type="hidden"
			name="fin_prdt_nm" value='${baseInfo.fin_prdt_nm}'> <input
			type="hidden" name="best_rate" value='${baseInfo.best_rate}'>
	</form>
	<%@include file="../includes/footer.jsp"%>
</body>
</html>