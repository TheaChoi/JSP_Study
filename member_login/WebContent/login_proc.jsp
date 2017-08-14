<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.DAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 처리</title>
</head>
<body>

   <%
   	
      String id = request.getParameter("id");
      String pw = request.getParameter("pw");

 
  
  	DAO dao=new DAO();
  
  	
 

		

		dao.login(id, pw);
	
		if(dao.login(id, pw)==true)
		{
			//out.print("ㅋ 로그인 성공 ㅋ");
			response.sendRedirect("index.jsp");
		}else
		{
			//out.print("ㅉ 망함 ");
			
	
			response.sendRedirect("login fail.jsp");
		}
	%>
</body>
</html>