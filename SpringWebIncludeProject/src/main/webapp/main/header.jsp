<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="../main/main.do">Include</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">서울 여행
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../seoul/list.do">명소</a></li>
          <li><a href="../seoul/nature.do">자연</a></li>
          <li><a href="../seoul/shop.do">쇼핑</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">스토어
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../goods/main.do">전체상품</a></li>
          <li><a href="../goods/main.do">신상품</a></li>
          <li><a href="../goods/main.do">베스트상품</a></li>
        </ul>
      </li>
      <li><a href="#">실시간 채팅</a></li>
      <li><a href="../food/find.do">맛집 검색</a></li>
      <li><a href="../goods/find.do">상품 검색</a></li>
    </ul>
  </div>
</nav>
</body>
</html>