<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="dashboard-content">
	<form action="/member/register" method="post" enctype="multipart/form-data">
		<p>아이디 : <input type="text" name="userId" id="userId"/>
		<p>이름 : <input type="text" name="userName" id="userName"/>
		<p>프로필 사진 : <input type="file" name="picture" id="picture"/>
		<br/>
		<input type="submit" value="등록"/><br/>
		<button type="button" id="btnList" onclick="javascript:location.href='/member/main'">로그인</button>
	</form>
</div>
</body>
</html>