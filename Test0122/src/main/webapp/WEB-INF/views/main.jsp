<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
		UsersDto dto = (UsersDto)request.getAttribute("userInfo");
		int rdpoint = (int)(Math.random() * 1000) + 1;
	%>
<!DOCTYPE html>
<html>
<head>
	<script src="resources/js/jquery-3.7.1.min.js"></script>
	<meta charset="UTF-8">
	<title>메인페이지</title>
	<script>
		const loginId = "<%= loginId %>";
		let rdpoint = "<%= rdpoint %>";
		$(function(){
			$("#logout_btn").click(function(){
				alert("로그아웃 되었습니다.");
				location.href="logout";
			});
			$("#png1").click(function(){
		        let point = parseInt($("#my_point").text().replace(/[^0-9]/g, ""));
		        let contentPoint = parseInt($("#lntro").text().replace(/[^0-9]/g, ""));
		        if(point < contentPoint) {
		            alert("포인트가 부족합니다. 광고를 클릭하세요.");
		        }else{
		            alert("컨텐츠(intro)를 구입하였습니다.");
		            // 추가: 컨트롤러로 차감 정보를 보냄
		            location.href = "purchaseContent?amount=" + contentPoint;
		        }
		    });

		    $("#png2").click(function(){
		        let point = parseInt($("#my_point").text().replace(/[^0-9]/g, ""));
		        let contentPoint = parseInt($("#java").text().replace(/[^0-9]/g, ""));
		        if(point < contentPoint) {
		            alert("포인트가 부족합니다. 광고를 클릭하세요.");
		        }else{
		            alert("컨텐츠(java)를 구입하였습니다.");
		            // 추가: 컨트롤러로 차감 정보를 보냄
		            location.href = "purchaseContent?amount=" + contentPoint;
		        }
		    });

		    $("#png3").click(function(){
		        let point = parseInt($("#my_point").text().replace(/[^0-9]/g, ""));
		        let contentPoint = parseInt($("#cplpl").text().replace(/[^0-9]/g, ""));
		        if(point < contentPoint) {
		            alert("포인트가 부족합니다. 광고를 클릭하세요.");
		        }else{
		            alert("컨텐츠(c++)를 구입하였습니다.");
		            // 추가: 컨트롤러로 차감 정보를 보냄
		            location.href = "purchaseContent?amount=" + contentPoint;
		        }
		    });

		    $("#logo").click(function(){
		        alert(rdpoint + "점이 적립되었습니다.");
		        // 추가: 광고 클릭 시 포인트 적립을 위해 컨트롤러로 이동
		        // 기존의 외부 사이트 이동 대신, 서버를 거쳤다가 이동하게 바꿉니다.
		        location.href = "addPoint?amount=" + rdpoint;
		    });
			
		});
	</script>
	<style>
		#main_hedder {
			position: relative;
			height: 140px;
		}
		#mainpage_text {
			font-size: 30px;
   			font-weight: 700;
		}
		#right_setting {
			position: absolute;
    		right: 30px;
    		font-size: 20px;
    		font-weight: 500;
		}
		#main_content {
			position: relative;
			width: 100%;
    		height: 75%;
		}
		#content_text {
			font-size: 25px;
		}
		.img_set {
			position: absolute;
			width: 350px;
    		height: 408px;
    		top: 50px;
		}
		#png1 {
		    left: 10px;
		}
		#png2 {
			left: 40%;
		}
		#png3 {
			right: 10px;
		}
		.point_text {
			position: absolute;
			font-size: 20px;
		}
		#lntro {
			left: 110px;
  	 		top: 470px;
		}
		#java {
			top: 470px;
   			left: 48%;
		}
		#cplpl {
			top: 470px;
    		right: 110px;
		}
		#logo_div {
			position: absolute;
			bottom: -680px;
			right: 10px;
		}
		#logo_div > span {
			position: absolute;
			top: -15px;
		}
		#logo {
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div id="main_hedder">
		<span id="mainpage_text">메인페이지</span>
		<div id="right_setting">
			<% if(dto != null) { %>
            <span><span><%=dto.getName()%>(<%=dto.getId() %>)</span>님 안녕하세요.</span>
            <button id="logout_btn">로그아웃</button> <br/>
            <span>포인트 : <span id="my_point"><%=dto.getPoint()%></span></span>
        	<% } %>
		</div>
	</div>
	<div id="main_content">
		<span id="content_text">구입할 컨텐츠를 선택하세요.</span>
		<img class="img_set" id="png1" src="resources/img/Intro_350_408.png"/>
		<span class="point_text" id="lntro"> 100,000 포인트</span>
		<img class="img_set" id="png2" src="resources/img/Java_350_408.png"/>
		<span class="point_text" id="java"> 500,000 포인트</span>
		<img class="img_set" id="png3" src="resources/img/Cpp_350_408.png"/>
		<span class="point_text" id="cplpl"> 300,000 포인트</span>
		<div id="logo_div">
			<span><광고></span>
			<img id="logo" src="resources/img/korea_it.png"/>
		</div>
	</div>
</body>
</html>