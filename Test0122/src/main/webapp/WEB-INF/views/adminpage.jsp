<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.mj.dao.*"%>
<%@page import="com.mj.dto.*"%>
<%@page import="java.util.List"%>
<%-- 메시지 있으면 출력 --%>
<c:set var="msg" value="${msg}"/>
<c:choose>
	<c:when test="${not empty msg}">			
		<script>alert("${msg}");</script>
	</c:when>
</c:choose>
<%-- //메시지 있으면 출력 --%>
    <%
		String loginId = (String)session.getAttribute("userId");
		List<UsersDto> listuser = (List<UsersDto>)request.getAttribute("management");
		int rdpoint = (int)(Math.random() * 1000) + 1;
	%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자페이지</title>
	<script src="resources/js/jquery-3.7.1.min.js"></script>
	<script>
		const loginId = "<%= loginId %>";
		function btnStart() {
			location.href = "start";
		}
		function btnEnd() {
			location.href = "end";	
		}
		$(function(){
			//로그인 버튼 클릭 시 로그인화면으로 이동
			$("#login_btn").click(function(){
				location.href = "${pageContext.request.contextPath}/";
			});
			//수정버튼 클릭시 수정 페이지 이동(그 유저의 아이디를 들고가야됨)
			$(".btn_update").click(function() {
		        // 클릭된 버튼(this)에 저장된 data-id 값을 가져옵니다.
		        let id = $(this).data("id");
		        
		        location.href = "userUpdate?id=" + id;
    		});
			//삭제버튼 클릭시 삭제(그 유저의 아이디를 삭제)
			$(".btn_delete").click(function() {
		        // 클릭된 버튼(this)에 저장된 data-id 값을 가져옵니다.
		        let id = $(this).data("id");
		        alert("삭제되었습니다.");
		        location.href = "userDelete?id=" + id;
    		});
		});
	</script>
	<style>
		#admin_page_hedder {
			height: 100px;
		}
		#mainpage_text {
			font-size: 50px;
    		font-weight: 600;
    		margin-right: 300px;
		}
		table {
			height: 300px;
		    width: 565px;
		    border-collapse: collapse;
		    text-align: center;
		}
		#user_setting {
			height: 350px;
		}
	</style>
</head>
<body>
	<div id="admin_page_hedder">
		<span id="mainpage_text">회원관리</span>
		<button id="login_btn">로그인</button>
	</div>
	<div>
		<div id="user_setting">
			<table border="1">
				<tr id = "table_hedder">
					<th>ID</th>
					<th>PW</th>
					<th>Name</th>
					<th>Point</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			<%for(UsersDto dto : listuser) { %>
				<tr>
					<td><%=dto.getId()%></td>
					<td><%=dto.getPw()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getPoint()%></td>
					<td><button class="btn_update" data-id="<%=dto.getId()%>">수정</button></td>
					<td><button class="btn_delete" data-id="<%=dto.getId()%>">삭제</button></td>
				</tr>
			<%} %>
			</table>
		</div>
		<div>
			<h1 id="scheduler_text">스케줄러관리</h1>
			<button onclick="btnStart();">스케줄러(20초마다 포인트1 증가)실행 시작</button>
			<button onclick="btnEnd();">스케줄러 실행 종료</button>
		</div>
	</div>
</body>
</html>