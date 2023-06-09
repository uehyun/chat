<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
	<tr>
		<td>회원번호</td>
		<td>회원 아이디</td>
		<td>회원 이름</td>
		<td>채팅하기</td>
	</tr>
	<c:forEach items="${memberList }" var="member">
		<tr>
			<td>${member.userNo }</td>
			<td>${member.userId }</td>
			<td>${member.userName }</td>
			<td><a href="/chat/chatMessage?userId=${member.userId }">채팅하기</a></td>
		</tr>
	</c:forEach>
</table>