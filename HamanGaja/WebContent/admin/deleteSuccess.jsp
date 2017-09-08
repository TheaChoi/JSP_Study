<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String type = request.getParameter("type"); 
	type = URLEncoder.encode(type, "UTF-8");
	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	int viewStart = Integer.parseInt(request.getParameter("viewStart"));
	int viewEnd = Integer.parseInt(request.getParameter("viewEnd"));
	String word = request.getParameter("search");
%>


<script>
	function myAlert(){
		alert("게시글이 삭제되었습니다.");
		location.href="List.board?pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>";
	}
	
	setTimeout("myAlert()", 100);
</script>

</html>