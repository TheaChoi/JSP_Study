<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>setCookie</title>
</head>

<body>

<h1>쿠키에 데이터 생성하기</h1>

<%

Cookie c1 = new Cookie("name", "홍길동"); 
	response.addCookie(c1);
	c1.setMaxAge(60*60*24);
	c1.setComment("abs");
	
	Cookie c2 = new Cookie("SSang", "Min");
	response.addCookie(c2);
	c2.setMaxAge(60*60*24);
	c2.setComment("자기가 무슨 종인지도 모르고 쯧쯧");
%>


<a href="index.jsp">이전 페이지로</a>
</body>
</html>