<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>req1.jsp</title>
</head>
<body>

<h1>req1.jsp</h1>
<%
	String data = (String)request.getAttribute("ReqData1");
	RequestDispatcher RD = request.getRequestDispatcher("req2.jsp");
	RD.forward(request, response);
%>
	index.jsp 에서 전달받은 값은  <%=data %>;

</body>
</html>