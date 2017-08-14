<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getCookie</title>
</head>
<body>
<h1>쿠키 객체에 저장된 값 읽어오기</h1>
<h2>쿠키는 배열로 전달된다</h2>

<%
	Cookie[] cookies = request.getCookies();

	
	if(cookies != null){
		
		for(int i=0; i<cookies.length ; i++) {
			
			Cookie c = cookies[i];
			
			String cName = c.getName();
			String cValue = c.getValue();
			String cComment = c.getComment();
			
			if(cName.equals("name")==true) {
				c.setMaxAge(0); //만료시간을 0으로 함
	            response.addCookie(cookies[i]); //쿠키정보를 브라우저로 다시 보내줘야함 (여기는 서버)
			}
			
			out.println("이름:"+cName+",   값:"+cValue);
			out.println(cComment);
			out.println("<br>");
		}
	}
%>

</body>
</html>