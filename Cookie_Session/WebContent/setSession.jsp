<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>setSession</title>
</head>
<body>
<h1>세션에 값 저장하기</h1>

<%
	String name="꼬부기";
	session.setAttribute("sessionData1", name);
	%>
	
	세션에 저장한 값   "<%=name %>"
	<br><a href="index.jsp">이전페이지로 가기</a>

</body>
</html>