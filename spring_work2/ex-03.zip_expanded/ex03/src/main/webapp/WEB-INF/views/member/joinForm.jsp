<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/member/join" method="post">
		<fieldset>
			<ul>
				<li>
					<label>아이디</label>
					<input type="text" name="id" >
				</li>
				<li>
					<label>패스워드</label>
					<input type="password" name="password">
				</li>
				<li>
					<label>이름</label>
					<input type="text" name="name" >
				</li>
				<li>
					<label>핸드폰번호</label>
					<input type="text" name="hp">
				</li>
				<li>
					<label>주소</label>
					<input type="text" name="address" >
				</li>
				<li>
					<lable>추천 경로</lable>
					 <input type="radio" name="regpath" value="네이버" /> 네이버
			        <input type="radio" name="regpath" value="구글" /> 구글
			        <input type="radio" name="regpath" value="페이스북" /> 페이스북
				</li>
			</ul>
		</fieldset>
		<fieldset>
			<input type="submit" value="전송">
		</fieldset>
	</form>
	
</body>
</html>