<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="insertId" method="post">
		ID : <input id="ceartId" type="text" name="id" style="width:190px; margin-left: 8px;margin-bottom: 3px;"/><br/>
		PW : <input id="ceartPw" type="password" name="pw" style="margin-bottom: 3px; width: 190px;"/><br/>
		NAME : <input id="ceartName" type="text" name="name" style="margin-bottom: 3px;"/><br/>
		<button type="submit" style="width: 237px;">작성완료</button>
	</form>
</body>
</html>