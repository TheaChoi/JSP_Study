<%@page import="board.PageOut"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
<%
	List list = (List)request.getAttribute("LIST");
	PageOut pageOut = (PageOut)request.getAttribute("PAGEOUT");
	
	//시작 글번호는 총 글갯수 - 현재페이지 * 페이지의글개수
	int startNum = pageOut.getTotal() - pageOut.getPageNo()*pageOut.getPageSize();
%>

<style>
	a, span {font-size:20px; margin-right:20px; text-decoration:none; color:black;}
	a:visited { color: black; text-decoration: none;}
	a:hover {font-weight:bold;}

</style>

</head>
<body>

<h1> 게시판 </h1>
<table border='1'>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>조회수</td>
		<td>작성일</td>
	</tr>
	
<%
	int size = list.size();
	for(int i=0; i<size ; i++){
		
		HashMap hashmap = (HashMap)list.get(i);
		
		int num = (int)hashmap.get("num");
		String title = (String)hashmap.get("title");
		int count = (int)hashmap.get("count");
		Timestamp reg_date = (Timestamp)hashmap.get("reg_date");
	
%>
	<tr>
		<td><%=startNum%></td>
		<td><a href="read.bo?num=<%=num%>&pageNo=<%=pageOut.getPageNo() %>&pageSize=<%=pageOut.getPageSize() %>"><%=title%></a></td>
		<td><%=count%></td>
		<td><%=reg_date.toString().substring(0,10) %></td>
	</tr>
	
<% 
		startNum--;
	}//for(int i=0;i<size;i++)의 끝
%>
</table>

<%
	int pageNo = pageOut.getPageNo();  //현재 페이지 번호

 	if(pageOut.getPrevPage()!=-1){
%>

<a href="list.bo?pageNo=<%=pageOut.getPrevPage()%>&pageSize=<%=pageOut.getPageSize()%>"> ◀ </a>
	
<%} else{%>
<span style="color:grey">◀</span>
<%} %>

<%
	for(int i=0; i<pageOut.getPageNum() ; i++) {
		if(i != pageNo){
%>		
		<a href="list.bo?pageNo=<%=i%>&pageSize=<%=pageOut.getPageSize()%>"> <%=i+1 %> </a>
		
<%}else{
%>

	<span style="color:red"><%=i+1%></span>
	<% }
}
%>
<%
 if(pageOut.getNextPage()!=-1){
%>
<a href="list.bo?pageNo=<%=pageOut.getNextPage()%>&pageSize=<%=pageOut.getPageSize()%>">▶</a>
<%} else{%>
<span style="color:grey">▶</span>
<%} %>

<form action="find.bo" method="get">
	입력 <input type="text" name="word">
	<select name="mode">
		<option value="1" selected>제목</option>
		<option value="2">내용</option>
		<option value="3">제목+내용</option>
	</select>
	<input type="hidden" name="pageNo" value="0">
    <input type="hidden" name="pageSize" value="5">
	<input type="submit" value="검색">
</form>


</body>
</html>