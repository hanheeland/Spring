<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${empty cartList}">
		<b>선택한 상품이 없습니다.</b>
	</c:when>
	<c:otherwise>
		<ul>
			<c:forEach var="cart" items="${cartList }">
				<li>${cart.prdName}</li>
			</c:forEach>
		</ul>
	</c:otherwise>
</c:choose>

</body>
</html>