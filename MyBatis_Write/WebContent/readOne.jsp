<%@page import="board.ContentDTOOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	ContentDTOOut dto = (ContentDTOOut)request.getAttribute("CONTENTDTOOUT");
	int num = dto.getNum();
	%>
	
	
<title>글 읽어 오기</title>
</head>
<body>

<h1><%=dto.getTitle() %></h1>
글번호: <%=dto.getNum() %>  <br>
글제목 : <%=dto.getTitle() %> <br>
조회수: <%=dto.getCount() %><br>
작성일 : <%=dto.getReg_date().toString().substring(0,11) %>
<br><br>

<div style="border:1px solid #000; padding:10px; font-size:12px; width:250px; height:150px; margin:10px; line-height:20px;">
글내용<br>
<%=dto.getContent() %>
</div>

<a href="list.bo?pageNo=<%=pageNo %>&pageSize=<%=pageSize%>">글목록</a>
<a href="del.bo?num=<%=num %>">글삭제</a>
</body>
</html>