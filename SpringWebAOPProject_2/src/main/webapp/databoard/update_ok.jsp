<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${bCheck==true }"> <!-- 수정이 된 상태 -->
	<c:redirect url="detail.do?no=${no }"></c:redirect>
</c:if>
<c:if test="${bCheck==false}"> <!-- 수정이 안된 상태 -->
	<script>
		alert("비밀번호가 틀립니다!");
		history.back();
	</script>
</c:if>