<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let bClick=true
$(function(){
	$('.updates').click(function(){
		$('.ups').hide()
		let no=$(this).attr("data-no")
		if(bClick==true)
		{
			bClick=false;
			$(this).text("취소")
			$('#u'+no).show()
		}
		else
		{
			bClick=true;
			$(this).text("수정")
			$('#u'+no).hide()
		}
	})
})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td width="30%" class="text-center" rowspan="6">
						<img src="${vo.goods_poster }" style="width: 290px;height: 400px">
					</td>
					<td colspan="1">
						<h3>${vo.goods_name }</h3>
					</td>
				</tr>
				<tr>
					<th width="1%" class="text-right"></th>
					<td width="50%">${vo.goods_sub }</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">가격</th>
					<td width="50%">${vo.goods_price }</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">할인율</th>
					<td width="50%">${vo.goods_discount }</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">첫구매 할인가</th>
					<td width="50%">${vo.goods_first_price }</td>
				</tr>
				<tr>
					<th width="20%" class="text-right">배송</th>
					<td width="50%">${vo.goods_delivery }</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
		<div style="height: 20px"></div>
		<div class="col-sm-8">
			<table class="table">
				<tr>
					<td>
						<c:forEach var="gvo" items="${gList }">
							<table class="table">
								<tr>
									<td class="text-left">♥${gvo.name }(${gvo.dbday })</td>
									<td class="text-right">
										<c:if test="${gvo.id==sessionScope.id }">
											<span class="btn btn-xs btn-info updates" data-no=${gvo.rno }>수정</span>
											<a href="../goodsReply/reply_delete.do?rno=${gvo.rno }&no=${vo.no}" class="btn btn-xs btn-success">삭제</a>
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="text-left" valign="top">
										<pre style="white-space: pre;border: none;background-color: white;">${gvo.msg }</pre>
									</td>
								</tr>
								<tr style="display: none" id="u${gvo.rno }" class="ups">
									<td colspan="2">
										<form method="post" action="../goodsReply/reply_update.do">
											<input type=hidden name=no value="${vo.no }">
											<input type=hidden name=rno value="${gvo.rno }">
											<textarea rows="5" cols="65" name="msg" style="float: left">${gvo.msg }</textarea>
											<button class="btn-primary" style="width: 100px;height: 106px;float: left">댓글수정</button>
										</form>
									</td>
								</tr>
							</table>
						</c:forEach>
					</td>
				</tr>
			</table>
			<c:if test="${sessionScope.id!=null }">
			<table class="table">
				<tr>
					<td>
						<form method="post" action="../goodsReply/reply_insert.do">
							<input type=hidden name=no value="${vo.no }">
							<textarea rows="5" cols="65" name="msg" style="float: left"></textarea>
							<button class="btn-primary" style="width: 100px;height: 106px;float: left">댓글쓰기</button>
						</form>
					</td>
				</tr>
			</table>
			</c:if>
		</div>
	</div>
</body>
</html>