<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%-- 메시지 있으면 출력 --%>
<c:set var="msg" value="${msg}"/>
<c:choose>
	<c:when test="${not empty msg}">			
		<script>alert("${msg}");</script>
	</c:when>
</c:choose>
<%-- //메시지 있으면 출력 --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="resources/js/jquery-3.7.1.min.js"></script>
	<script>
			$(function(){
				$("#btn_cearted").click(function(){
					location.href="cearted_id";
				});
			});
	</script>
	</script>
	<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="login_check" method="post" style="margin: 0;">
		ID : <input id="loginId" type="text" name="id" style="width:169px; margin-left: 8px;margin-bottom: 3px;"/><br/>
		PW : <input id="loginPw" type="password" name="pw" style="margin-bottom: 3px;"/><br/>
		<button type="submit" style="width: 216px;margin-bottom: 3px;">로그인</button>
	</form>
	<button id="btn_cearted" style="width: 216px;margin-bottom: 3px;">회원가입</button>
</body>
</html>