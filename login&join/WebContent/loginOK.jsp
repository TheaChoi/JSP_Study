<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 	<% --%>
<!-- 		String id = (String)request.getAttribute("UserID");  //스트링으로 형변환을 해주어야만 UserID를 받아올수있음 -->
<%-- 	%> --%>
	<%
		String id2 = (String)session.getAttribute("UserID");  //스트링으로 형변환을 해주어야만 UserID를 받아올수있음
	%>
<%-- <h1><%=id %>님이 로그인을 성공했습니다.</h1> --%>
<h1><%=id2 %>님이 로그인을 성공했습니다.</h1>
<a href="Controller?cmd=logout">로그아웃</a>
		<!-- ?앞은 주소 ?뒤는 전달할 값 -->
<a href="menu.jsp"> 메인 메뉴</a>
	
</body>
</html>