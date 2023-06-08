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
	<form action="/member/login" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="userId" id="userId"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="/member/register">회원가입</a>
				</td>
			</tr>
		</table>
		<button type="submit">로그인</button>
	</form>
</div>
</body>
</html>