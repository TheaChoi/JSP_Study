<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- //관리자가 아닌 사람은 들어올 수 없도록 -->
<%
	String userid = (String) session.getAttribute("USERID");
	String admin = (String) session.getAttribute("ADMIN");
	
	if(userid != null && admin != null){ %>
<!-- 		<script> alert("관리자 로그인 상태");</script> -->
<%}	else{%>
		<script> alert("관리자 권한으로 사용되는 페이지입니다. 관리자로 로그인해 주세요.");
			location.href="index.jsp";
		</script>
<%}%>