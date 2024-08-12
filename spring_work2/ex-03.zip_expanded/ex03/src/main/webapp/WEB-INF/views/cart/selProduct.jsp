<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<head>
<title>selProduct.jsp</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="../../../resources/js/cart.js"></script>
</head>

<body>
<div align="center">
	<H2>상품선택</H2>
	<HR>
	 ${member.name } 님 환영합니다!!!!
	<HR>
	<form name="form1" action="/cart/add" method="POST">
		<input type="hidden" name="id" value="${member.id} ">
		<SELECT name="prdName">
			<option>사과</option>
			<option>귤</option>
			<option>파인애플</option>
			<option>자몽</option>
			<option>레몬</option>
		</SELECT>
		<input type="submit" value="추가"/>
	</form>
	
	<form name="form2"  action="/cart/list" method="POST">
		<input type="hidden" name="id" value="${member.id} ">
		<input type="submit" value="계산"/>
	</form>
</div>

</body>
</html>