<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <hr>
   <h1> request 객체에 데이터 저장하기 </h1>
<%
   request.setAttribute("ReqData1", "홍길동");

// 	RequestDispatcher RD = request.getRequestDispatcher("req1.jsp");
// 	RD.forward(request, response);
	
%>
<hr>
<h1>session객체에 데이터 저장하기</h1>


<a href="setSession.jsp">세션에 값 저장하기</a>
<a href="getSession.jsp">세션에서 값 읽어오기</a>

<hr>
<h1>Cookie 객체에 데이터 저장하기</h1>

<a href="setCookie.jsp">쿠키에 값 저장하기</a>
<a href="getCookie.jsp">쿠키에서 값 읽어오기</a>

</body>
</html>