<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<body class="fontType">
<!-- Navigation -->
  <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="${path }/">KBFintech</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          
          <li class="nav-item">
            <a class="nav-link" href="${path }/card/register">카드 등록</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/product/question">상품 목록</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/plan/planer">플랜 작성</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              	내 정보 관리
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
              <a class="dropdown-item" href="portfolio-1-col.html">회원 정보</a>
              <a class="dropdown-item" href="portfolio-2-col.html">카드 관리하기</a>
              <a class="dropdown-item" href="portfolio-3-col.html">상품 관리하기</a>
              <a class="dropdown-item" href="portfolio-4-col.html">포인트 관리하기</a>
              <a class="dropdown-item" href="portfolio-item.html">플랜 관리하기</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path}/member/login">로그인</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</body>