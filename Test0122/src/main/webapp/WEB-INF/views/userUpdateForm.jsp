<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리-수정관리자</title>
<script>
$(".submit_btn").click(function(){
	alert("수정되었습니다.");
});
</script>
</head>
<body>
	<h1>회원관리-수정관리자</h1>

    <form action="userUpdateCommit" method="post">
        <div class="input-group">
            <label>ID</label>
            <input type="text" name="id" value="${user.id}" readonly>
        </div>
        
        <div class="input-group">
            <label>PW</label>
            <input type="text" name="pw" value="${user.pw}">
        </div>
        
        <div class="input-group">
            <label>Name</label>
            <input type="text" name="name" value="${user.name}">
        </div>
        
        <div class="input-group">
            <label>point</label>
            <input type="number" name="point" value="${user.point}">
        </div>

        <button type="submit" class="submit_btn">제출</button>
    </form>
</body>
</html>