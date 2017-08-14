<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file = "login.jsp" %>  

<script>
	function myAlert(){
		alert("아이디 또는 비밀번호가 틀렸거나,\n가입되지 않은 회원입니다.");
		//location.href="login.jsp";
	}
	
	setTimeout("myAlert()", 100);
</script>

</html>