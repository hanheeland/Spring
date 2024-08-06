<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>글목록</h3>
	<a href="/springweb//board_insert">글쓰기</a>
	<br><br>
	
	<table border="1" width="800">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>삭제</th>
		</tr>
		
		<c:forEach var="board" items="${list }">
			<tr>
				<td>${board.seq }</td>
				<td><a href="/springweb/board_detail/${board.seq }">${board.title }</a></td>
				<td>${board.writer }</td>
				<td>
					<fmt:parseDate var="dt" value="${board.regdate }" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${dt }" pattern="yyyy/MM/dd"/>
				</td>
				<td>${board.hitcount }</td>
				<td><a href="delete.jsp?seq=${board.seq} ">삭제</a></td>
			</tr>
		</c:forEach>

	</table>
	<br><br>
	
</body>
</html>

