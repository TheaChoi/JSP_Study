<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int x=Integer.parseInt(request.getParameter("x"));
	int y=Integer.parseInt(request.getParameter("y"));
	int z=x+y;
	int z1=x*y;
%>
{"plus":<%=z%>, "mul":<%=z1 %>}
